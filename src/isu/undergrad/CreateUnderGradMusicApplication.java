package isu.undergrad;

import static org.junit.Assert.assertEquals;
import isu.data.UnderGradAutomatedData;
import isu.util.PropertiesUtil;
import isu.util.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class CreateUnderGradMusicApplication {

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
		driver.manage().window().maximize();
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

				URL1 = propUtil.getPropertyValue("ih.test.url", input);
				driver.get(URL1);
				// driver.get("https://portalisuvtst.oracleoutsourcing.com/psp/TISUVP/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST");
				driver.findElement(By.id("userid")).clear();
				driver.findElement(By.id("userid")).sendKeys(underGradDataList.get(j).getUserid());
				driver.findElement(By.id("pwd")).clear();
				driver.findElement(By.id("pwd")).sendKeys(underGradDataList.get(j).getPwd());
				driver.findElement(By.name("Submit")).click();
				Thread.sleep(2000);

				if ("exception".equalsIgnoreCase(driver.getTitle())) {
					driver.findElement(By.id("testLink")).click();
					driver.findElement(By.id("userid")).clear();
					driver.findElement(By.id("userid")).sendKeys(underGradDataList.get(j).getUserid());
					driver.findElement(By.id("pwd")).clear();
					driver.findElement(By.id("pwd")).sendKeys(underGradDataList.get(j).getPwd());
					driver.findElement(By.name("Submit")).click();
				}
/*
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						while (true) {
							try {
								System.out.println("Executed");
								return driver.findElement(By.id("app-ugrd")).isDisplayed();

							}

							catch (StaleElementReferenceException ex) {
								System.out.println("Sign In not found");
								return false;
							} catch (ElementNotFoundException ex) {
								System.out.println("Sign In not found");
								return false;
							}
						}
					}
				});*/

				driver.findElement(By.id("app-ugrd")).click();
				Thread.sleep(1500);
				driver.switchTo().frame("TargetContent");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_LINK_WRK_TRANSFER_BTN")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_CHECK_BOX$0")).click();
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(2500);
				driver.findElement(By.id("Q1A")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS03")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS04")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS05")).click();
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1000);
				new Select(driver.findElement(By.id("OAA_BIO_PERS2_BIRTHCOUNTRY"))).selectByVisibleText(underGradDataList.get(j).getBirthCountry());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_BIO_PERS2_BIRTHPLACE")).clear();
				driver.findElement(By.id("OAA_BIO_PERS2_BIRTHPLACE")).sendKeys("Test Normal");
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_HOME_ADDR_ADDRESS1")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_ADDRESS1")).sendKeys(underGradDataList.get(j).getHomeAddress1());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_HOME_ADDR_CITY")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_CITY")).sendKeys(underGradDataList.get(j).getHomeAddressCity());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_HOME_ADDR_POSTAL")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_POSTAL")).sendKeys(underGradDataList.get(j).getHomeAddressPostal());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE_TYPE$0"))).selectByVisibleText("Home");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE$0")).clear();
				driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE$0")).sendKeys(underGradDataList.get(j).getPhone());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_CNTCT_EMAIL_EMAIL_ADDR$0")).clear();
				driver.findElement(By.id("OAA_CNTCT_EMAIL_EMAIL_ADDR$0")).sendKeys(underGradDataList.get(j).getEmail());
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				 Actions action = new Actions(driver);
				    Thread.sleep(1000);
				    action.sendKeys(Keys.PAGE_DOWN).perform();
				    driver.switchTo().frame("TargetContent");
				    Thread.sleep(1000);
				    driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0")).click();
				    Thread.sleep(1000);
				    new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0"))).selectByIndex(2);
				//new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0"))).selectByVisibleText("None");
				/*Select select = new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0")));
				if(!select.getAllSelectedOptions().equals("None"))
					select.selectByVisibleText("None");*/
				
				
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_HISP_LAT_Y$17$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_AMI_ALN")).click();
				driver.findElement(By.id("OAA_MILITARY_ST_CHECK_BOX")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_CRIMINAL_QA_OAA_CRIMINAL_QUES1$18$")).click();
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_SELECTED$1")).click();
				Thread.sleep(1500);
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_ACAD_INTRST_ADMIT_TERM"))).selectByVisibleText("Fall 2015");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_PR_PL_S_WRK_ADDL_DATA_PB")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_STRING")).clear();
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_STRING")).sendKeys("Music");
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_BTN")).click();
				Thread.sleep(2500);
				driver.findElement(By.id("OAA_PR_PL_SR_VW_DESCR254_MIXED$6")).click();
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0")))
						.selectByVisibleText(underGradDataList.get(j).getAppAnswer0());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$1")))
						.selectByVisibleText(underGradDataList.get(j).getAppAnswer1());
				Thread.sleep(1500);
				
				
				/*SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yy");
				SimpleDateFormat myFormat = new SimpleDateFormat("MMMM dd,yyyy");
				String reformattedStr ="";
				try {

				    reformattedStr = myFormat.format(fromUser.parse(underGradDataList.get(j).getAppAnswer2()));
				} catch (ParseException e) {
				    e.printStackTrace();
				}
				
			
				System.out.println(reformattedStr);*/
				driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$2")).click();
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$2"))).selectByIndex(2);
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$3")).click();
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$3")).clear();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$3")).sendKeys(underGradDataList.get(j).getAppAnswer3() +"\t");
				Thread.sleep(2000);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$4")).click();
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$4")).clear();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$4")).sendKeys(underGradDataList.get(j).getAppAnswer4() +"\t");
				Thread.sleep(2000);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$5")).click();
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$5")).clear();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$5")).sendKeys(underGradDataList.get(j).getAppAnswer5()+"\t");
				Thread.sleep(2000);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$6")).click();
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$6")).clear();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$6")).sendKeys(underGradDataList.get(j).getAppAnswer6()+"\t");
				Thread.sleep(2000);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$7")).click();
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$7")).clear();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$7")).sendKeys(underGradDataList.get(j).getAppAnswer7()+"\t");
				Thread.sleep(2000);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_EDU_HISTORY_OAA_HIGH_SCH_OPTNS$0")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SEARCH_PB$0")).click();
				Thread.sleep(1500);
				// ERROR: Caught exception [ERROR: Unsupported command
				// [selectFrame | TargetContent | ]]
				new Select(driver.findElement(By.id("OAA_ORG_SRCH_STATE"))).selectByVisibleText("Illinois");
				driver.findElement(By.id("OAA_ORG_SRCH_SEARCH_PB")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("OAA_DERIVED_WRK_LINK2$3")).click();
				new Select(driver.findElement(By.id("OAA_EDU_HISTORY_GPA_TYPE$0"))).selectByVisibleText("4 Point Scale");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_EDU_HISTORY_EXT_GPA$0")).clear();
				driver.findElement(By.id("OAA_EDU_HISTORY_EXT_GPA$0")).sendKeys(underGradDataList.get(j).getGpascore());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_EDU_HIS_SUB_EXT_SUBJECT_AREA$0"))).selectByVisibleText("Fine Arts");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_EDU_HIS_SUB_UNT_COMP_TOTAL$0")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_SUB_UNT_COMP_TOTAL$0")).sendKeys(underGradDataList.get(j).getComptotal());
				Thread.sleep(1500);
				driver.findElement(By.cssSelector("img[alt=\"Choose a date (Alt+5)\"]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
				driver.findElement(By.linkText("5")).click();
				// new
				// Select(driver.findElement(By.id("OAA_EDU_HIS_SUB_EXT_SUBJECT_AREA$0"))).selectByVisibleText("English");
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_TSTINF_TBL1_TEST_ID$0"))).selectByVisibleText(underGradDataList.get(j).getTest());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$0")).click();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$0")).clear();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$0")).sendKeys(underGradDataList.get(j).getScore0());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$1")).click();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$1")).clear();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$1")).sendKeys(underGradDataList.get(j).getScore1());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$2")).click();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$2")).clear();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$2")).sendKeys(underGradDataList.get(j).getScore2());
				Thread.sleep(1500);
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
			//	driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_APPL_FEE_OAA_PAY_BY_CHECK")).click();
				Thread.sleep(1500);
			//	driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_SUBMIT_CONF_CONFIRMED")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$6$")).click();
				Thread.sleep(3500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SUMMARY_LBL")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_RETURN_PB")).click();
				// ERROR: Caught exception [ERROR: Unsupported command
				// [selectFrame | TargetContent | ]]
				driver.findElement(By.id("OAA_DERIVED_WRK_SUBMITTED")).click();
				Thread.sleep(6000);
				driver.findElement(By.id("OAA_DERIVED_WRK_OK_BTN")).click();
				// ERROR: Caught exception [ERROR: Unsupported command
				// [selectWindow | null | ]]
				driver.switchTo().defaultContent();
				driver.findElement(By.linkText("Sign out")).click();
				// ERROR: Caught exception [ERROR: Unsupported command
				// [waitForPopUp | _top | 30000]]
				// ERROR: Caught exception [unknown command []]

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
