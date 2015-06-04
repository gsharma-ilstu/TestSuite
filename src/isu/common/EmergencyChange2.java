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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class EmergencyChange2 {

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

	private static String actvDate = "1/1/15";
	private static String prgcDate = "1/2/15";
	String tag = "";
	int ACTVindex = 0;
	int PRGCindex = 0;
	private static boolean isFirsttime = true;

	@BeforeTest
	public void beforeTest() {

		// ProfilesIni allProfiles = new ProfilesIni(); FirefoxProfile profile =
		// allProfiles.getProfile("default");
		// System.setProperty("webdriver.firefox.bin",
		// "Q://LEAP_Test_Cases//Automation//Tools//Mozilla Firefox//firefox.exe"
		// );
		// profile.setPreference("capability.policy.default.Window.QueryInterface"
		// , "allAccess");
		// profile.setPreference("capability.policy.default.Window.frameElement.get"
		// , "allAccess");

		// System.setProperty("webdriver.chrome.driver",
		// "C://Program Files (x86)//Google//Chrome//Application//Chrome.exe");
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();
		baseUrl = "www.google.com";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void EmergencyTest() {

		try {
			String[] workBookNames = { "Sheet1" };
			Util util = new Util();
			// List<EmergencyAutomatedData> dataList = new
			// ArrayList<EmergencyAutomatedData>();
			dataList = util.readFile("Q://LEAP_Test_Cases//Automation//Data", "EmergencyAutomationData2.xls",
					workBookNames, true, false);
			// dataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data",
			// "AutomationData.xls", workBookNames,false,true);

			driver.get("https://sis.illinoisstate.edu/psp/PISUVJ/EMPLOYEE/HRMS/?cmd=logout");
			Thread.sleep(1000);
			driver.findElement(By.id("userid")).clear();
			driver.findElement(By.id("userid")).sendKeys("GSHARMA");
			Thread.sleep(1000);
			driver.findElement(By.id("pwd")).clear();
			driver.findElement(By.id("pwd")).sendKeys("June@2014");
			Thread.sleep(1000);
			driver.findElement(By.name("Submit")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("pthnavbca_MYFAVORITES")).click();
			Thread.sleep(1000);
			driver.findElement(By.linkText("Student Program/Plan")).click();
			Thread.sleep(1000);
			driver.switchTo().frame("TargetContent");

			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows " + dataList.size());
			for (int j = 0; j < dataList.size(); j++) {

				System.out.println("Started with EMPLID :" + dataList.get(j).getEMPLID());
				driver.findElement(By.id("STDNT_CAR_SRCH_EMPLID")).clear();
				driver.findElement(By.id("STDNT_CAR_SRCH_EMPLID")).sendKeys(dataList.get(j).getEMPLID());
				Thread.sleep(1000);
				new Select(driver.findElement(By.id("STDNT_CAR_SRCH_ACAD_CAREER"))).selectByVisibleText(dataList.get(j)
						.getAcadCareer());
				Thread.sleep(1000);
				driver.findElement(By.id("STDNT_CAR_SRCH_STDNT_CAR_NBR")).clear();
				driver.findElement(By.id("STDNT_CAR_SRCH_STDNT_CAR_NBR")).sendKeys(dataList.get(j).getStdntCrNbr());
				Thread.sleep(1000);

				if (!driver.findElement(By.id("#ICCorrectHistory")).isSelected())
					driver.findElement(By.id("#ICCorrectHistory")).click();

				Thread.sleep(1000);
				driver.findElement(By.id("#ICSearch")).click();
				Thread.sleep(1000);

				if (driver.getPageSource().contains("Search Results")) {
					// driver.findElement(By.linkText(dataList.get(j).getAcadCareer())).click();
					// String rowToClick = dataList.get(j).getAcadProg();
					// driver.findElement(By.xpath("//a[starts-with()='" +
					// rowToClick + "'])")).click();
					// System.out.println("Clicked on " + rowToClick);
					if (driver.findElement(By.id("RESULT3$0")).getText().equals(dataList.get(j).getStdntCrNbr())) {
						driver.findElement(By.id("RESULT3$0")).click();
						Thread.sleep(1000);
					} else if (driver.findElement(By.id("RESULT3$1")).getText().equals(dataList.get(j).getStdntCrNbr())) {
						driver.findElement(By.id("RESULT3$1")).click();
						Thread.sleep(1000);
					}
				}

			if(!driver.getPageSource().contains("1 of 3"))
				{
					try {
					if (driver.findElement(By.id("SSR_ACDPRG_AUS$hviewall$0")).isEnabled()) {
						driver.findElement(By.id("SSR_ACDPRG_AUS$hviewall$0")).click();

						for (int i = 0; i < 3; i++)

						{
							tag = driver.findElement(By.id("ACAD_PROG_PROG_ACTION$" + i)).getAttribute("value");
							if (tag.equals("ACTV") && dataList.get(j).getActionReason().equals("ACTV")) {
								ACTVindex = i;
								driver.findElement(By.id("ACAD_PROG_EFFDT$" + ACTVindex)).clear();
								driver.findElement(By.id("ACAD_PROG_EFFDT$" + ACTVindex)).sendKeys("01/01/2015");
								System.out.println("Found ACTV");
								break;
							}
							if (tag.equals("PRGC") && dataList.get(j).getActionReason().equals("PRGC")) {
								PRGCindex = i;
								driver.findElement(By.id("ACAD_PROG_EFFDT$" + PRGCindex)).clear();
								driver.findElement(By.id("ACAD_PROG_EFFDT$" + PRGCindex)).sendKeys("01/02/2015");
								System.out.println("Found PRGC");
								break;
							}

						}
						// List<WebElement> rowItems = driver.findElements(By
						// .xpath("div[starts-with(@id,'win0divACAD_PROG_PROG_ACTION')]"));
						/*
						 * for (int i = 0; i < rowItems.size(); i++) { tag =
						 * rowItems.get(i).getAttribute("value");
						 * if(dataList.get(j).getActionReason().equals("ACTV"))
						 * { if (tag.equals("ACTV")) { ACTVindex = i;
						 * driver.findElement(By.id("ACAD_PROG_EFFDT$" +
						 * ACTVindex)).clear();
						 * driver.findElement(By.id("ACAD_PROG_EFFDT$" +
						 * ACTVindex)).sendKeys("01/01/2015");
						 * System.out.println("Found ACTV"); break; } }
						 * if(dataList.get(j).getActionReason().equals("PRGC"))
						 * { if (tag.equals("PRGC")) { PRGCindex = i;
						 * driver.findElement(By.id("ACAD_PROG_EFFDT$" +
						 * PRGCindex)).clear();
						 * driver.findElement(By.id("ACAD_PROG_EFFDT$" +
						 * PRGCindex)).sendKeys("01/02/2015");
						 * System.out.println("Found PRGC"); break; } }
						 */

					} else {
						if (dataList.get(j).getActionReason().equals("ACTV")) {
							driver.findElement(By.id("ACAD_PROG_EFFDT$0")).clear();
							driver.findElement(By.id("ACAD_PROG_EFFDT$0")).sendKeys("01/01/2015");
						} else if (dataList.get(j).getActionReason().equals("PRGC")) {
							driver.findElement(By.id("ACAD_PROG_EFFDT$0")).clear();
							driver.findElement(By.id("ACAD_PROG_EFFDT$0")).sendKeys("01/02/2015");
						}

					}
				} catch (Exception qa) {
					if (dataList.get(j).getActionReason().equals("ACTV")) {
						driver.findElement(By.id("ACAD_PROG_EFFDT$0")).clear();
						driver.findElement(By.id("ACAD_PROG_EFFDT$0")).sendKeys("01/01/2015");
					} else if (dataList.get(j).getActionReason().equals("PRGC")) {
						driver.findElement(By.id("ACAD_PROG_EFFDT$0")).clear();
						driver.findElement(By.id("ACAD_PROG_EFFDT$0")).sendKeys("01/02/2015");
					}
				}
				try {
					driver.findElement(By.id("#ICSave")).click();
					Thread.sleep(1000);
					
				} catch (Exception q) {
					driver.findElement(By.id("#ICList")).click();
					Thread.sleep(1000);
					driver.findElement(By.id("#ICClear")).click();
					Thread.sleep(1000);
					System.out.println("Skipped EMPLID :" + dataList.get(j).getEMPLID());
				}
				System.out.println("Completed EMPLID :" + dataList.get(j).getEMPLID());
				
				}
				driver.findElement(By.id("#ICList")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("#ICClear")).click();
				Thread.sleep(1000);
				
				
			}
		} catch (IOException io) {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error " + e.getMessage());
		}
	}

	@AfterTest
	public void afterTest() throws InterruptedException, IOException {

		driver.quit();
	}

}
