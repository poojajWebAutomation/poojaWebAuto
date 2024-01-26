package com.bungii.common.utilities;



import com.bungii.common.core.DriverBase;
import com.bungii.common.enums.ResultType;
import com.bungii.common.manager.CucumberContextManager;
import static com.bungii.common.manager.ResultManager.fail;
import static com.bungii.common.manager.ResultManager.failureStep;

import com.bungii.common.manager.ResultManager;
import com.google.common.collect.ObjectArrays;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;


/**
 * @author vishal.bagi
 *Write test result to HTML file
 */

public class ReportGeneratorUtility extends DriverBase {
	//TODO create new summary file
	private String detailsFolderPath,/*detailFilepath,*/summaryPath,screenshotFolder,miscFolder,logFolder;
	private Writer bufWriter1, fileWriter;
	private ArrayList<String> detailsArray = new ArrayList<String>();
	private ArrayList<String> summaryArray = new ArrayList<String>();
	private ArrayList<String> stackTraceArray = new ArrayList<String>();
	ArrayList<String> failureArray = new ArrayList<String>();


	private final static String SUMMARY_TITLE="TEST SUMMARY REPORT";
	private final static String logoPath =PropertyUtility.getResultConfigProperties("MISC_DIRECTORY")+"/"+PropertyUtility.getResultConfigProperties("LOGO_FILENAME");
	private final static String downloadPath =PropertyUtility.getResultConfigProperties("MISC_DIRECTORY")+"/"+PropertyUtility.getResultConfigProperties("DOWNLOAD_LOGO");

	private final static String passPath =PropertyUtility.getResultConfigProperties("MISC_DIRECTORY")+"/"+PropertyUtility.getResultConfigProperties("PASS_lOGO");
	private final static String failPath =PropertyUtility.getResultConfigProperties("MISC_DIRECTORY")+"/"+PropertyUtility.getResultConfigProperties("FAIL_lOGO");

	private static LogUtility logger = new LogUtility(ReportGeneratorUtility.class);
	private int testCases = 1;
	private int failed = 0;
	private int passed = 0;
	private int inconclusive=0;
	private int skipped =0;
	private int testStepCount=0;
	private Date  testFinish, startTime = null;

	private Date testStepStart, testStepEnd;
	private String tcName;
	private String featureName;
    private String reason;
	private String tags;
	private boolean isTcVerifyFailed;

	public ReportGeneratorUtility(String detailsFolderPath, String screenshotFolder, String miscFolder, String logFolder){
		this.detailsFolderPath=detailsFolderPath;
		this.screenshotFolder=screenshotFolder;
		this.miscFolder=miscFolder;
		this.logFolder=logFolder;
	}


