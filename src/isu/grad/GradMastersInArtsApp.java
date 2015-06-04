package isu.grad;

import static org.junit.Assert.assertEquals;
import isu.data.GradAutomatedData;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class GradMastersInArtsApp {

	private WebDriver driver;
	private String baseUrl;/*
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();*/
	String URL1;
	PropertiesUtil propUtil ;
	InputStream input;
	
	
	@BeforeTest
	public void beforeTest() {
		/*ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		System.setProperty("webdriver.firefox.bin", "Q://LEAP_Test_Cases//Automation//Tools//Mozilla Firefox//firefox.exe");
		profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");  
		profile.setPreference("capability.policy.default.Window.frameElement.get","allAccess");  */
		FirefoxProfile fp = new FirefoxProfile();
		fp.setPreference("webdriver.load.strategy", "stable");
		driver = new FirefoxDriver(fp);
		baseUrl = "https://account-test.illinoisstate.edu/selfservice/registration/begin";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
	}
	@Test
	public void GradMastersInArtsAppTest()  {

		try{ 
			propUtil = new PropertiesUtil();
			input= propUtil.getPropertyFile("url.properties");
			String[] workBookNames= {"createApplication"};
			Util util = new Util();		
			List<GradAutomatedData> gradDataList = new ArrayList<GradAutomatedData>();
			gradDataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data", "GraduateAutomationData.xls", workBookNames,false,false);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows "+gradDataList.size());
			for(int j=0;j<1;j++)
			{

				URL1 = propUtil.getPropertyValue("ih.test.url", input);
				//driver.get(URL1);
				driver.get("https://portalisuvtst.oracleoutsourcing.com/psp/TISUVP/?cmd=login&languageCd=ENG");
				Thread.sleep(3000);
				driver.findElement(By.id("userid")).clear();
				driver.findElement(By.id("userid")).sendKeys(gradDataList.get(j).getUserid());
				Thread.sleep(2000);
				driver.findElement(By.id("pwd")).clear();
				driver.findElement(By.id("pwd")).sendKeys(gradDataList.get(j).getPwd());
				driver.findElement(By.name("Submit")).click();
				Thread.sleep(3000);
				if("exception".equalsIgnoreCase(driver.getTitle()) || "signin".equalsIgnoreCase(driver.getTitle())){
					//driver.findElement(By.id("testLink")).click();
					driver.findElement(By.id("userid")).clear();
					driver.findElement(By.id("userid")).sendKeys(gradDataList.get(j).getUserid());
					driver.findElement(By.id("pwd")).clear();
					driver.findElement(By.id("pwd")).sendKeys(gradDataList.get(j).getPwd());
					driver.findElement(By.name("Submit")).click();
				}
				
				WebDriverWait wait = new WebDriverWait(driver,10);
				/*	wait.until(new ExpectedCondition<Boolean>() {
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
				});*/
				Thread.sleep(3000);
				driver.findElement(By.id("app-grd")).click();
				driver.switchTo().frame("TargetContent");
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_LINK_WRK_TRANSFER_BTN")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_CHECK_BOX$0")).click();
				//driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(3000);
				//driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(500);
				driver.findElement(By.xpath("//input[@id='OAA_DERIVED_WRK_NEXT$3$']")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS06")).click();
				Thread.sleep(4000);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS03")).click();
				Thread.sleep(4000);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS04$200$")).click();
				Thread.sleep(4000);
				driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS05$205$")).click();
				Thread.sleep(4000);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(4000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(4000);
				driver.findElement(By.id("OAA_BIO_PERS_OAA_ADD_PRF_NAME")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_NAME_PRF_FIRST_NAME")).clear();
				driver.findElement(By.id("OAA_NAME_PRF_FIRST_NAME")).sendKeys(gradDataList.get(j).getPrefFirstName());
				Thread.sleep(500);
				new Select(driver.findElement(By.id("OAA_BIO_PERS2_BIRTHCOUNTRY"))).selectByVisibleText(gradDataList.get(j).getBirthCountry());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_BIO_PERS2_BIRTHPLACE")).clear();
				driver.findElement(By.id("OAA_BIO_PERS2_BIRTHPLACE")).sendKeys(gradDataList.get(j).getBirthPlace());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_SSN_INFO_SSN")).clear();
				driver.findElement(By.id("OAA_SSN_INFO_SSN")).sendKeys("235789856");
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_HOME_ADDR_ADDRESS1")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_ADDRESS1")).sendKeys(gradDataList.get(j).getHomeAddress1());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_HOME_ADDR_CITY")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_CITY")).sendKeys(gradDataList.get(j).getHomeAddressCity());
				Thread.sleep(3000);
				new Select(driver.findElement(By.id("OAA_HOME_ADDR_STATE"))).selectByVisibleText(gradDataList.get(j).getHomeAddressState());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_HOME_ADDR_POSTAL")).clear();
				driver.findElement(By.id("OAA_HOME_ADDR_POSTAL")).sendKeys(gradDataList.get(j).getHomeAddressPostal());
				Thread.sleep(3000);
				new Select(driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE_TYPE$0"))).selectByVisibleText(gradDataList.get(j).getPhoneType());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE$0")).clear();
				driver.findElement(By.id("OAA_CNTCT_PHONE_PHONE$0")).sendKeys(gradDataList.get(j).getPhone());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_CNTCT_EMAIL_EMAIL_ADDR$0")).clear();
				driver.findElement(By.id("OAA_CNTCT_EMAIL_EMAIL_ADDR$0")).sendKeys(gradDataList.get(j).getEmail());
				Thread.sleep(3000);
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0"))).selectByVisibleText(gradDataList.get(j).getISURelative());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RESIDENCY_RESIDENCY_REQ")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_HISP_LAT_Y$17$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_BLK_AFAM")).click();
				Thread.sleep(3000);
				driver.findElement(By.cssSelector("img[alt=\"Choose a date (Alt+5)\"]")).click();
				Thread.sleep(1000);
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2011");
				Thread.sleep(1000);
				driver.findElement(By.linkText("8")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[2]")).click();
				Thread.sleep(1000);
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
				Thread.sleep(1000);
				driver.findElement(By.linkText("17")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_CRIMINAL_QA_OAA_CRIMINAL_QUES1$18$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_AP_DN_Q_WRK_SELECTED$1")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(3000);
				new Select(driver.findElement(By.id("OAA_ACAD_INTRST_ADMIT_TERM"))).selectByVisibleText(gradDataList.get(j).getAdmitTerm());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_PR_PL_S_WRK_ADDL_DATA_PB")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_STRING")).clear();
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_STRING")).sendKeys(gradDataList.get(j).getSearchOne());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_PR_PL_S_WRK_SEARCH_BTN")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_PR_PL_SR_VW_DESCR254_MIXED$3")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(3000);
				new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0"))).selectByVisibleText(gradDataList.get(j).getAppAnswer0());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMM_WRK_ADD_MBR_BTN")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).sendKeys(gradDataList.get(j).getRecomFirstName());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).sendKeys(gradDataList.get(j).getRecomLastName());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).sendKeys(gradDataList.get(j).getRecomWorkEmail());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_SAVEBTN")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMM_WRK_ADD_MBR_BTN")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).sendKeys(gradDataList.get(j).getRecomFirstName1());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).sendKeys(gradDataList.get(j).getRecomLastName1());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).sendKeys(gradDataList.get(j).getRecomWorkEmail1());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_SAVEBTN")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMM_WRK_ADD_MBR_BTN")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_FIRST_NAME")).sendKeys(gradDataList.get(j).getRecomFirstName2());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_LAST_NAME")).sendKeys(gradDataList.get(j).getRecomLastName2());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).clear();
				driver.findElement(By.id("OAA_RECOMMD_WRK_EMAIL_ADDR")).sendKeys(gradDataList.get(j).getRecomWorkEmail2());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_RECOMMD_WRK_SAVEBTN")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_EDU_HIST1_LS_SCHOOL_TYPE$0")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_EDU_HIST1_SEARCH_BTN$0")).click();
				Thread.sleep(3000);
				new Select(driver.findElement(By.id("OAA_ORG_SRCH_STATE"))).selectByVisibleText(gradDataList.get(j).getSchoolState());
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_ORG_SRCH_SEARCH_PB")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("LINK1$0")).click();
				Thread.sleep(3000);
				driver.findElement(By.cssSelector("img[alt=\"Choose a date (Alt+5)\"]")).click();
				Thread.sleep(1000);
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2010");
				Thread.sleep(1000);
				driver.findElement(By.linkText("8")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[2]")).click();
				Thread.sleep(1000);
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
				Thread.sleep(1000);
				driver.findElement(By.linkText("10")).click();
				Thread.sleep(3000);
				new Select(driver.findElement(By.id("OAA_EDU_HIST1_DEGREE$0"))).selectByVisibleText(gradDataList.get(j).getDegree());
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[3]")).click();
				Thread.sleep(1000);
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
				Thread.sleep(1000);
				new Select(driver.findElement(By.name("PTMonth"))).selectByVisibleText("December");
				Thread.sleep(1000);
				driver.findElement(By.linkText("22")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_SQC$0")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_SQC$0")).sendKeys("231");
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_TITLE$0")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_TITLE$0")).sendKeys("esp");
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_EDU_HIS_CRS_CRSE_CONTACT_HRS$0")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_CRSE_CONTACT_HRS$0")).sendKeys("21");
				Thread.sleep(3000);
				driver.findElement(By.id("$ICField$66$$fnew$0")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_EDU_HIST1_LS_SCHOOL_TYPE$1")).click();
				driver.findElement(By.id("OAA_EDU_HIST1_SEARCH_BTN$1")).click();
				Thread.sleep(3000);
				new Select(driver.findElement(By.id("OAA_ORG_SRCH_STATE"))).selectByVisibleText(gradDataList.get(j).getSchoolState2());
				driver.findElement(By.id("OAA_ORG_SRCH_SEARCH_PB")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("LINK1$0")).click();
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[4]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2004");
				driver.findElement(By.linkText("1")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[5]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2005");
				new Select(driver.findElement(By.name("PTMonth"))).selectByVisibleText("February");
				driver.findElement(By.linkText("13")).click();
				Thread.sleep(3000);
				new Select(driver.findElement(By.id("OAA_EDU_HIST1_DEGREE$1"))).selectByVisibleText(gradDataList.get(j).getDegree2());
				driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[6]")).click();
				new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2005");
				driver.findElement(By.linkText("7")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_SQC$1")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_SQC$1")).sendKeys("432");
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_TITLE$1")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_COURSE_TITLE$1")).sendKeys("artsetest");
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_EDU_HIS_CRS_CRSE_CONTACT_HRS$1")).clear();
				driver.findElement(By.id("OAA_EDU_HIS_CRS_CRSE_CONTACT_HRS$1")).sendKeys("31");
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_SUBMIT_CONF_CONFIRMED")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("OAA_DERIVED_WRK_SUBMITTED")).click();
				Thread.sleep(3500);
				driver.findElement(By.id("OAA_DERIVED_WRK_OK_BTN")).click();
				// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
				driver.findElement(By.linkText("Sign out")).click();
				// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _top | 30000]]
				assertEquals("signin", driver.getTitle());
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
