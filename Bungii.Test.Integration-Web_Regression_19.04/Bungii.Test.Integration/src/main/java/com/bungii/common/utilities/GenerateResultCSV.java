package com.bungii.common.utilities;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

public class GenerateResultCSV {
    static Path configFilePath;
    private static ArrayList<String> summaryData = new ArrayList<>();
    private static int passCount = 0, failCount = 0, inConclusiveCount = 0;
    private static String logoFilePath = "";
    private static Date startTime, endTime;

    public static void GenerateCSV(String result) throws IOException, ParseException {
        try {

                String mainFolder = result;
                configFilePath = Paths.get(mainFolder);
                List<String> listOfResultFile = getListOfResultFile();
                int testCount = 1;
                //Iterate over all HTML file
                for (String path : listOfResultFile) {

                    File in = new File(path);

                    String subFolder = in.getParentFile().getName();

                    Element table;
                    Elements rows;
                    Document doc = Jsoup.parse(in, null);
                    try {
                         table = doc.select("table").get(0); //select the first table.
                         rows = table.select("tr");

                    for (int i = 1 + 1; i < rows.size(); i++) {
                        Element row = rows.get(i);
                        Elements cols = row.select("td");
                        String data = cols.toString();
                        data = data.replace(cols.get(2).toString(), "").replace(cols.get(3).toString(), "").replace(cols.get(4).toString(), "").replace(cols.get(5).toString(), "");
                        data = data.replace("\n<td style=\"background-color:pink;\">",",");
                        data = data.replace("\n<td style=\"background-color:MediumSeaGreen;\">",",");
                        data = data.replace("<td cursor:'pointer;'>","");
                        data = data.replace("</td>","");
                        data = data.replace("<td>","");
                        data = data.replace("<b>","");
                        data = data.replace("</b>","");
                        data = data.replace("\n\n\n","");
                        data = data.replace("<td cursor:'pointer;' style=\"text-align:left;\">","");
                        summaryData.add(data);
                    }
                    } catch(Exception ex){}

                }
                addDataToCSV();

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

    public static void addDataToCSV()
    {

        String listString = String.join("", summaryData);
        try {
            File resultCSV = new File(configFilePath + "/" + PropertyUtility.getResultConfigProperties("MERGED_CSV_FILE"));
            FileWriter outputfile = new FileWriter(resultCSV);
            outputfile.write("Test Scenario, Status "+ new DateTime().toString("dd-MM-yyyy")+"\n");
            outputfile.write(listString);
            outputfile.close();
            System.out.println("Generated CSV File");

        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
