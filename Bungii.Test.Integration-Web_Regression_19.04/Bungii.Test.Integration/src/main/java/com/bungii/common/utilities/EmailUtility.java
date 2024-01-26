package com.bungii.common.utilities;

import com.bungii.common.core.DriverBase;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.commons.mail.util.MimeMessageParser;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class EmailUtility extends DriverBase {

    public Store authenticateEmailClientAndGetStore(String host, String username, String password) throws MessagingException, GeneralSecurityException {
        System.setProperty("javax.net.ssl.trustStore", PropertyUtility.getEmailProperties("email.ca.certificate"));
        System.setProperty("javax.net.ssl.trustStorePassword", PropertyUtility.getEmailProperties("email.ca.password"));
        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", PropertyUtility.getEmailProperties("email.store.protocol"));
        MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
        socketFactory.setTrustAllHosts(true);
        properties.put("mail.imap.ssl.trust", "*");
        properties.put("mail.imaps.ssl.socketFactory", socketFactory);

        Session session = Session.getDefaultInstance(properties, null);//creating session
        Store store = session.getStore(PropertyUtility.getEmailProperties("email.store.protocol"));
        store.connect(host, username, password);//connect to GMail using imap protocol
        return store;
    }

    public Message[] getEmailObject(String expectedFromAddress, String expectedToAddress, String expectedSubject, Integer noOfPreviousDays) {
        String activationURL = null;
        //run the email checking code in a time loop

        long t = System.currentTimeMillis();
        long end = t + (5 * 60 * 1000);//run for 5 min 5*60*1000 in milli seconds
        boolean EmailwithLinkFound = false;
        Message[] recentMessages = null;
      //  while ((System.currentTimeMillis() < end)) {
            try {

                String host = PropertyUtility.getEmailProperties("email.host");
                String username = PropertyUtility.getEmailProperties("email.client.id");
                String password = PropertyUtility.getEmailProperties("email.client.password");

                Store store = authenticateEmailClientAndGetStore(host, username, password);

                Folder[] f = store.getDefaultFolder().list();
                for (Folder fd : f)
                    System.out.println(">> " + fd.getName());

                //Read Mail from NogginRequestor folder
                Folder folder = (IMAPFolder) store.getFolder("INBOX");//get mails from inbox folder

                System.out.print("Time ::::" + System.currentTimeMillis() + "\n");


                if (!folder.isOpen())
                    folder.open(Folder.READ_WRITE);
                Message[] messages = folder.getMessages();
                //Limit message from yesterday only
                Calendar cal = Calendar.getInstance();
                cal.roll(Calendar.DATE, -noOfPreviousDays);
                Date date = cal.getTime();

                SearchTerm st = new ReceivedDateTerm(ComparisonTerm.GT, date);
               recentMessages = folder.search(st, messages);

            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
       // }
        return recentMessages;
    }


    public String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    public String getURLFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        String aTag = "";
        String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String[] name = cucumberContextManager.getScenarioContext("CUSTOMER_NAME").toString().split(" ");
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        if(result.contains(name[0]))
        {
            Pattern pattern = Pattern.compile("<a href=\"(.*?) ",Pattern.DOTALL);
            Matcher matcher = pattern.matcher(result);
            while (matcher.find()) {
                aTag = matcher.group(1);
            }
        }
        return aTag;
    }
    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart)  throws MessagingException, IOException{
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }

    public String readHtmlContent(MimeMessage message) throws Exception {
        return new MimeMessageParser(message).parse().getHtmlContent();
    }

    public String readPlainContent(MimeMessage message) throws Exception {
        return new MimeMessageParser(message).parse().getPlainContent();
    }

    ////////////////////////////////////////////////////////////////////////////ALL HTML including alternatvie
    private boolean textIsHtml = false;

    public String getText(Part p) throws MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            String s = (String)p.getContent();
            textIsHtml = p.isMimeType("text/html");
            return s;
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }

        return null;
    }

    ////////////////////////
    public static String readLineByLineJava8(String filePath,String emailValue) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\r\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String fileValue=contentBuilder.toString();
        Path p1 = Paths.get(filePath);

        List<String> listF1 = Files.readAllLines(p1);
        List<String> listF2 = Arrays.asList(emailValue.split("\r\n"));

        for (int i = 0; i < listF1.size(); i++) {
            if (listF1.get(i).equals("old line")) {
                listF1.set(i, "new line");
                break;
            }
        }
        if(listF1.size()==listF2.size()){
            if ((listF1.equals(listF2)))
            {
                System.out.println("Both list are matching");

            }else{
                //both list are not matching ,iterate over all line to check value
                for(int i=0;i<listF1.size();i++){

                    if(listF1.get(i).equals(listF2.get(i))){
                  //      System.out.println("Subject: match found");

                    }else{

                        System.out.println(listF1.get(i));
                        System.out.println(listF2.get(i));

                    }
                }
            }
        }


        return contentBuilder.toString();
    }
    public void deleteAllEmails(Store store)
    {
        try
        {
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in));
            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("Number of unread messages = " + messages.length);
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
              //  System.out.println("Email Number " + (i + 1));
                String subject = message.getSubject();
                    message.setFlag(Flags.Flag.DELETED, true);
                    System.out.println("DELETED message with Subject: " + subject);
            }
            emailFolder.close(true);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
       }
    }
    public void deleteEmailWithSubject(String subject, Store store)
    {
        try
        {
            Store newStore = null;
            Folder emailFolder = null;
            if (store==null)
            {
                String host = PropertyUtility.getEmailProperties("email.host");
                String username = PropertyUtility.getEmailProperties("email.client.id");
                String password = PropertyUtility.getEmailProperties("email.client.password");
                newStore = authenticateEmailClientAndGetStore(host, username, password);
                emailFolder = newStore.getFolder("INBOX");
            }
            else
                emailFolder = store.getFolder("INBOX");

            emailFolder.open(Folder.READ_WRITE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in));
            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            // creates a search criterion
            SearchTerm searchCondition = new SubjectTerm(subject);
            Message[] foundMessages = emailFolder.search(searchCondition);

            for (int i = 0; i < foundMessages.length-1; i++) {
                Message message = foundMessages[i];
                String subject1 = message.getSubject();
                System.out.println("Found message #" + i + ": " + subject1);
                message.setFlag(Flags.Flag.DELETED, true);
                System.out.println("DELETED message with Subject: " + subject1);
            }
            emailFolder.close(true);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
