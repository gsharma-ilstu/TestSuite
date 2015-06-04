package isu.common;

import static org.junit.Assert.*;
import isu.data.CommonAutomatedData;
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

public class PasswordChange {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	Character[] invalidChars = {'#', '!', '$', '@', '%', '^', '&'};
	
	private static String ulid=null;
	private static String pwd=null;
	private static String newPwd=null;
	
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

	}


	@Test
	public void AccountCreationAndVerificationTest() {


		try {
			String[] workBookNames= {"passwordChange"};
			Util util = new Util();
			List<CommonAutomatedData> dataList = new ArrayList<CommonAutomatedData>();
			dataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data", "CommonAutomationData.xls", workBookNames,false,true);
			//dataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data", "AutomationData.xls", workBookNames,false,true);

			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows "+dataList.size());
			for(int j=0;j<1;j++)
			{
				 ulid=dataList.get(0).getUlid();
				 pwd=dataList.get(0).getPass();
				 newPwd=dataList.get(0).getNewPassword();
				 changePassword(ulid,pwd,newPwd);
					
			}

		}
		catch(IOException io){
			System.err.println("Error occured while reading the excel sheet for creating account "+io.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


	private void changePassword(String ulid,String pwd,String newPwd) throws InterruptedException, IOException {
		
			URL1 = propUtil.getPropertyValue("self.service.url", input);
			driver.get(URL1);
			//driver.get("https://account-test.illinoisstate.edu/selfservice/login/");
		    assertEquals("Redbird Account :: Illinois State University", driver.getTitle());
		    driver.findElement(By.id("ulid")).clear();
		    driver.findElement(By.id("ulid")).sendKeys(ulid);
		    driver.findElement(By.id("pw")).clear();
		    driver.findElement(By.id("pw")).sendKeys(pwd);
		    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		    assertEquals("Redbird Account :: Illinois State University", driver.getTitle());
		    driver.findElement(By.xpath("(//a[contains(text(),'Account Management')])")).click();
		    driver.findElement(By.xpath("(//a[contains(text(),'change')])[2]")).click();
		    assertEquals("Redbird Account :: Illinois State University", driver.getTitle());
		    driver.findElement(By.id("newPassword")).clear();
		    driver.findElement(By.id("newPassword")).sendKeys(newPwd);
		    driver.findElement(By.id("newPassword2")).clear();
		    driver.findElement(By.id("newPassword2")).sendKeys(newPwd);
		    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		    driver.findElement(By.linkText("Account Management")).click();
		    assertEquals("Redbird Account :: Illinois State University", driver.getTitle());
		    driver.findElement(By.xpath("(//a[contains(text(),'update')])[3]")).click();
		    
		    Thread.sleep(1000);
		    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
			
		    driver.findElement(By.id("logon")).clear();
		    driver.findElement(By.id("logon")).sendKeys(ulid);
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys(newPwd);
		    driver.findElement(By.id("qa1")).clear();
		    driver.findElement(By.id("qa1")).sendKeys("Normal");
		    new Select(driver.findElement(By.id("q2"))).selectByVisibleText("What is your favorite color?");
		    driver.findElement(By.cssSelector("#q2 > option[value=\"8\"]")).click();
		    driver.findElement(By.id("qa2")).clear();
		    driver.findElement(By.id("qa2")).sendKeys("Black");
		    driver.findElement(By.id("qa3")).clear();
		    driver.findElement(By.id("qa3")).sendKeys("Test");
		    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		    driver.switchTo().window(tabs2.get(1));
			driver.close();
			driver.switchTo().window(tabs2.get(0));
		    driver.findElement(By.linkText("Account Management")).click();
		    assertEquals("Redbird Account :: Illinois State University", driver.getTitle());
	}


	@AfterTest
	public void afterTest() throws InterruptedException, IOException {
		Thread.sleep(1000);
		//revert the password
		changePassword(ulid,newPwd,pwd);
		driver.quit();
	}


/*	public void accountVerificationMethod(List<CommonAutomatedData> dataList,int j) throws InterruptedException {

		driver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/");
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(dataList.get(0).getPersonalEmail());
		assertTrue("Invalid email" , dataList.get(j).getPersonalEmail().contains("@"));
		driver.findElement(By.id("Passwd")).clear();
		driver.findElement(By.id("Passwd")).sendKeys(dataList.get(0).getPersonalPasswd());
		driver.findElement(By.id("signIn")).click();
		driver.findElement(By.name("q")).clear();
		driver.findElement(By.name("q")).sendKeys("Verification");
		driver.findElement(By.name("nvp_site_mail")).click();
		driver.findElement(By.cssSelector("span.ts")).click();
		driver.findElement(By.linkText("Verify this email address and continue your registration")).click();

		Thread.sleep(3000);

		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));

		driver.findElement(By.id("electronicSignatureConsent-0")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("initials")).clear();
		driver.findElement(By.id("initials")).sendKeys(dataList.get(j).getInitials());
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("appropriateUsePolicy-1")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("surveyParticipation-1")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		// driver.findElement(By.xpath("//body[@id='ng-app']/div[7]/div/div[3]/form/fieldset/div[4]/div/button"));
		Thread.sleep(2000);

		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(dataList.get(j).getPassword());
		driver.findElement(By.id("passwordconfirmation")).clear();
		driver.findElement(By.id("passwordconfirmation")).sendKeys(dataList.get(j).getPassword());
		driver.findElement(By.id("answer1")).clear();
		driver.findElement(By.id("answer1")).sendKeys(dataList.get(j).getAnswer1());
		new Select(driver.findElement(By.id("securityQuestion2"))).selectByVisibleText("What is your mother's maiden name?");
		driver.findElement(By.id("answer2")).clear();
		driver.findElement(By.id("answer2")).sendKeys(dataList.get(j).getAnswer2());
		driver.findElement(By.id("answer3")).clear();
		driver.findElement(By.id("answer3")).sendKeys(dataList.get(j).getAnswer3());
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		Thread.sleep(6000);

		driver.switchTo().window(tabs2.get(1));
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		driver.findElement(By.name("q")).clear();
	    driver.findElement(By.name("q")).sendKeys("Illinois State University");
	    driver.findElement(By.name("nvp_site_mail")).click();
	    assertEquals("Gmail - Search results for: Illinois State University", driver.getTitle());
	    driver.findElement(By.name("t")).click();
	    driver.findElement(By.name("nvp_a_tr")).click();
	    assertEquals("Gmail - Search results for: Illinois State University", driver.getTitle());
	    driver.findElement(By.id("gb_71")).click();
		//driver.findElement(By.linkText("Sign out")).click();



	}
*/	}
