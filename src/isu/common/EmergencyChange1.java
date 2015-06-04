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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class EmergencyChange1 {

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
	private static boolean isFirsttime=true;
	@BeforeTest
	public void beforeTest() {
		/*ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		System.setProperty("webdriver.firefox.bin",
				"Q://LEAP_Test_Cases//Automation//Tools//Mozilla Firefox//firefox.exe");
		profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");
		profile.setPreference("capability.policy.default.Window.frameElement.get", "allAccess");*/
		driver = new FirefoxDriver();
		baseUrl = "https://account-test.illinoisstate.edu/selfservice/registration/begin";
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void EmergencyTest() {

		try {
			String[] workBookNames = { "Sheet1" };
			Util util = new Util();
			// List<EmergencyAutomatedData> dataList = new
			// ArrayList<EmergencyAutomatedData>();
			dataList = util.readFile("Q://LEAP_Test_Cases//Automation//Data", "EmergencyAutomationData1.xls",
					workBookNames, true, false);
			// dataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data",
			// "AutomationData.xls", workBookNames,false,true);

			driver.get("https://sis.illinoisstate.edu/psp/PISUVJ/EMPLOYEE/HRMS/?cmd=logout");
			assertEquals("Oracle | PeopleSoft Enterprise Sign-in", driver.getTitle());
			driver.findElement(By.id("userid")).clear();
			driver.findElement(By.id("userid")).sendKeys("GSHARMA");
			driver.findElement(By.id("pwd")).clear();
			driver.findElement(By.id("pwd")).sendKeys("June@2014");
			driver.findElement(By.name("Submit")).click();
	
			driver.findElement(By.id("pthnavbca_PORTAL_ROOT_OBJECT")).click();
			Thread.sleep(500);
		    driver.findElement(By.id("HCCC_BUILD_COMMUNITY")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("fldra_HCCC_PERSONAL_INFORMATION")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("fldra_HCCC_BIOGRAPHICAL")).click();
		    Thread.sleep(500);
		    driver.findElement(By.linkText("Names")).click();
		    Thread.sleep(500);
			driver.switchTo().frame("TargetContent");
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows " + dataList.size());
			for (int j = 0; j < dataList.size(); j++) {

				System.out.println("Started with EMPLID :"+dataList.get(j).getEMPLID());
					String text ="";
				    driver.findElement(By.id("PEOPLE_SRCH_EMPLID")).clear();
				    driver.findElement(By.id("PEOPLE_SRCH_EMPLID")).sendKeys(dataList.get(j).getEMPLID());
				    Thread.sleep(1000);
				    if(!driver.findElement(By.id("#ICCorrectHistory")).isSelected())
				    driver.findElement(By.id("#ICCorrectHistory")).click();
				    driver.findElement(By.id("#ICSearch")).click();
				    Thread.sleep(500);
				    driver.findElement(By.id("SCC_NAME_TYPE_LNK$0")).click();
				    Thread.sleep(500);
				    text = driver.findElement(By.id("DERIVED_SCC_NM_MIDDLE_NAME")).getAttribute("value").toString();
				    System.out.println(text);
				    if(text.equalsIgnoreCase("0"))
				    {
				    	JavascriptExecutor js = (JavascriptExecutor) driver;
				    	js.executeScript("document.getElementById('DERIVED_SCC_NM_MIDDLE_NAME').setAttribute('value', ' ')");
				    	text = driver.findElement(By.id("DERIVED_SCC_NM_MIDDLE_NAME")).getAttribute("value").toString();
				    	if(text.equalsIgnoreCase("0")){
				    		driver.findElement(By.id("DERIVED_SCC_NM_MIDDLE_NAME")).sendKeys(""+"\t");	
				    	}
				    //
				    Thread.sleep(1500);
				    driver.findElement(By.id("DERIVED_SCC_NM_SCC_ADDR_SUBMT_BTN")).click();
				    Thread.sleep(500);
				    driver.findElement(By.id("#ICSave")).click();
				    Thread.sleep(500);
				    }
				    driver.findElement(By.id("#ICList")).click();
				    Thread.sleep(500);
				    driver.findElement(By.id("#ICClear")).click();
				    Thread.sleep(500);
				    System.out.println("Completed EMPLID :"+dataList.get(j).getEMPLID());
			}
		} catch (IOException io) {
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error "+e.getMessage());
		}
	}

	@AfterTest
	public void afterTest() throws InterruptedException, IOException {
		
		Thread.sleep(1000);
		// revert the password
		driver.quit();
	}

		
}
