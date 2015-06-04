package isu.undergrad;

import static org.junit.Assert.assertEquals;
import isu.data.UnderGradAutomatedData;
import isu.util.PropertiesUtil;
import isu.util.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class CreateUnderGradHistoryApplicationCS {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	String URL1;
	PropertiesUtil propUtil = new PropertiesUtil();
	InputStream input = propUtil.getPropertyFile("url.properties");

	@BeforeTest
	public void beforeTest() {
		// System.setProperty("webdriver.chrome.driver",
		// "Q://LEAP_Test_Cases//Automation//Tools//chromedriver_win32/chromedriver.exe");
		// driver = new RemoteWebDriver("http://localhost:9515", new
		// DesiredCapabilities(DesiredCapabilities.chrome()));

		// driver = new ChromeDriver();

		/*ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		System.setProperty("webdriver.firefox.bin", "Q://LEAP_Test_Cases//Automation//Tools//Mozilla Firefox//firefox.exe");
		profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");
		profile.setPreference("capability.policy.default.Window.frameElement.get", "allAccess");*/
		driver = new FirefoxDriver();

		baseUrl = "https://account-test.illinoisstate.edu/selfservice/registration/begin";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void CreateUnderGradMusicApplicationTest() {

		try {

			String[] workBookNames = { "createApplication" };
			Util util = new Util();
			List<UnderGradAutomatedData> underGradDataList = new ArrayList<UnderGradAutomatedData>();
			underGradDataList = util.readFile("Q://LEAP_Test_Cases//Automation//Data", "UnderGradAutomationData.xls", workBookNames, false, false);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows " + underGradDataList.size());
			for (int j = 0; j < 1; j++) {

				URL1 = propUtil.getPropertyValue("cs.test.url", input);
				driver.get(URL1);
				// driver.get("https://portalisuvtst.oracleoutsourcing.com/psp/TISUVP/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST");
				driver.findElement(By.id("userid")).clear();
				driver.findElement(By.id("userid")).sendKeys(underGradDataList.get(j).getUserid());
				driver.findElement(By.id("pwd")).clear();
				driver.findElement(By.id("pwd")).sendKeys(underGradDataList.get(j).getPwd());
				driver.findElement(By.name("Submit")).click();
				Thread.sleep(2000);

				driver.findElement(By.id("pthnavbca_PORTAL_ROOT_OBJECT")).click();
				driver.findElement(By.id("fldra_OAA_ONLINE_APPLICATION")).click();
				driver.findElement(By.id("OAA_ADMISSION_APPLICATION")).click();
				driver.findElement(By.id("crefli_OAA_APPLN_LINKS_GBL")).click();
				Thread.sleep(500);
				// ERROR: Caught exception [ERROR: Unsupported command
				// [waitForPopUp | _parent | 30000]]
				// assertEquals("OAA Apps Links Test Page", driver.getTitle());
				driver.findElement(By.id("DESCR$2")).click();
				// assertEquals("Online Admission Application",
				// driver.getTitle());
				driver.findElement(By.id("OAA_LINK_WRK_TRANSFER_BTN")).click();
				Thread.sleep(1500);
				// assertEquals("Online Admission Application",
				// driver.getTitle());
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_CHECK_BOX$0")).click();
				Thread.sleep(1500);
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("Q1A")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS03")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS04")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS05$205$")).click();
				Thread.sleep(1500);
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				/*
				 * driver.findElement(By.id("OAA_BIO_PERS_FIRST_NAME")).clear();
				 * driver
				 * .findElement(By.id("OAA_BIO_PERS_FIRST_NAME")).sendKeys(
				 * "Vega");
				 * driver.findElement(By.id("OAA_BIO_PERS_LAST_NAME")).clear();
				 * driver
				 * .findElement(By.id("OAA_BIO_PERS_LAST_NAME")).sendKeys("Tes"
				 * );
				 */
				new Select(driver.findElement(By.id("OAA_BIO_PERS2_BIRTHCOUNTRY"))).selectByVisibleText(underGradDataList.get(j).getBirthCountry());
				driver.findElement(By.id("OAA_SSN_INFO_SSN")).clear();
				driver.findElement(By.id("OAA_SSN_INFO_SSN")).sendKeys("652-24-4586");
				Thread.sleep(1500);
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0"))).selectByVisibleText("None");
				Thread.sleep(1500);
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_HISP_LAT_Y$17$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_HAW_PAC")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_CRIMINAL_QA_OAA_CRIMINAL_QUES1$18$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_SELECTED$1")).click();
				Thread.sleep(1500);
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_ACAD_INTRST_ADMIT_TERM"))).selectByVisibleText("Fall 2015");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_PR_PL_S_WRK_ADDL_DATA_PB")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_STRING")).clear();
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_STRING")).sendKeys("History");
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_BTN")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("OAA_PR_PL_SR_VW_DESCR254_MIXED$1")).click();
				Thread.sleep(1500);
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_EDU_HISTORY_OAA_HIGH_SCH_OPTNS$65$$0")).click();
				Thread.sleep(1500);
				driver.findElement(By.name("OAA_EDU_HISTORY_DATE_RECEIVED$prompt$img$0")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2011");
				new Select(driver.findElement(By.name("PTMonth"))).selectByVisibleText("April");
				driver.findElement(By.linkText("18")).click();
				Thread.sleep(1500);
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_APPL_FEE_WAIVER")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_SUBMIT_CONF_CONFIRMED")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$6$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SUBMITTED")).click();
				Thread.sleep(3500);
				driver.findElement(By.id("OAA_DERIVED_WRK_OK_BTN")).click();

			}

		} catch (IOException ex) {
			System.err.println("Error reading excel file while testing Login User" + ex.getMessage());
		} catch (InterruptedException ie) {
			System.err.println("Error in Thread in Login User" + ie.getMessage());
		}
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