	public void startSuiteFile() throws Exception {
		//startDetailsFile(this.detailFilepath, SUMMARY_TITLE);
	}
	


	
	/**
	 * Method that will be called at end of Test Suite
	 */
	public void endSuiteFile() {
		//logDetails(summaryArray);
		//endSuiteSummary();
		//testCaseHeaderTemplate();
		//logDetails(detailsArray);
		//finishDetailsFile();
		try{
		createResultFileFromTemplate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public void createResultFileFromTemplate(){
	    try {

			File result= new File(detailsFolderPath+this.featureName.replace(".feature","").replace(" ","")+".html"); //PropertyUtility.getResultConfigProperties("SUMMARY_FILE"));
			BufferedReader br =new BufferedReader(new InputStreamReader(ReportGeneratorUtility.class.getResourceAsStream("/" + "Templates/resulttemplate.html")));
	    String s;
	    String totalStr = "";
	
			while ((s = br.readLine()) != null) {
			    totalStr += s;	        
			}
	        totalStr = totalStr.replaceAll("<!--LOGO.PATH-->",logoPath);
			String session = (String) cucumberContextManager.getScenarioContext("SESSION");
			if(session =="")
				totalStr = totalStr.replaceAll("<!--FEATURE.NAME-->",this.featureName);
            else
			totalStr = totalStr.replaceAll("<!--FEATURE.NAME-->",this.featureName+" | Session ID : "+session);
	        totalStr = totalStr.replaceAll("<!--SUMARRY-->", Matcher.quoteReplacement(getLogDetails(summaryArray)));
			totalStr = totalStr.replaceAll("<!--DETAILS-->", Matcher.quoteReplacement(getLogDetails(detailsArray)));
	        totalStr = totalStr.replaceAll("<!--PASSED.COUNT-->",passed+"");
	        totalStr = totalStr.replaceAll("<!--FAILED.COUNT-->",failed+"");
	        totalStr = totalStr.replaceAll("<!--INCONCLUSIVE.COUNT-->",inconclusive+"");
			totalStr = totalStr.replaceAll("<!--SKIPPED.COUNT-->",skipped+"");
			totalStr = totalStr.replaceAll("<!--FAILURE.DETAILS-->",Matcher.quoteReplacement(getLogFailureData(failureArray)));

	        FileWriter fw = new FileWriter(result);
	    fw.write(totalStr);
	    fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	/**
	 * Method that will be called before start of test case
	 * @param tcName Name of test case 
	 */
	public void startTestCase(String tcName , String featureName , String tags) {
		this.tcName = tcName.replace(",","");
		this.featureName = featureName;
		this.startTime = new Date();
		this.isTcVerifyFailed=false;
		this.testStepCount=0;
		this.reason="";
		this.tags= tags;
		addTestCaseEntryInDetailsTable(tcName, featureName);
		ThreadLocalStepDefinitionMatch.resetNumberOfSteps();
	}

	/**
	 * @param name Add Test case entry to details table
	 */
	public void addTestCaseEntryInDetailsTable(String name, String featureName) {
	    name= name.replace(",","");
		String str = "<tr class='header'><td colspan='5' align='left'>Scenario : "+ name +" - Tags : "+tags+"</td></tr>"; ;
		detailsArray.add(str);
		stackTraceArray.clear();
        this.reason="";
		logger.detail("SCENARIO : "+testCases+" OF FEATURE : "+ featureName);
	}

	/**
	 * Add test case step data to file buffer
	 * @param eventData Map that contains test details
	 */
	public void addTestData(Map<String, String> eventData) {

		testStepStart = testStepEnd == null ? startTime : testStepEnd;
		testStepEnd = new Date();
		int stepCount= testStepCount+1;
		String reason = screenDumpLink((String) eventData.get("actual"), eventData);
		String str = "<tr><td + rightSpan + >" + stepCount + "</td>";
		str = str + "<td align='left'>" + eventData.get("name").toString() + "</td>";
		if (eventData.get("type").toString() == "PASSED") {
			str = str + "<td style='background-color:MediumSeaGreen;'>" + eventData.get("type").toString() + "</td>";
		}
		else if (eventData.get("type").toString() == "WARNING") {
			str = str + "<td style='background-color:orange;'>" + eventData.get("type").toString() + "</td>";
		}
		else {
			str = str + "<td style='background-color:pink;'>" + eventData.get("type").toString() + "</td>";
		}

		str = str + "<td align='left'>" + eventData.get("expected").toString() + "</td>";
		str = str + "<td align='left'>" + reason + "</td>";
		str = str + "</tr>";

		detailsArray.add(str);
		if (eventData.get("type").toString() != "PASSED") {
			detailsArray.addAll(stackTraceArray);
            this.reason = reason;
			stackTraceArray.clear();
		}
		//increase step count ;
		testStepCount++;
	}
	/**
	 * Add test case step data to file buffer
	 * @param eventData Map that contains test details
	 */
	public void addStackTrace(Map<String, String> eventData) {
   if(eventData.get("actual").toString()!= "") {
       String str = "<tr><td + rightSpan + ></td>";
       str = str + "<td colspan=8 align='left'>Error Log : <div class='maincontent'><div class='content'>" + eventData.get("actual").toString() + "</div><div class='txtcol'><a>Show More</a></div></div></td>";
       stackTraceArray.add(str);
   }
   else {
       stackTraceArray.add("");
         }
	}

	/**
	 * @param message Message that is to be updated
	 * @param eventData Map in which screendump key is to be search
	 * @return String with link and message
	 */
	private String screenDumpLink(String message, Map<String, String> eventData) {
		String sDumpFile;
		try {
			sDumpFile = (String) (eventData.get("screenDump"));

		} catch (Exception e) {

			return message;
		}

		if (sDumpFile == null) {
			// return null;
			return message;
		}

		String path = sDumpFile.replace('\\', '/');
		logger.detail("Screenshot Path :  "+ path );
		return message +" <div style='color:red; font-weight: bold'> " +
				" <img src='./"+downloadPath+"' alt='' onclick=\"showImage('"+path+"')\"/> Download screenshot here" +
				"</div><div><a href='" + path+"'> Link </a></div>";
		//return "<a href='" + sDumpFile.replace("\\", "/") + "'>" + message + "</a>";
	}

	/**
	 * @param isFailed boolean flag set by testng assert statements
	 */
	public void endTestCase(boolean isFailed, boolean isSkipped) {
		String str1;
		//String str2;
		testCases++;
		testFinish = new Date();
		String str = "";
		String status = "";
		if(isSkipped)
		{
			skipped++;
			status = "<td style='background-color:skyblue;'>Skipped</td>";
			str = "<td>" + ThreadLocalStepDefinitionMatch.getNumberOfSteps() + "</td>" + "<td>" + this.startTime
					+ "</td><td>" + this.testFinish + "</td><td>" + calculateDuration(this.testFinish, this.startTime);
			str1 = "<td cursor:'pointer;' style='text-align:left;'>" + tcName + "</td>" + status + str;
			summaryArray.add(str1);
		}
		else {
			//check testng assert and local flag as well
			if (!isFailed && !isTcVerifyFailed) {
				status = "<td style='background-color:MediumSeaGreen;'>Pass</td>";
				passed++;
			} else {
				try {
					if (this.reason.equalsIgnoreCase("")) {
						String cause = (String) cucumberContextManager.getScenarioContext("ERROR");
						String step = (String) cucumberContextManager.getScenarioContext("STEP");
						this.reason = cause;
						if (cause != "")
							failureStep(step, "Step Should be successful", (String) cucumberContextManager.getScenarioContext("ERROR"), true);
					}
				} catch (Exception ex) {
				}
				String reason = this.reason;
				//"<tr><td + rightspan+ ><td colspan='7' style='text-align: left;'>"+reason+"</td></tr><tr>":"<tr>";
				String st = "<td + rightspan+ ><td colspan='7' style='text-align: left;'>Note: Some steps are skipped/Not passed due to above error. Please refer to logs for more details</td>";
				if (reason == "") {
					CucumberContextManager.getObject().setScenarioContext("FAILURE", "TRUE");
					status = "<td style='background-color:skyblue;'>Inconclusive</td>";
				} else if (((String) CucumberContextManager.getObject().getScenarioContext("PASS_WITH_OBSERVATIONS")).equals("TRUE")) {
					passed++;
					status = "<td style='background-color:orange;'>Observations</td>";
				} else {
					failed++;
					status = "<td style='background-color:pink;'>Fail</td>";
				}
				detailsArray.add(st);
				String str2 = "<td>*</td><td align='left'>" + tcName + "</td>" + status + "<td align='left'>" + reason + "</td>";
				failureArray.add(str2);
				failureArray.addAll(stackTraceArray);

			}

			str = "<td>" + ThreadLocalStepDefinitionMatch.getNumberOfSteps() + "</td>" + "<td>" + this.startTime
					+ "</td><td>" + this.testFinish + "</td><td>" + calculateDuration(this.testFinish, this.startTime);
/*		str = "<td>" + this.testStepCount + "</td>" + "<td>" + this.startTime
				+ "</td><td>" + this.testFinish + "</td><td>" + calculateDuration(this.testFinish, this.startTime);*/

			str1 = "<td cursor:'pointer;' style='text-align:left;'>" + tcName + "</td>" + status + str;


			summaryArray.add(str1);
			//int totalExecuted = passed + failed + inconclusive;
			//logger.detail("FEATURE EXECUTION STATUS : PASS: "+ passed +" | FAIL: "+ failed + " | INCONCLUSIVE:"+inconclusive+" | TOTAL EXECUTED : " + totalExecuted);
		}
	}

	public void getFeatureExecutionStatus()
	{
		int totalExecuted = passed + failed + inconclusive+ skipped;
		logger.detail("FEATURE EXECUTION STATUS : PASS: "+ passed +" | FAIL: "+ failed + " | INCONCLUSIVE:"+inconclusive+" | SKIPPED : " + skipped+" | TOTAL EXECUTED : " + totalExecuted);
	}

	/**
	 * Mark test case as failed.  Dont stop test, use in case of verify
	 */
	public void verificationFailed(Map<String, String> eventData){
		this.isTcVerifyFailed=true;
		endTestDataContainer(eventData);
		logger.trace("Marked test case :"+this.tcName +" failed as verification got failed"  );
	}
	
	/**
	 * @param d2 End time
	 * @param d1 Start time
	 * @return difference inbetween time as string
	 */
	private String calculateDuration(Date d2, Date d1) {
		long diff = d2.getTime() - d1.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		try {
			String diffTime = cal(String.valueOf(diffHours)) + ":" + cal(String.valueOf(diffMinutes)) + ":"
					+ cal(String.valueOf(diffSeconds));
			return diffTime;
		} catch (Exception e) {
			logger.handleError("Error in calculating duration in HTML report", e);
			return null;
		}

	}

	/**
	 * @param time current  time as string
	 * @return Append time with two characters in return
	 */
	private String cal(String time) {
		while (time.length() != 2)
			time = "0" + time;
		return time;
	}

	/**
	 * Close summary file
	 */
	private void closeDetailsFile() {
		try {
			bufWriter1.close();
		} catch (IOException e) {
			logger.handleError("Exception caught while closing details file ", e);
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				logger.handleError("Exception caught while closing details file ", e);
			} finally {
				fileWriter = bufWriter1 = null;
			}
		}
	}

	/**
	 * Write input string to output stream
	 * @param lines String that is to be written
	 */
	private void writeDetails(String lines) {
		try {
			bufWriter1.write(lines);
		} catch (IOException e) {
			logger.handleError("Exception caught while writing details in HTML : ", e);
		}
	}
	public boolean isScenarioFailed(){
		return this.isTcVerifyFailed;
	}

	public int inconclusive(){
		inconclusive++;

		return inconclusive;
	}
	public int skipped(){
		return skipped;
	}
	private String getLogDetails(ArrayList<String> strArray) {
		String strDetails = "";
		for (String str : strArray)
			strDetails+="<tr>" + str + "</tr>";
		final String cleansedString = StringUtils.normalizeSpace(strDetails);
		//logger.detail("Generated Report : "+cleansedString);
		return strDetails;
	}
	private String getLogFailureData(ArrayList<String> strArray) {
		String strDetails = "";
		for (String str : strArray)
			strDetails+="<tr class='header'>" + str + "</tr>";
		final String cleansedString = StringUtils.normalizeSpace(strDetails);
		//logger.detail("Generated Report : "+cleansedString);
		return strDetails;
	}

	public void endTestDataContainer(Map<String, String> eventData)
	{
		//String str = "<tr><td + rightspan+ ><td colspan='7' style='text-align: left;'>Note: Some steps are skipped due to above error.</td>";
		//str = str + "<td style='background-color:pink;'> " + eventData.get("type").toString() + "</td>";

	//	str = str + "<td>" + eventData.get("expected").toString() + "</td>";
	//	str = str + "<td>" + screenDumpLink((String) eventData.get("actual"), eventData) + "</td>";

		//detailsArray.add(str);

	}


}
