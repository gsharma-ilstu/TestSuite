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

public class DualOther {

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

			String[] workBookNames= {"DualOther"};
			Util util = new Util();		
			List<VisitorAutomatedData> dataList = new ArrayList<VisitorAutomatedData>();
			dataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data", "VisitorAutomationData.xls", workBookNames,false,false);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows "+dataList.size());
			for(int j=0;j<1;j++)
			{

				URL1 = propUtil.getPropertyValue("ih.test.url", input);
				driver.get(URL1);  
				//driver.get("https://portalisuvtst.oracleoutsourcing.com/psp/TISUVP/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST&cmd=login&errorCode=105&languageCd=ENG");
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
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_AP_DN_Q_WRK_CHECK_BOX$0")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS07$182$")).click();
			    driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS03")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS04")).click();
			    driver.findElement(By.id("OAA_DYNAMIC_QSN_OAA_DYN_ANS05$205$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
			    driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
			    Thread.sleep(500);
			    new Select(driver.findElement(By.id("OAA_DYN_ANSWERS_OAA_ANSWER_ID$0"))).selectByVisibleText("None");
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_HISP_LAT_Y$17$")).click();
			    driver.findElement(By.id("OAA_ETHNICITY_SCC_IS_BLK_AFAM")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_CRIMINAL_QA_OAA_CRIMINAL_QUES1$18$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_AP_DN_Q_WRK_SELECTED$1")).click();
			    driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
			    Thread.sleep(500);
			    new Select(driver.findElement(By.id("OAA_ACAD_INTRST_ADMIT_TERM"))).selectByVisibleText(dataList.get(j).getTerm());
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_EDU_HISTORY_OAA_HIGH_SCH_OPTNS$0")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_SEARCH_PB$0")).click();
			    new Select(driver.findElement(By.id("OAA_ORG_SRCH_STATE"))).selectByVisibleText("Illinois");
			    driver.findElement(By.id("OAA_ORG_SRCH_SCHOOL")).clear();
			    driver.findElement(By.id("OAA_ORG_SRCH_SCHOOL")).sendKeys("normal west");
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_ORG_SRCH_SEARCH_PB")).click();
			    driver.findElement(By.id("OAA_DERIVED_WRK_LINK2$0")).click();
			    Thread.sleep(500);
			    new Select(driver.findElement(By.id("OAA_EDU_HISTORY_GPA_TYPE$0"))).selectByVisibleText("4 Point Scale");
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_EDU_HISTORY_EXT_GPA$0")).clear();
			    driver.findElement(By.id("OAA_EDU_HISTORY_EXT_GPA$0")).sendKeys("3.25");
			    Thread.sleep(500);
			    driver.findElement(By.cssSelector("img[alt=\"Choose a date (Alt+5)\"]")).click();
			    new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2009");
			    driver.findElement(By.linkText("21")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_EDU_HIST1_LS_SCHOOL_TYPE$0")).click();
			    driver.findElement(By.id("OAA_EDU_HIST1_SEARCH_BTN$0")).click();
			    driver.findElement(By.id("OAA_ORG_SRCH_SCHOOL")).clear();
			    driver.findElement(By.id("OAA_ORG_SRCH_SCHOOL")).sendKeys("Illinois State");
			    driver.findElement(By.id("OAA_ORG_SRCH_SEARCH_PB")).click();
			    Thread.sleep(500);
			    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
			    driver.findElement(By.id("#ICOK")).click();
			    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | TargetContent | ]]
			    new Select(driver.findElement(By.id("OAA_ORG_SRCH_STATE"))).selectByVisibleText("Illinois");
			    driver.findElement(By.id("OAA_ORG_SRCH_SEARCH_PB")).click();
			    driver.findElement(By.id("LINK1$0")).click();
			    driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[2]")).click();
			    new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2011");
			    new Select(driver.findElement(By.name("PTMonth"))).selectByVisibleText("May");
			    driver.findElement(By.linkText("16")).click();
			    Thread.sleep(500);
			    driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[3]")).click();
			    new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
			    new Select(driver.findElement(By.name("PTMonth"))).selectByVisibleText("April");
			    driver.findElement(By.xpath("//table[@id='bodyCalendar']/tbody/tr[2]/td[3]")).click();
			    Thread.sleep(500);
			    new Select(driver.findElement(By.id("OAA_EDU_HIST1_DEGREE$0"))).selectByVisibleText("Bachelor's Degree");
			    driver.findElement(By.xpath("(//img[@alt='Choose a date (Alt+5)'])[4]")).click();
			    new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
			    driver.findElement(By.linkText("15")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$1$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_NEXT$3$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_SUBMIT_CONF_CONFIRMED")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_SAVEBTN$6$")).click();
			    Thread.sleep(500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_SUBMITTED")).click();
			    Thread.sleep(3500);
			    driver.findElement(By.id("OAA_DERIVED_WRK_OK_BTN")).click();
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
