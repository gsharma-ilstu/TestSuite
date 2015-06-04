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
import org.apache.xml.serialize.ElementState;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class TransferUnderGradMusicApplication {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	String URL1;
	PropertiesUtil propUtil = new PropertiesUtil();
	InputStream input= propUtil.getPropertyFile("url.properties");

	@BeforeTest
	public void beforeTest() {
		/*ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		System.setProperty("webdriver.firefox.bin", "Q://LEAP_Test_Cases//Automation//Tools//Mozilla Firefox//firefox.exe");
		profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");  
		profile.setPreference("capability.policy.default.Window.frameElement.get","allAccess");*/  
		driver = new FirefoxDriver();
		baseUrl = "https://account-test.illinoisstate.edu/selfservice/registration/begin";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TransferUnderGradMusicApplicationTest()  {

		try{ 

			String[] workBookNames= {"transferUnderGrad"};
			Util util = new Util();
			List<UnderGradAutomatedData> underGradDataList = new ArrayList<UnderGradAutomatedData>();
			underGradDataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data", "UnderGradAutomationData.xls", workBookNames,false,false);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows "+underGradDataList.size());
			for(int j=0;j<1;j++)
			{

				URL1 = propUtil.getPropertyValue("ih.test.url", input);
				driver.get(URL1);
				//driver.get("https://portalisuvtst.oracleoutsourcing.com/psp/TISUVP/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST&cmd=login");
				driver.findElement(By.id("userid")).clear();
				driver.findElement(By.id("userid")).sendKeys(underGradDataList.get(j).gettUserId());
				driver.findElement(By.id("pwd")).clear();
				driver.findElement(By.id("pwd")).sendKeys(underGradDataList.get(j).gettPwd());
				driver.findElement(By.name("Submit")).click();
				Thread.sleep(2000);
				if("exception".equalsIgnoreCase(driver.getTitle())){
					driver.findElement(By.id("testLink")).click();
					driver.findElement(By.id("userid")).clear();
					driver.findElement(By.id("userid")).sendKeys(underGradDataList.get(j).gettUserId());
					driver.findElement(By.id("pwd")).clear();
					driver.findElement(By.id("pwd")).sendKeys(underGradDataList.get(j).gettPwd());
					driver.findElement(By.name("Submit")).click();
				}
		
				driver.findElement(By.id("app-ugrd")).click();
				driver.switchTo().frame("TargetContent");
				driver.findElement(By.id("OAA_LINK_WRK_TRANSFER_BTN")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_CHECK_BOX$0")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("Q1B")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS03")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS05$205$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_BIO_PERS_OAA_ADD_PRF_NAME")).click();
				Thread.sleep(1500);
				if(driver.findElement(By.id("OAA_BIO_PERS_OAA_ADD_NAME")).isEnabled())
				{
					driver.findElement(By.id("OAA_BIO_PERS_OAA_ADD_NAME")).click();
					Thread.sleep(1500);
				}
				driver.findElement(By.id("OAA_NAME_PRF_FIRST_NAME")).clear();
				driver.findElement(By.id("OAA_NAME_PRF_FIRST_NAME")).sendKeys(underGradDataList.get(j).gettPrefFirstName());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_BIO_PERS3_LAST_NAME")).clear();
				driver.findElement(By.id("OAA_BIO_PERS3_LAST_NAME")).sendKeys(underGradDataList.get(j).gettPrefLastName());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_BIO_PERS2_BIRTHCOUNTRY"))).selectByVisibleText(underGradDataList.get(j).gettBirthCountry());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_BIO_PERS2_BIRTHPLACE")).clear();
				driver.findElement(By.id("OAA_BIO_PERS2_BIRTHPLACE")).sendKeys(underGradDataList.get(j).gettBirthPlace());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_SSN_INFO_SSN")).clear();
				driver.findElement(By.id("OAA_SSN_INFO_SSN")).sendKeys(underGradDataList.get(j).gettSSN());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_HOME_ADDR_ADDRESS1")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_ADDRESS1")).sendKeys(underGradDataList.get(j).gettHomeAddress1());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_HOME_ADDR_CITY")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_CITY")).sendKeys(underGradDataList.get(j).gettHomeAddressCity());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_HOME_ADDR_STATE"))).selectByVisibleText(underGradDataList.get(j).gettHomeAddressState());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_HOME_ADDR_POSTAL")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_POSTAL")).sendKeys(underGradDataList.get(j).gettHomeAddressPostal());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE_TYPE$0"))).selectByVisibleText("Home");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE$0")).clear();
				driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE$0")).sendKeys(underGradDataList.get(j).gettPhone());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_CNTCT_EMAIL_EMAIL_ADDR$0")).clear();
				driver.findElement(By.id("OAA_CNTCT_EMAIL_EMAIL_ADDR$0")).sendKeys(underGradDataList.get(j).gettEmail());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_FAMILY_INFO_FIRST_NAME$0")).clear();
				driver.findElement(By.id("OAA_FAMILY_INFO_FIRST_NAME$0")).sendKeys(underGradDataList.get(j).gettFamilyFirstName());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_FAMILY_INFO_LAST_NAME$0")).clear();
				driver.findElement(By.id("OAA_FAMILY_INFO_LAST_NAME$0")).sendKeys(underGradDataList.get(j).gettFamilyLastName());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_FAMILY_INFO_PEOPLE_RELATION$0"))).selectByVisibleText(underGradDataList.get(j).gettFamilyRelation());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_FAMILY_INFO_ADDRESS1$0")).clear();
				driver.findElement(By.id("OAA_FAMILY_INFO_ADDRESS1$0")).sendKeys(underGradDataList.get(j).gettFamilAddress1());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_FAMILY_INFO_CITY$0")).clear();
				driver.findElement(By.id("OAA_FAMILY_INFO_CITY$0")).sendKeys(underGradDataList.get(j).gettFamilyAddressCity());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_FAMILY_INFO_STATE$0"))).selectByVisibleText(underGradDataList.get(j).gettFamilyAddressState());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_FAMILY_INFO_POSTAL$0")).clear();
				driver.findElement(By.id("OAA_FAMILY_INFO_POSTAL$0")).sendKeys(underGradDataList.get(j).gettFamilyAddressPostal());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_FAMILY_INFO_EMAILID$0")).clear();
				driver.findElement(By.id("OAA_FAMILY_INFO_EMAILID$0")).sendKeys(underGradDataList.get(j).gettFamilyEmail());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_FAMILY_INFO_PHONE_DAY$0")).clear();
				driver.findElement(By.id("OAA_FAMILY_INFO_PHONE_DAY$0")).sendKeys(underGradDataList.get(j).gettFamilyPhone());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0"))).selectByVisibleText("Other Relative");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_RESIDENCY_STATE"))).selectByVisibleText(underGradDataList.get(j).gettResidencyState());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_HISP_LAT_Y$17$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_WHITE")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_CRIMINAL_QA_OAA_CRIMINAL_QUES1$18$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_SELECTED$1")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_ACAD_INTRST_ADMIT_TERM"))).selectByVisibleText(underGradDataList.get(j).gettAdmitTerm());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_ACAD_INTRST_ACADEMIC_LEVEL"))).selectByVisibleText(underGradDataList.get(j).gettAcadLevel());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_PR_PL_S_WRK_ADDL_DATA_PB")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_STRING")).clear();
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_STRING")).sendKeys("Music");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_BTN")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_PR_PL_SR_VW_DESCR254_MIXED$8")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_PRE_PRO_PLN_ACAD_PLAN_INC"))).selectByVisibleText("Pre-Engineering Physics");
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0"))).selectByVisibleText(underGradDataList.get(j).gettAppAnswer0());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$1"))).selectByVisibleText(underGradDataList.get(j).gettAppAnswer1());
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$2"))).selectByVisibleText(underGradDataList.get(j).gettAppAnswer2());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$3")).sendKeys(underGradDataList.get(j).gettAppAnswer3()+"\t");
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$4"))).selectByVisibleText(underGradDataList.get(j).gettAppAnswer4());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$5")).sendKeys(underGradDataList.get(j).gettAppAnswer5()+"\t");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$6")).sendKeys(underGradDataList.get(j).gettAppAnswer6()+"\t");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$7")).sendKeys(underGradDataList.get(j).gettAppAnswer7()+"\t");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$8")).sendKeys(underGradDataList.get(j).gettAppAnswer8()+"\t");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_OAA_APPLICANT_ANS$9")).sendKeys(underGradDataList.get(j).gettAppAnswer9()+"\t");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_EDU_HISTORY_OAA_HIGH_SCH_OPTNS$65$$0")).click();
				driver.findElement(By.cssSelector("img[alt=\"Choose a date (Alt+5)\"]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
				driver.findElement(By.linkText("11")).click();
				driver.findElement(By.id("OAA_EDU_HIST1_LS_SCHOOL_TYPE$0")).click();
				driver.findElement(By.id("OAA_EDU_HIST1_SEARCH_BTN$0")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_ORG_SRCH_STATE"))).selectByVisibleText("Massachusetts");
				driver.findElement(By.id("OAA_ORG_SRCH_SEARCH_PB")).click();
				Thread.sleep(2500);
				driver.findElement(By.id("LINK1$7")).click();
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[2]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2009");
				driver.findElement(By.xpath("//table[@id='bodyCalendar']/tbody/tr[2]/td[2]")).click();
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[3]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2012");
				driver.findElement(By.linkText("11")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_EDU_HIST1_DEGREE$0"))).selectByVisibleText("Associate of Arts");
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[4]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
				driver.findElement(By.linkText("10")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_SQC$0")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_SQC$0")).sendKeys("231");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_TITLE$0")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_TITLE$0")).sendKeys("Music bla bla");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_EDU_HIS_CRS_CRSE_CONTACT_HRS$0")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_CRSE_CONTACT_HRS$0")).sendKeys("40");
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_TSTINF_TBL1_TEST_ID$0"))).selectByVisibleText(underGradDataList.get(j).gettTest());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$0")).click();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$0")).clear();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$0")).sendKeys(underGradDataList.get(j).gettScore0());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$1")).click();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$1")).clear();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$1")).sendKeys(underGradDataList.get(j).gettScore1());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$2")).click();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$2")).clear();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$2")).sendKeys(underGradDataList.get(j).gettScore2());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$3")).click();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$3")).clear();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$3")).sendKeys(underGradDataList.get(j).gettScore3());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$4")).click();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$4")).clear();
				driver.findElement(By.id("OAA_TSTINF_TBL2_SCORE$4")).sendKeys(underGradDataList.get(j).gettScore4());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				new Select(driver.findElement(By.id("OAA_APPL_FEE_PAYMENT_METHOD"))).selectByVisibleText(underGradDataList.get(j).gettPayMethod());
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_APPL_FEE_OAA_PAYMENT_LINK")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("cardNum")).clear();
				driver.findElement(By.id("cardNum")).sendKeys(underGradDataList.get(j).gettCardNumber());
				Thread.sleep(1000);
				driver.findElement(By.id("submitBtn")).click();
				Thread.sleep(1500);
				if(driver.findElement(By.id("name")).isDisplayed())
				{
					driver.findElement(By.id("name")).clear();
					driver.findElement(By.id("name")).sendKeys(underGradDataList.get(j).gettNameOnCard());
				}
				Thread.sleep(1500);
				driver.findElement(By.id("cvv")).clear();
				driver.findElement(By.id("cvv")).sendKeys(underGradDataList.get(j).gettCVV());
				Thread.sleep(1500);
				driver.findElement(By.id("submitBtn")).click();
				Thread.sleep(3500);
				driver.findElement(By.id("OAA_PAYMENT_WRK_OAA_SUBMIT_TYPE")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_PAYMENT_WRK_CONTINUE2_PB")).click();
				Thread.sleep(2500);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_SUBMIT_CONF_CONFIRMED")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("OAA_DERIVED_WRK_SUBMITTED")).click();
				Thread.sleep(6500);
				driver.findElement(By.id("OAA_DERIVED_WRK_OK_BTN")).click();
				Thread.sleep(1500);
				driver.switchTo().defaultContent();
				Thread.sleep(1500);
				// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
				driver.findElement(By.linkText("Sign out")).click();
				// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _top | 30000]]




			}

		}
		catch(IOException ex){
			System.err.println("Error reading excel file while testing Login User" +ex.getMessage()); 
		}
		catch(InterruptedException ie){
			System.err.println("Error in Thread in Login User" +ie.getMessage());
		}
	}


	@AfterTest
	public void afterTest() {
		try {
			Thread.sleep(2500);
			driver.quit();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
