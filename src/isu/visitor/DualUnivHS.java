package isu.visitor;

import static org.junit.Assert.assertEquals;
import isu.data.GradAutomatedData;
import isu.data.UnderGradAutomatedData;
import isu.data.VisitorAutomatedData;
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

public class DualUnivHS {

	private WebDriver driver;
	private String baseUrl;
	String URL1;
	PropertiesUtil propUtil = new PropertiesUtil();
	InputStream input= propUtil.getPropertyFile("url.properties");


	@BeforeTest
	public void beforeTest() {
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		System.setProperty("webdriver.firefox.bin", "Q://LEAP_Test_Cases//Automation//Tools//Mozilla Firefox//firefox.exe");
		profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");  
		profile.setPreference("capability.policy.default.Window.frameElement.get","allAccess");  
		driver = new FirefoxDriver(profile);
		baseUrl = "https://account-test.illinoisstate.edu/selfservice/registration/begin";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


	}
	@Test
	public void DualUnivHSTest()  {

		try{ 

			String[] workBookNames= {"DualUnivHS"};
			Util util = new Util();		
			List<VisitorAutomatedData> dataList = new ArrayList<VisitorAutomatedData>();
			dataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data", "VisitorAutomationData.xls", workBookNames,false,false);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows "+dataList.size());
			for(int j=0;j<1;j++)
			{

				URL1 = propUtil.getPropertyValue("ih.test.url", input);
				driver.get(URL1);
				assertEquals("signin", driver.getTitle());
				driver.findElement(By.id("userid")).clear();
				driver.findElement(By.id("userid")).sendKeys(dataList.get(j).getUserid());
				driver.findElement(By.id("pwd")).clear();
				driver.findElement(By.id("pwd")).sendKeys(dataList.get(j).getPwd());
				driver.findElement(By.name("Submit")).click();
				Thread.sleep(2000);
				if("exception".equalsIgnoreCase(driver.getTitle())){
					driver.findElement(By.id("testLink")).click();
					driver.findElement(By.id("userid")).clear();
					driver.findElement(By.id("userid")).sendKeys(dataList.get(j).getUserid());
					driver.findElement(By.id("pwd")).clear();
					driver.findElement(By.id("pwd")).sendKeys(dataList.get(j).getPwd());
					driver.findElement(By.name("Submit")).click();
				}
			
				WebDriverWait wait = new WebDriverWait(driver,30);
				wait.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						while(true){
							try{
								System.out.println("Executed");
								return driver.findElement(By.id("app-visitor")).isDisplayed();

							}

							catch(StaleElementReferenceException ex)
							{
								System.out.println("Sign In not found");
								return false;
							}
							catch(ElementNotFoundException ex)
							{
								System.out.println("Sign In not found");
								return false;
							}
						}
					}
				});
				driver.findElement(By.id("app-visitor")).click();
				driver.switchTo().frame("TargetContent");
				driver.findElement(By.id("OAA_LINK_WRK_TRANSFER_BTN")).click();
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_CHECK_BOX$0")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS07$181$")).click();
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS03")).click();
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS04")).click();
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS05$205$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_SSN_INFO_SSN")).clear();
				driver.findElement(By.id("OAA_SSN_INFO_SSN")).sendKeys("526354578");
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0"))).selectByVisibleText("None");
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_HISP_LAT_Y$17$")).click();
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_AMI_ALN")).click();
				driver.findElement(By.id("OAA_CRIMINAL_QA_OAA_CRIMINAL_QUES1$18$")).click();
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_SELECTED$1")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				new Select(driver.findElement(By.id("OAA_ACAD_INTRST_ADMIT_TERM"))).selectByVisibleText(dataList.get(j).getTerm());
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_EDU_HISTORY_OAA_HIGH_SCH_OPTNS$0")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SEARCH_PB$0")).click();
				new Select(driver.findElement(By.id("OAA_ORG_SRCH_STATE"))).selectByVisibleText("Oklahoma");
				driver.findElement(By.id("OAA_ORG_SRCH_SEARCH_PB")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_LINK2$10")).click();
				new Select(driver.findElement(By.id("OAA_EDU_HISTORY_GPA_TYPE$0"))).selectByVisibleText("4 Point Scale");
				driver.findElement(By.id("OAA_EDU_HISTORY_EXT_GPA$0")).clear();
				driver.findElement(By.id("OAA_EDU_HISTORY_EXT_GPA$0")).sendKeys("3.1");
				driver.findElement(By.cssSelector("img[alt=\"Choose a date (Alt+5)\"]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2009");
				new Select(driver.findElement(By.name("PTMonth"))).selectByVisibleText("April");
				driver.findElement(By.linkText("17")).click();
				driver.findElement(By.id("OAA_EDU_HIST1_LS_SCHOOL_TYPE$0")).click();
				driver.findElement(By.id("OAA_EDU_HIST1_SEARCH_BTN$0")).click();
				new Select(driver.findElement(By.id("OAA_ORG_SRCH_STATE"))).selectByVisibleText("Delaware");
				driver.findElement(By.id("OAA_ORG_SRCH_SEARCH_PB")).click();
				driver.findElement(By.id("LINK1$2")).click();
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[2]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2010");
				new Select(driver.findElement(By.name("PTMonth"))).selectByVisibleText("June");
				driver.findElement(By.linkText("15")).click();
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[3]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2012");
				driver.findElement(By.linkText("17")).click();
				new Select(driver.findElement(By.id("OAA_EDU_HIST1_DEGREE$0"))).selectByVisibleText("Bachelor's Degree");
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[4]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
				driver.findElement(By.linkText("15")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_SUBMIT_CONF_CONFIRMED")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$6$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SUBMITTED")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_OK_BTN")).click();
				// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
				driver.findElement(By.linkText("Sign out")).click();
				// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _top | 30000]]
				assertEquals("signin", driver.getTitle());
				/*//driver.get("https://portalisuvtst.oracleoutsourcing.com/psp/TISUVP/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST");
				driver.findElement(By.id("userid")).clear();
				driver.findElement(By.id("userid")).sendKeys(gradDataList.get(j).getUserid());
				driver.findElement(By.id("pwd")).clear();
				driver.findElement(By.id("pwd")).sendKeys(gradDataList.get(j).getPwd());
				driver.findElement(By.name("Submit")).click();
				Thread.sleep(2000);
				if("exception".equalsIgnoreCase(driver.getTitle())){
					driver.findElement(By.id("testLink")).click();
					driver.findElement(By.id("userid")).clear();
					driver.findElement(By.id("userid")).sendKeys(gradDataList.get(j).getUserid());
					driver.findElement(By.id("pwd")).clear();
					driver.findElement(By.id("pwd")).sendKeys(gradDataList.get(j).getPwd());
					driver.findElement(By.name("Submit")).click();
				}

				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						while(true){
							try{
								System.out.println("Executed");
								return driver.findElement(By.id("app-grd")).isDisplayed();

							}

							catch(StaleElementReferenceException ex)
							{
								System.out.println("Sign In not found");
								return false;
							}
							catch(ElementNotFoundException ex)
							{
								System.out.println("Sign In not found");
								return false;
							}
						}
					}
				});


				driver.findElement(By.id("app-grd")).click();
				driver.switchTo().frame("TargetContent");

				driver.findElement(By.id("OAA_LINK_WRK_TRANSFER_BTN")).click();
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_CHECK_BOX$0")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS06")).click();
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS03")).click();
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS04$200$")).click();
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS05$205$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_BIO_PERS_OAA_ADD_PRF_NAME")).click();
				driver.findElement(By.id("OAA_NAME_PRF_FIRST_NAME")).clear();
				driver.findElement(By.id("OAA_NAME_PRF_FIRST_NAME")).sendKeys(gradDataList.get(j).getPrefFirstName());
				new Select(driver.findElement(By.id("OAA_BIO_PERS2_BIRTHCOUNTRY"))).selectByVisibleText(gradDataList.get(j).getBirthCountry());
				driver.findElement(By.id("OAA_BIO_PERS2_BIRTHPLACE")).clear();
				driver.findElement(By.id("OAA_BIO_PERS2_BIRTHPLACE")).sendKeys(gradDataList.get(j).getBirthPlace());
				driver.findElement(By.id("OAA_SSN_INFO_SSN")).clear();
				driver.findElement(By.id("OAA_SSN_INFO_SSN")).sendKeys("235789856");
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_HOME_ADDR_ADDRESS1")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_ADDRESS1")).sendKeys(gradDataList.get(j).getHomeAddress1());
				driver.findElement(By.id("OAA_HOME_ADDR_CITY")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_CITY")).sendKeys(gradDataList.get(j).getHomeAddressCity());
				new Select(driver.findElement(By.id("OAA_HOME_ADDR_STATE"))).selectByVisibleText(gradDataList.get(j).getHomeAddressState());
				driver.findElement(By.id("OAA_HOME_ADDR_POSTAL")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_POSTAL")).sendKeys(gradDataList.get(j).getHomeAddressPostal());
				new Select(driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE_TYPE$0"))).selectByVisibleText(gradDataList.get(j).getPhoneType());
				driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE$0")).clear();
				driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE$0")).sendKeys(gradDataList.get(j).getPhone());
				driver.findElement(By.id("OAA_CNTCT_EMAIL_EMAIL_ADDR$0")).clear();
				driver.findElement(By.id("OAA_CNTCT_EMAIL_EMAIL_ADDR$0")).sendKeys(gradDataList.get(j).getEmail());
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0"))).selectByVisibleText(gradDataList.get(j).getISURelative());
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_RESIDENCY_RESIDENCY_REQ")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_HISP_LAT_Y$17$")).click();
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_BLK_AFAM")).click();
				driver.findElement(By.cssSelector("img[alt=\"Choose a date (Alt+5)\"]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2011");
				driver.findElement(By.linkText("8")).click();
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[2]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
				driver.findElement(By.linkText("17")).click();
				driver.findElement(By.id("OAA_CRIMINAL_QA_OAA_CRIMINAL_QUES1")).click();
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_SELECTED$0")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				new Select(driver.findElement(By.id("OAA_ACAD_INTRST_ADMIT_TERM"))).selectByVisibleText(gradDataList.get(j).getAdmitTerm());
				driver.findElement(By.id("OAA_PR_PL_S_WRK_ADDL_DATA_PB")).click();
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_STRING")).clear();
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_STRING")).sendKeys(gradDataList.get(j).getSearchOne());
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_BTN")).click();
				driver.findElement(By.id("OAA_PR_PL_SR_VW_DESCR254_MIXED$3")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0"))).selectByVisibleText(gradDataList.get(j).getAppAnswer0());
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_RECOMM_WRK_ADD_MBR_BTN")).click();
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).sendKeys(gradDataList.get(j).getRecomFirstName());
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).sendKeys(gradDataList.get(j).getRecomLastName());
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).sendKeys(gradDataList.get(j).getRecomWorkEmail());
				driver.findElement(By.id("OAA_RECOMMD_WRK_SAVEBTN")).click();
				driver.findElement(By.id("OAA_RECOMM_WRK_ADD_MBR_BTN")).click();
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).sendKeys(gradDataList.get(j).getRecomFirstName1());
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).sendKeys(gradDataList.get(j).getRecomLastName1());
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).sendKeys(gradDataList.get(j).getRecomWorkEmail1());
				driver.findElement(By.id("OAA_RECOMMD_WRK_SAVEBTN")).click();
				driver.findElement(By.id("OAA_RECOMM_WRK_ADD_MBR_BTN")).click();
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).sendKeys(gradDataList.get(j).getRecomFirstName2());
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).sendKeys(gradDataList.get(j).getRecomLastName2());
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).sendKeys(gradDataList.get(j).getRecomWorkEmail2());
				driver.findElement(By.id("OAA_RECOMMD_WRK_SAVEBTN")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_EDU_HIST1_LS_SCHOOL_TYPE$0")).click();
				driver.findElement(By.id("OAA_EDU_HIST1_SEARCH_BTN$0")).click();
				new Select(driver.findElement(By.id("OAA_ORG_SRCH_STATE"))).selectByVisibleText(gradDataList.get(j).getSchoolState());
				driver.findElement(By.id("OAA_ORG_SRCH_SEARCH_PB")).click();
				driver.findElement(By.id("LINK1$0")).click();
				driver.findElement(By.cssSelector("img[alt=\"Choose a date (Alt+5)\"]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2010");
				driver.findElement(By.linkText("8")).click();
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[2]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
				driver.findElement(By.linkText("10")).click();
				new Select(driver.findElement(By.id("OAA_EDU_HIST1_DEGREE$0"))).selectByVisibleText(gradDataList.get(j).getDegree());
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[3]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
				new Select(driver.findElement(By.name("PTMonth"))).selectByVisibleText("December");
				driver.findElement(By.linkText("22")).click();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_SQC$0")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_SQC$0")).sendKeys("231");
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_TITLE$0")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_TITLE$0")).sendKeys("esp");
				driver.findElement(By.id("OAA_EDU_HIS_CRS_CRSE_CONTACT_HRS$0")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_CRSE_CONTACT_HRS$0")).sendKeys("21");
				driver.findElement(By.id("$ICField$66$$fnew$0")).click();
				driver.findElement(By.id("OAA_EDU_HIST1_LS_SCHOOL_TYPE$1")).click();
				driver.findElement(By.id("OAA_EDU_HIST1_SEARCH_BTN$1")).click();
				new Select(driver.findElement(By.id("OAA_ORG_SRCH_STATE"))).selectByVisibleText(gradDataList.get(j).getSchoolState2());
				driver.findElement(By.id("OAA_ORG_SRCH_SEARCH_PB")).click();
				driver.findElement(By.id("LINK1$0")).click();
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[4]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2004");
				driver.findElement(By.linkText("1")).click();
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[5]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2005");
				new Select(driver.findElement(By.name("PTMonth"))).selectByVisibleText("February");
				driver.findElement(By.linkText("13")).click();
				new Select(driver.findElement(By.id("OAA_EDU_HIST1_DEGREE$1"))).selectByVisibleText(gradDataList.get(j).getDegree2());
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[6]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2005");
				driver.findElement(By.linkText("7")).click();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_SQC$1")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_SQC$1")).sendKeys("432");
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_TITLE$1")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_TITLE$1")).sendKeys("artsetest");
				driver.findElement(By.id("OAA_EDU_HIS_CRS_CRSE_CONTACT_HRS$1")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_CRSE_CONTACT_HRS$1")).sendKeys("31");
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				driver.findElement(By.id("OAA_SUBMIT_CONF_CONFIRMED")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_SUBMITTED")).click();
				driver.findElement(By.id("OAA_DERIVED_WRK_OK_BTN")).click();
				// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
				driver.findElement(By.linkText("Sign out")).click();
				// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _top | 30000]]
				assertEquals("signin", driver.getTitle());*/
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
		driver.quit();
	}

}
