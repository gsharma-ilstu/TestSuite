package isu.common;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class GradCommonRecomm {

	private WebDriver driver;
	private String baseUrl;
	/*private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();*/
	String URL1,URL2;
	PropertiesUtil propUtil ;
	InputStream input;
	WebElement password;
	String passwordValue;
	
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
		
		
	}
	@Test
	public void GradMastersInArtsRecommTest()  {

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


				URL1=new String (propUtil.getPropertyValue("gmail.recommendation.url", input));
				driver.get(URL1); 
				//driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/h/1aodjj7t5oevk/?zy%3Dg%26f%3D1&scc=1&ltmpl=default&ltmplcache=2&emr=1");
				    assertEquals("Gmail", driver.getTitle());
				    driver.findElement(By.id("Email")).clear();
				    driver.findElement(By.id("Email")).sendKeys(gradDataList.get(j).getRecomWorkEmail());
				    driver.findElement(By.id("Passwd")).clear();
				    driver.findElement(By.id("Passwd")).sendKeys("Reggie2014");
				    driver.findElement(By.id("signIn")).click();
				    driver.findElement(By.name("q")).clear();
				    driver.findElement(By.name("q")).sendKeys("Recommendation");
				    driver.findElement(By.name("nvp_site_mail")).click();
				    driver.findElement(By.cssSelector("span.ts")).click();
				    password = driver.findElement(By.className("msg"));
				    passwordValue = password.getText().trim();
				    		
				    passwordValue = passwordValue.substring(passwordValue.indexOf("Password:"),passwordValue.indexOf("Password:")+17);
				    passwordValue = passwordValue.substring(passwordValue.length()-6,passwordValue.length());
				    
				    
				    input= propUtil.getPropertyFile("url.properties");
				    URL2=new String(propUtil.getPropertyValue("cs.test.recommendation.url", input));
					driver.get(URL2); 
				    //driver.findElement(By.linkText("https://csisuvtst-dmz.oracleoutsourcing.com/psc/TISUVJ/EMPLOYEE/PSFT_CS/c/OAA_ONLINE_APPLICATION.OAA_RECMNDER_LOGIN.GBL?Page=OAA_APPLICATION26&Action=U&OAA_REC_TEMPLTE_ID=ISU_RECOMMENDAT")).click();
				   // driver.get("https://csisuvtst-dmz.oracleoutsourcing.com/psc/TISUVJ/EMPLOYEE/PSFT_CS/c/OAA_ONLINE_APPLICATION.OAA_RECMNDER_LOGIN.GBL?Page=OAA_APPLICATION26&Action=U&OAA_REC_TEMPLTE_ID=ISU_RECOMMENDAT");
				    //driver.findElement(By.linkText("'"+propUtil.getPropertyValue("cs.qa.recommendation.url", input)+"'")).click();
				    /*ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs2.get(1));*/
				    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _blank | 30000]]
				    driver.findElement(By.id("OAA_SIGNON_WRK_EMAIL_ADDR")).clear();
				    driver.findElement(By.id("OAA_SIGNON_WRK_EMAIL_ADDR")).sendKeys(gradDataList.get(j).getRecomWorkEmail());
				    driver.findElement(By.id("OAA_SIGNON_WRK_OPERPSWD")).clear();
				    driver.findElement(By.id("OAA_SIGNON_WRK_OPERPSWD")).sendKeys(passwordValue);
				    driver.findElement(By.id("OAA_RECMDER_WRK_BUTTON1")).click();
				    assertEquals("Recommendations", driver.getTitle());
				    driver.findElement(By.id("DESCR1$0")).click();
				    driver.findElement(By.id("OAA_RECOMM_ATCH_COMMENTS$0")).clear();
				    driver.findElement(By.id("OAA_RECOMM_ATCH_COMMENTS$0")).sendKeys("Submitting Recommendation");
				    driver.findElement(By.id("OAA_RECOMM_WRK_SEND_OK")).click();
				    driver.findElement(By.id("#ICOK")).click();
				    driver.switchTo().frame("ptModFrame_0");
				    driver.findElement(By.id("OAA_RECOMM_WRK_SAVE_BTN")).click();
				    driver.findElement(By.id("OAA_RECOMM_WRK_OK_BTN")).click();
				    driver.findElement(By.id("OAA_HDR_LNK_WRK_HRS_LOGOUT_PB")).click();
				   /* driver.switchTo().window(tabs2.get(1));
					driver.close();
					driver.switchTo().window(tabs2.get(0));*/
				    driver.findElement(By.cssSelector("span.gb_4.gbii")).click();
				    driver.findElement(By.id("gb_71")).click();
				    assertEquals("Google Accounts", driver.getTitle());
				    assertEquals("Google Accounts", driver.getTitle());
				    assertEquals("Gmail", driver.getTitle());
			  
			}

		}
		catch(IOException ex){
			System.err.println("Error reading excel file while testing Login User" +ex.getMessage()); 
		}
		
	}


	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
