package com.bungii.common.utilities;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.stream.Collectors.toList;

public class GenerateSummaryReport {
    static Path configFilePath;
    private static ArrayList<String> summaryData = new ArrayList<>();
    private static ArrayList<String> failureSummaryData = new ArrayList<>();
    private static int passCount = 0, failCount = 0, inConclusiveCount = 0,skippedCount = 0;
    private static String logoFilePath = "";
    private static Date startTime, endTime;

    public static void main(String[] args) throws IOException, ParseException {
        try {
            if (args.length > 0) {
                 String mainFolder = args[0];
                String platform = args[1];
                String category = args[2];
                String environment = args[3];

                configFilePath = Paths.get(mainFolder);
                CopyMiscFolder();
                //get List of File
                List<String> listOfResultFile = getListOfResultFile();
                int testCount = 1;
                Boolean isFailed = false;
                //Iterate over all HTML file
                for (String path : listOfResultFile) {

                    File in = new File(path);

                    String subFolder = in.getParentFile().getName();
                    //Logo image path
                    if (logoFilePath.equals("")) {
                        PropertyUtility.loadRunConfigProps();
                        String relativePath = PropertyUtility.getResultConfigProperties("MISC_DIRECTORY") + "/" + PropertyUtility.getResultConfigProperties("LOGO_FILENAME");
                        logoFilePath = subFolder + "/" + relativePath;
                    }
                    //Parse HTML file and extract data
                    Document doc = Jsoup.parse(in, null);
                    Element table = doc.select("table").get(0); //select the first table.

                    Elements rows = table.select("tr");
                    int featureTotal = Integer.parseInt(doc.getElementById("pass").val().contains("--") ? "0" : doc.getElementById("pass").val())+Integer.parseInt(doc.getElementById("fail").val().contains("--") ? "0" : doc.getElementById("fail").val())+ Integer.parseInt(doc.getElementById("inconclusive").val().contains("--") ? "0" : doc.getElementById("inconclusive").val())+Integer.parseInt(doc.getElementById("skipped").val().contains("--") ? "0" : doc.getElementById("skipped").val());
                    int featurePass = Integer.parseInt(doc.getElementById("pass").val().contains("--") ? "0" : doc.getElementById("pass").val());
                    int featureFail = Integer.parseInt(doc.getElementById("fail").val().contains("--") ? "0" : doc.getElementById("fail").val());
                    int featureInconclusive = Integer.parseInt(doc.getElementById("inconclusive").val().contains("--") ? "0" : doc.getElementById("inconclusive").val());
                    int featureSkipped = Integer.parseInt(doc.getElementById("skipped").val().contains("--") ? "0" : doc.getElementById("skipped").val());

                    String featureSummary = "[TOTAL : "+featureTotal+" | PASS : "+ featurePass +" | FAIL : "+ featureFail + " | INCONCLUSIVE : " + featureInconclusive + " | SKIPPED : " + featureSkipped+ "]";
                    summaryData.add("<tr> </tr>");
                    summaryData.add(" <td colspan=3 style='text-align:left;'> FEATURE : " + in.getName().toString().replace(".html", "") +" "+ featureSummary+" </td>");
                    summaryData.add(" <td colspan=3><a href=" + subFolder + "/" + in.getName() + "> EXECUTION REPORT : " + in.getName() + "</td>");
                    summaryData.add("<tr> </tr>");

                    passCount = passCount + featurePass;
                    failCount = failCount + featureFail;
                    inConclusiveCount = inConclusiveCount + featureInconclusive;
                    skippedCount = skippedCount + featureSkipped;

                    for (int i = 1 + 1; i < rows.size(); i++) { //first row is the col names so skip it.
                        Element row = rows.get(i);
                        Elements cols = row.select("td");
                        String data = cols.toString();
                        //cols.get(2)
                        //Replace No of steps column by space
                        data = data.replace(cols.get(2).toString(), "");
                        //if first test case in result file then Get start time
                        if (i == 2) {
                            String startTime = cols.get(3).text();
                            storeStartTime(startTime);
                        }
                        //if last test case in result file then Get end time
                        if (i == rows.size() - 1) {
                            String startTime = cols.get(4).text();
                            storeEndTime(startTime);
                        }
                        summaryData.add("<tr></tr><td>" + testCount + "</td>");
                        summaryData.add(data);
                        testCount++;
                    }
                    if(doc.select("table").size()>2) {
                        Element table2 = doc.select("table").get(2); //select the third hidden table.

                    Elements rows2 = table2.select("tr");
                    for (int i = 1 + 1; i < rows2.size(); i++) { //first row is the col names so skip it.
                        Element row = rows2.get(i);
                        Elements cols = row.select("td");
                        String data = cols.toString();
                        failureSummaryData.add("<tr>" + data + "</tr>");
                        isFailed = true;
                    }
                    }
                }
                createResultFileFromSummaryTemplate(platform, category, environment);
                System.out.println("Generated index.html");
                createCountTemplate(platform, category, environment);
                System.out.println("Generated summarycount.html");

                new GenerateResultCSV().GenerateCSV(mainFolder);


                if (isFailed)
                {
                    createResultFileFromFailedSummaryTemplate(platform, category, environment);
                    CopyScreenshotsToDirectory();
                    System.out.println("Generated failedsummary.html");

                }

                newName(configFilePath,"MavenRun");
            } else {
                System.err.println("Pass Main folder  name of parallel test  as argument");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    static  Path  newName(Path oldName, String newNameString) throws IOException {
        return Files.move(oldName, oldName.resolveSibling(newNameString));
    }

    /**
     * Check if start time of current report is less then start time of previous report .If less then update start time
     *
     * @param time time parsed from HTML report
     */
    public static void storeStartTime(String time) {
        try {
            DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyyy", Locale.ENGLISH);
            Date date = format.parse(time);
            if (startTime == null)
                startTime = date;

            if (startTime.after(date)) {
                startTime = date;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if end time of current report is more then end time of previous report .If Greater then update end time
     *
     * @param time time parsed from HTML report
     */
    public static void storeEndTime(String time) {
        try {
            DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyyy", Locale.ENGLISH);
            Date date = format.parse(time);
            if (endTime == null)
                endTime = date;

            if (endTime.before(date)) {
                endTime = date;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * @return return List of HTML file
     * @throws IOException
     */
    public static List<String> getListOfResultFile() throws IOException {
        List<String> listOfResultFile = Files.walk(configFilePath)
                .filter(s -> s.toString().endsWith(".html")).map((p) -> p.getParent() + "/" + p.getFileName())
                .filter(s -> !s.toString().contains("htmlpublisher-wrapper"))
                .filter(s -> !s.toString().contains("index"))
                .sorted()
                .collect(toList());
        return listOfResultFile;
    }
    /**
     * @return return List of HTML file
     * @throws IOException
     */
    public static List<String> getScreenshots() throws IOException {
        List<String> listOfScreenshotFile = Files.walk(configFilePath)
                .filter(s -> s.toString().endsWith(".jpg")).map((p) -> p.getParent() + "/" + p.getFileName())
                .sorted()
                .collect(toList());
        return listOfScreenshotFile;
    }
    /**
     * @return return List of HTML file
     * @throws IOException
     */
    public static List<String> getLogos() throws IOException {
        List<String> listOfScreenshotFile = Files.walk(configFilePath)
                .filter(s -> s.toString().endsWith(".PNG")).map((p) -> p.getParent() + "/" + p.getFileName())
                .sorted()
                .collect(toList());
        return listOfScreenshotFile;
    }
    /**
     * @return return List of HTML file
     * @throws IOException
     */
    public static void CopyScreenshotsToDirectory() throws IOException {
        try {
            List<String> listOfScreenshotFile = getScreenshots();
            Path targetDirectory = Paths.get(configFilePath+"/Screenshot");
            File directory = new File(String.valueOf(targetDirectory));
            if(!directory.exists()) {
                directory.mkdir();
            }
            for(String file : listOfScreenshotFile) {
                Files.copy(Paths.get(file),
                        (new File(directory +"/"+ Paths.get(file).getFileName().toString())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    /**
     * @return return List of HTML file
     * @throws IOException
     */
    public static void CopyMiscFolder() throws IOException {
        try {
            List<String> listOfScreenshotFile = getLogos();
            Path targetDirectory = Paths.get(configFilePath+"/Misc");
            File directory = new File(String.valueOf(targetDirectory));
            if(!directory.exists()) {
                directory.mkdir();
            }
            for(String file : listOfScreenshotFile) {
                Files.copy(Paths.get(file),
                        (new File(directory +"/"+ Paths.get(file).getFileName().toString())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                break; //Only single
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    /**
     * Create Summery File for parallel test
     */
    public static void createResultFileFromSummaryTemplate(String platform, String category, String environment) {

        try {
            File result = new File(configFilePath + "/" + PropertyUtility.getResultConfigProperties("MERGED_SUMMARY_FILE"));
            BufferedReader br = new BufferedReader(new InputStreamReader(ReportGeneratorUtility.class.getResourceAsStream("/" + "Templates/summarytemplate.html")));
            String s;
            String totalStr = "";
            String listString = String.join("", summaryData);

            //if start time is null due to any reason then set it to current time
            if (startTime == null) {
                startTime = new Date();
                endTime = new Date();
            }
            while ((s = br.readLine()) != null) {
                totalStr += s;
            }
            totalStr = totalStr.replaceAll("<!--LOGO.PATH-->", logoFilePath);
            totalStr = totalStr.replaceAll("<!--PLATFORM-->",  platform.toUpperCase());
            totalStr = totalStr.replaceAll("<!--SUMARRY-->", listString);
            int total = passCount+failCount+inConclusiveCount+skippedCount;
            totalStr = totalStr.replaceAll("<!--TOTAL.COUNT-->",  total + "");
            totalStr = totalStr.replaceAll("<!--PASSED.COUNT-->", passCount + "");
            totalStr = totalStr.replaceAll("<!--FAILED.COUNT-->", failCount + "");
            totalStr = totalStr.replaceAll("<!--INCONCLUSIVE.COUNT-->", inConclusiveCount + "");
            totalStr = totalStr.replaceAll("<!--SKIPPED.COUNT-->", skippedCount + "");

            totalStr = totalStr.replaceAll("<!--START.TIME-->", startTime + "");
            totalStr = totalStr.replaceAll("<!--END.TIME-->", endTime + "");
            totalStr = totalStr.replaceAll("<!--TOTAL.TIME-->", calculateDuration(endTime,startTime) + "");
            totalStr = totalStr.replaceAll("<!--CATEGORY-->", (category==null)?"":category.toUpperCase());
            totalStr = totalStr.replaceAll("<!--ENVIRONMENT-->", (environment==null)?"": environment.toUpperCase());
            totalStr = totalStr.replaceAll("<!--LINK.TO.FAILURE-->", (failCount==0)? "":  "<a href='./failureSummary.html'> Link to Failure Test Summary Report</a>");

            FileWriter fw = new FileWriter(result);
            fw.write(totalStr);
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * Create Summery File for parallel test
     */
    public static void createCountTemplate(String platform, String category, String environment) {

        try {

            File result = new File(configFilePath + "/" + PropertyUtility.getResultConfigProperties("MERGED_COUNT_FILE"));
            BufferedReader br = new BufferedReader(new InputStreamReader(ReportGeneratorUtility.class.getResourceAsStream("/" + "Templates/resultcounttemplate.html")));
            String s;
            String totalStr = "";
            String listString = String.join("", summaryData);

            //if start time is null due to any reason then set it to current time
            if (startTime == null) {
                startTime = new Date();
                endTime = new Date();
            }
            while ((s = br.readLine()) != null) {
                totalStr += s;
            }
           // totalStr = totalStr.replaceAll("<!--LOGO.PATH-->", logoFilePath);
          //  totalStr = totalStr.replaceAll("<!--PLATFORM-->",  platform.toUpperCase());
           // totalStr = totalStr.replaceAll("<!--SUMARRY-->", listString);
            int total = passCount+failCount+inConclusiveCount+skippedCount;
            totalStr = totalStr.replaceAll("<!--TOTAL.COUNT-->",  total + "");
            totalStr = totalStr.replaceAll("<!--PASSED.COUNT-->", passCount + "");
            totalStr = totalStr.replaceAll("<!--FAILED.COUNT-->", failCount + "");
            totalStr = totalStr.replaceAll("<!--INCONCLUSIVE.COUNT-->", inConclusiveCount + "");
            totalStr = totalStr.replaceAll("<!--SKIPPED.COUNT-->", skippedCount + "");
            totalStr = totalStr.replaceAll("<!--START.TIME-->", startTime + "");
            totalStr = totalStr.replaceAll("<!--END.TIME-->", endTime + "");
            totalStr = totalStr.replaceAll("<!--TOTAL.TIME-->", calculateDuration(endTime,startTime) + "");
           // totalStr = totalStr.replaceAll("<!--CATEGORY-->", (category==null)?"":category.toUpperCase());
          //  totalStr = totalStr.replaceAll("<!--ENVIRONMENT-->", (environment==null)?"": environment.toUpperCase());
           // totalStr = totalStr.replaceAll("<!--LINK.TO.FAILURE-->", (failCount==0)? "":  "<a href='./failureSummary.html'> Link to Failure Test Summary Report</a>");

            FileWriter fw = new FileWriter(result);
            fw.write(totalStr);
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * Create Failed Summery File for parallel test
     */
    public static void createResultFileFromFailedSummaryTemplate(String platform, String category, String environment) {

        try {
            File result = new File(configFilePath + "/" + PropertyUtility.getResultConfigProperties("MERGED_FAILEDSUMMARY_FILE"));
            BufferedReader br = new BufferedReader(new InputStreamReader(ReportGeneratorUtility.class.getResourceAsStream("/" + "Templates/failuresummarytemplate.html")));
            String s;
            String totalStr = "";
            String listString = String.join("", failureSummaryData);
            listString = listString.replaceAll("<tr></tr>","");
            //if start time is null due to any reason then set it to current time
            if (startTime == null) {
                startTime = new Date();
                endTime = new Date();
            }

            while ((s = br.readLine()) != null) {
                totalStr += s;
            }
            totalStr = totalStr.replaceAll("<!--LOGO.PATH-->", logoFilePath);
            totalStr = totalStr.replaceAll("<!--PLATFORM-->",  platform.toUpperCase());
            totalStr = totalStr.replace("<!--FAILURE.SUMMARY-->", listString);
            totalStr = totalStr.replaceAll("<!--PASSED.COUNT-->", passCount + "");
            totalStr = totalStr.replaceAll("<!--FAILED.COUNT-->", failCount + "");
            totalStr = totalStr.replaceAll("<!--INCONCLUSIVE.COUNT-->", inConclusiveCount + "");
            totalStr = totalStr.replaceAll("<!--SKIPPED.COUNT-->", skippedCount + "");

            totalStr = totalStr.replaceAll("<!--START.TIME-->", startTime + "");
            totalStr = totalStr.replaceAll("<!--END.TIME-->", endTime + "");
            totalStr = totalStr.replaceAll("<!--TOTAL.TIME-->", calculateDuration(endTime,startTime) + "");
            totalStr = totalStr.replaceAll("<!--CATEGORY-->", (category==null)?"":category.toUpperCase());
            totalStr = totalStr.replaceAll("<!--ENVIRONMENT-->", (environment==null)?"": environment.toUpperCase());

            FileWriter fw = new FileWriter(result);
            fw.write(totalStr);
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * @param d2 End time
     * @param d1 Start time
     * @return difference inbetween time as string
     */
    private static String calculateDuration(Date d2, Date d1) {
        long diff = d2.getTime() - d1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        try {
            String diffTime = cal(String.valueOf(diffHours)) + ":" + cal(String.valueOf(diffMinutes)) + ":"
                    + cal(String.valueOf(diffSeconds));
            return diffTime;
        } catch (Exception e) {
            System.out.println("Error in calculating duration in HTML report"+ e);
            return "";
        }

    }
    /**
     * @param time current  time as string
     * @return Append time with two characters in return
     */
    private static String cal(String time) {
        while (time.length() != 2)
            time = "0" + time;
        return time;
    }
}
