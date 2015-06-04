package isu.common;

import static org.junit.Assert.*;
import isu.data.CommonAutomatedData;
import isu.data.EmergencyAutomatedData;
import isu.data.UnderGradAutomatedData;
import isu.util.DBUtil;
import isu.util.PropertiesUtil;
import isu.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class EmergencyChange {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	Character[] invalidChars = { '#', '!', '$', '@', '%', '^', '&' };

	private static List<EmergencyAutomatedData> dataList = new ArrayList<EmergencyAutomatedData>();
	private static String acadCareer = null;
	private static String stdntCareerNbr = null;
	private static int identifier = 0;
	private static boolean isError = false;
	String URL1;
	PropertiesUtil propUtil = new PropertiesUtil();
	InputStream input = propUtil.getPropertyFile("url.properties");
	private StringBuilder log = new StringBuilder();
	private static int counter=1;
	  ExecutorService executor = Executors.newSingleThreadExecutor();
      Future<String> future = executor.submit(new Task());
	
	private static boolean isFirsttime=true;
	@BeforeTest
	public void beforeTest() {
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		System.setProperty("webdriver.firefox.bin",
				"Q://LEAP_Test_Cases//Automation//Tools//Mozilla Firefox//firefox.exe");
		profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");
		profile.setPreference("capability.policy.default.Window.frameElement.get", "allAccess");
		driver = new FirefoxDriver(profile);
		baseUrl = "https://account-test.illinoisstate.edu/selfservice/registration/begin";
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@Test
	public void EmergencyTest() {

		try {
			String[] workBookNames = { "Sheet1" };
			Util util = new Util();
			// List<EmergencyAutomatedData> dataList = new
			// ArrayList<EmergencyAutomatedData>();
			dataList = util.readFile("Q://LEAP_Test_Cases//Automation//Data", "EmergencyAutomationData.xls",
					workBookNames, true, false);
			// dataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data",
			// "AutomationData.xls", workBookNames,false,true);

			driver.get("https://sis.illinoisstate.edu/psp/PISUVJ/EMPLOYEE/HRMS/?cmd=logout");
			assertEquals("Oracle | PeopleSoft Enterprise Sign-in", driver.getTitle());
			driver.findElement(By.id("userid")).clear();
			driver.findElement(By.id("userid")).sendKeys("GJBARRE");
			driver.findElement(By.id("pwd")).clear();
			driver.findElement(By.id("pwd")).sendKeys("Zyx1234567");
			driver.findElement(By.name("Submit")).click();
			//assertEquals("Employee-facing registry content", driver.getTitle());
			 driver.findElement(By.id("pthnavbca_MYFAVORITES")).click();
			 driver.findElement(By.cssSelector("#crefli_fav_HC_ACAD_HISTORY_ADMA_GBL > a")).click();
			   
			/*driver.findElement(By.id("pthnavbca_PORTAL_ROOT_OBJECT")).click();
			driver.findElement(By.id("fldra_HCSR_RECORDS_AND_REGISTRATION")).click();
			driver.findElement(By.id("fldra_HCSR_TRANSFER_CREDIT_EVAL")).click();
			driver.findElement(By.linkText("External Education")).click();*/
			// ERROR: Caught exception [ERROR: Unsupported command
			// [waitForPopUp | _parent | 30000]]
			//assertEquals("External Education", driver.getTitle());
			// ERROR: Caught exception [ERROR: Unsupported command
			// [selectFrame | TargetContent | ]]
			driver.switchTo().frame("TargetContent");
			
			log.append(">>>>>>>>>>>>>>>>>>>>>>Total rows " + dataList.size());
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows " + dataList.size());
			for (int j = 0; j < dataList.size(); j++) {

				
				Thread.sleep(500);
				try{
				driver.findElement(By.id("ADM_APPL_SCTY_EMPLID")).clear();
				driver.findElement(By.id("ADM_APPL_SCTY_EMPLID")).sendKeys(dataList.get(j).getEMPLID());
				future.get(3, TimeUnit.SECONDS);
				}
				catch(Exception e){
					driver.findElement(By.id("ADM_APPL_SCTY_EMPLID")).clear();
					driver.findElement(By.id("ADM_APPL_SCTY_EMPLID")).sendKeys(dataList.get(j).getEMPLID());
					future.get(3, TimeUnit.SECONDS);
				}
				Thread.sleep(500);
				log.append("\n Starting with EMPLID " + dataList.get(j).getEMPLID());
				System.out.println(" Starting with EMPLID " + dataList.get(j).getEMPLID());
				driver.findElement(By.id("#ICSearch")).click();
				Thread.sleep(1500);
				if(driver.getPageSource().contains("No matching values were found."))
				{
					break;
				}
				
				else if(driver.getPageSource().contains("Search Results")){
					if(isFirsttime){
						if(driver.findElements(By.id("RESULT2$0")).size() != 0){
					driver.findElement(By.id("RESULT2$0")).click();
					isFirsttime=false;
					}
					}
					else if(!isFirsttime)
					{
						if(driver.findElements(By.id("RESULT2$1")).size() != 0)
						driver.findElement(By.id("RESULT2$1")).click();
					}
				}
				
				log.append("\n Name " + dataList.get(j).getName());
				System.out.println(" Name " + dataList.get(j).getName());
				// System.out.println(driver.findElement(By.xpath("//*[@id=\"$ICField$11$$hviewall$0\"]/descendant::span[text()='1 of']")));

				
				try{
					
				//check for ext org ID's
					
				driver.findElement(By.id("$ICField$11$$hviewall$0")).click();
				future.get(5, TimeUnit.SECONDS);
				Thread.sleep(500);
				for (int i = 1; i < 15; i++) {

					if ((driver.findElement(By.xpath("//input[@id='ACAD_HISTORY_EXT_ORG_ID$" + i + "']"))
							.getAttribute("value").equals(dataList.get(j).getExtOrgID())) && !(driver.findElement(By.xpath("//input[@id='ACAD_HISTORY_EXT_ORG_ID$" + i + "']"))
									.getAttribute("value").equals("")) ) {
						identifier = i;
						/*Select select = new Select(driver.findElement(By
								.xpath("//Select[@id='EXT_ACAD_DATA_EXT_CAREER$" + identifier + "']")));
						System.out.println("Updating Date for " + select.getAllSelectedOptions());*/
						log.append(" Date before updating "
								+ driver.findElement(
										By.xpath("//input[@id='EXT_ACAD_DATA_TO_DT$" + identifier + "']"))
										.getAttribute("value"));
						System.out.println(" Date before updating "
								+ driver.findElement(
										By.xpath("//input[@id='EXT_ACAD_DATA_TO_DT$" + identifier + "']"))
										.getAttribute("value"));
						break;
					}
					

				}
				
				//check for Data number 
				driver.findElement(By.xpath("//a[@id='$ICField$173$$hend$" + identifier + "' and text()='Last']")).click();
				
				Thread.sleep(500);
				driver.findElement(By.id("EXT_ACAD_DATA_TO_DT$" + identifier)).clear();
				driver.findElement(By.id("EXT_ACAD_DATA_TO_DT$" + identifier)).sendKeys(
						dataList.get(j).getHSGDate());
				Thread.sleep(1000);
				log.append("Date after Updating " + dataList.get(j).getHSGDate());
				System.out.println("Date after Updating " + dataList.get(j).getHSGDate());
				Thread.sleep(500);
				driver.findElement(By.id("#ICSave")).click();
				Thread.sleep(1000);
				log.append("\n Completed " + dataList.get(j).getEMPLID());
				System.out.println("Completed " + dataList.get(j).getEMPLID());
				
					
				
				}
				catch(Exception ex)
				{
					
					try{
						driver.findElement(By.xpath("//input[@id='ACAD_HISTORY_EXT_ORG_ID$" + identifier + "']"))
						.getAttribute("value").equals(dataList.get(j).getExtOrgID());
						future.get(3, TimeUnit.SECONDS);
						Thread.sleep(500);
						
						//driver.findElement(By.xpath("//a[@id='$ICField$173$$hend$" + identifier + "' and text()='Last']")).click();
						
					}
					catch(Exception t){
						try{
						identifier=0;
						driver.findElement(By.xpath("//a[@id='$ICField$173$$hend$" + identifier + "' and text()='Last']")).click();
						future.get(3, TimeUnit.SECONDS);
						Thread.sleep(500);
						}
						catch(Exception l){
							System.out.println("Check here");
						}
						
					}
						
					if ((driver.findElement(By.xpath("//input[@id='ACAD_HISTORY_EXT_ORG_ID$" + identifier + "']"))
							.getAttribute("value").equals(dataList.get(j).getExtOrgID())) && !(driver.findElement(By.xpath("//input[@id='ACAD_HISTORY_EXT_ORG_ID$" + identifier + "']"))
									.getAttribute("value").equals("")) ) {
						//identifier = 0;
						/*Select select = new Select(driver.findElement(By
								.xpath("//Select[@id='EXT_ACAD_DATA_EXT_CAREER$" + identifier + "']")));
						System.out.println("Updating Date for " + select.getAllSelectedOptions());*/
						
						
						//check for Data number 
						/*int introw=	Integer.parseInt(dataList.get(j).getRowNumber());
						try {
							driver.findElement(By.xpath("//a[@id='$ICField$173$$hend$" + identifier + "' and text()='Last']")).click();
							log.append("\n Date before updating "
									+ driver.findElement(
											By.xpath("//input[@id='EXT_ACAD_DATA_TO_DT$" + identifier + "']"))
											.getAttribute("value"));
							System.out.println(" Date before updating "
									+ driver.findElement(
											By.xpath("//input[@id='EXT_ACAD_DATA_TO_DT$" + identifier + "']"))
											.getAttribute("value"));
							driver.findElement(By.id("EXT_ACAD_DATA_TO_DT$" + identifier + "")).clear();
							driver.findElement(By.id("EXT_ACAD_DATA_TO_DT$" + identifier + "")).sendKeys(
									dataList.get(j).getHSGDate());
							
							log.append("\n Date after Updating " + dataList.get(j).getHSGDate());
							System.out.println("Date after Updating " + dataList.get(j).getHSGDate());
							driver.findElement(By.id("#ICSave")).click();
							Thread.sleep(1000);
							
							log.append("\n Completed " + dataList.get(j).getEMPLID());
							System.out.println("Completed " + dataList.get(j).getEMPLID());
							}
						catch(Exception e){
							
							*/
						try{
						driver.findElement(By.xpath("//a[@id='$ICField$173$$hend$" + identifier + "' and text()='Last']")).click();
						future.get(3, TimeUnit.SECONDS);
						Thread.sleep(500);
						}
						catch(Exception b){
							
						}
							log.append("\n Date before updating "
									+ driver.findElement(
											By.xpath("//input[@id='EXT_ACAD_DATA_TO_DT$" + identifier + "']"))
											.getAttribute("value"));
							
							System.out.println(" Date before updating "
									+ driver.findElement(
											By.xpath("//input[@id='EXT_ACAD_DATA_TO_DT$" + identifier + "']"))
											.getAttribute("value"));
							driver.findElement(By.id("EXT_ACAD_DATA_TO_DT$" + identifier + "")).clear();
							driver.findElement(By.id("EXT_ACAD_DATA_TO_DT$" + identifier + "")).sendKeys(
									dataList.get(j).getHSGDate());
							Thread.sleep(1000);
							log.append("\n Date after Updating " + dataList.get(j).getHSGDate());
							System.out.println("Date after Updating " + dataList.get(j).getHSGDate());
							Thread.sleep(500);
							driver.findElement(By.id("#ICSave")).click();
							Thread.sleep(1000);
							
							log.append("\n Completed " + dataList.get(j).getEMPLID());
							System.out.println("Completed " + dataList.get(j).getEMPLID());
						//}
						
						
					}
					
				}
				
				
				
				
				/*try {
					for (int i = 0; i < 10; i++) {

						if ((driver.findElement(By.xpath("//input[@id='ACAD_HISTORY_EXT_ORG_ID$" + i + "']"))
								.getAttribute("value").equals(dataList.get(j).getExtOrgID())) && !(driver.findElement(By.xpath("//input[@id='ACAD_HISTORY_EXT_ORG_ID$" + i + "']"))
										.getAttribute("value").equals("")) ) {
							identifier = i;
							Select select = new Select(driver.findElement(By
									.xpath("//Select[@id='EXT_ACAD_DATA_EXT_CAREER$" + identifier + "']")));
							System.out.println("Updating Date for " + select.getAllSelectedOptions());
							System.out.println(" Date before updating "
									+ driver.findElement(
											By.xpath("//input[@id='EXT_ACAD_DATA_TO_DT$" + identifier + "']"))
											.getAttribute("value"));
							break;
						}

					}
				} catch (Exception ex) {
					System.err.println("Skipped " + dataList.get(j).getEMPLID()+ " ERROR "+ex.getMessage());
					isError = true;
					break;
				}*/

				/*if (!isError) {
					if( dataList.get(j).getRowNumber()=="1"){
					driver.findElement(By.id("EXT_ACAD_DATA_TO_DT$" + identifier)).clear();
					driver.findElement(By.id("EXT_ACAD_DATA_TO_DT$" + identifier)).sendKeys(
							dataList.get(j).getHSGDate());
					System.out.println("Date after Updating " + dataList.get(j).getHSGDate());
					driver.findElement(By.id("#ICSave")).click();
					Thread.sleep(1000);
					System.out.println("Completed " + dataList.get(j).getEMPLID());
					}
					else{
						//driver.findElement(By.xpath("//a[@id='']")).click();
						System.out.println(dataList.get(j).getRowNumber());
					}
				}*/
				driver.findElement(By.id("#ICList")).click();
				Thread.sleep(500);
				//driver.findElement(By.id("#ICClear")).click();
				
				// ERROR: Caught exception [ERROR: Unsupported command
				// [selectWindow | null | ]]
				//driver.switchTo().frame("null");
				//driver.findElement(By.id("pthnavbccrefanc_HC_ACAD_HISTORY_PERS_GBL1")).click();
				// ERROR: Caught exception [ERROR: Unsupported command
				// [waitForPopUp | _self | 30000]]
				//assertEquals("External Education", driver.getTitle());
				counter++;
			}
			System.out.println(log);
		} catch (IOException io) {
			
			log.append("\n Error occured while reading the excel sheet for creating account " + io.getMessage());
			System.err.println("Error occured while reading the excel sheet for creating account " + io.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.append("\n Error " + e.getMessage());
			System.err.println("Error ");
		}
	}

	@AfterTest
	public void afterTest() throws InterruptedException, IOException {
		
		System.out.println(log);
		Thread.sleep(1000);
		// revert the password
		driver.quit();
	}

	class Task implements Callable<String> {
	    @Override
	    public String call() throws Exception {
	        Thread.sleep(5000); // Just to demo a long running task of 4 seconds.
	        return "Dummy Timer!";
	    }}	
}
