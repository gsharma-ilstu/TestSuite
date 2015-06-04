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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

public class AccountCreationVerification {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	Character[] invalidChars = {'#', '!', '$', '@', '%', '^', '&'};
	String URL1,URL2,URL3;
	PropertiesUtil propUtil;
	InputStream input;

	@BeforeTest
	public void beforeTest() {
		/*ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		System.setProperty("webdriver.firefox.bin", "Q://LEAP_Test_Cases//Automation//Tools//Mozilla Firefox//firefox.exe");
		profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");  
		profile.setPreference("capability.policy.default.Window.frameElement.get","allAccess");  */
		driver = new FirefoxDriver();
		baseUrl = "https://account-test.illinoisstate.edu/selfservice/registration/begin";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	@Test
	public void AccountCreationAndVerificationTest() {


		try {
			propUtil = new PropertiesUtil();
			input= propUtil.getPropertyFile("url.properties");
			String[] workBookNames= {"accCreationVerification"};
			Util util = new Util();
			List<CommonAutomatedData> accCreationVerificationDataList = new ArrayList<CommonAutomatedData>();
			accCreationVerificationDataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data", "CommonAutomationData.xls", workBookNames,false,true);
			//dataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data", "AutomationData.xls", workBookNames,false,true);

			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows "+accCreationVerificationDataList.size());
			for(int j=0;j<1;j++)
			{
				accountCreationMethod(accCreationVerificationDataList,j);
				accountVerificationMethodNew(accCreationVerificationDataList,j);

				Thread.sleep(3000);
				String sql ="SELECT ISU_LOGON FROM TEST_ISU.ILSTU_IDENTITY_REG_REQUESTS where UPPER(NAME_FIRST) =UPPER(\'"+accCreationVerificationDataList.get(j).getFirstName()+"\')"+
						" AND UPPER(NAME_LAST)=UPPER(\'"+accCreationVerificationDataList.get(j).getLastName()+"\') AND REQUEST_COMPLETED_ON IS NOT NULL";

				String userId = getUserName(sql);

				writeToExcel("Q://LEAP_Test_Cases//Automation//Data", "GraduateAutomationData.xls", "createApplication",userId);
				writeToExcel("Q://LEAP_Test_Cases//Automation//Data", "GraduateAutomationData.xls", "createMusicApp",userId);
				writeToExcel("Q://LEAP_Test_Cases//Automation//Data", "GraduateAutomationData.xls", "transferUnderGrad",userId);
				writeToExcel("Q://LEAP_Test_Cases//Automation//Data", "GraduateAutomationData.xls", "readmitGrad",userId);
				writeToExcel("Q://LEAP_Test_Cases//Automation//Data", "GraduateAutomationData.xls", "userID",userId);
				
			}

		}
		catch(InterruptedException ex){
			System.err.println("Error occured while creating account "+ex.getMessage());
		}
		catch(IOException io){
			System.err.println("Error occured while reading the excel sheet for creating account "+io.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
	}

	public void accountCreationMethod(List<CommonAutomatedData> dataList,int j) throws InterruptedException, IOException{
		
		propUtil = new PropertiesUtil();
		input= propUtil.getPropertyFile("url.properties");
		URL1 = propUtil.getPropertyValue("account.creation.url", input);
		driver.get(URL1);  
		Thread.sleep(1000);
		//driver.get("https://account-test.illinoisstate.edu/selfservice/registration/begin");
		assertEquals("Redbird Account :: Illinois State University", driver.getTitle());
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(dataList.get(j).getEmailForCreation());
		Thread.sleep(1000);
		//assertTrue((driver.findElement(By.id("email"))).getText().contains("@"));
		assertTrue("Invalid email" , dataList.get(j).getEmailForCreation().contains("@"));
		driver.findElement(By.id("emailConfirmation")).clear();
		driver.findElement(By.id("emailConfirmation")).sendKeys(dataList.get(j).getEmailConfirmation());
		Thread.sleep(1000);
		assertTrue("Invalid email",dataList.get(j).getEmailConfirmation().contains("@"));
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys(dataList.get(j).getFirstName());
		Thread.sleep(1000);
		assertTrue("First Name has non-alphabetic characters",dataList.get(j).getFirstName().matches("[a-zA-Z](.*)"));
		driver.findElement(By.id("middleName")).clear();
		driver.findElement(By.id("middleName")).sendKeys(dataList.get(j).getMiddleName());
		Thread.sleep(1000);
		assertTrue("Middle Name has non-alphabetic characters",dataList.get(j).getMiddleName().matches("[a-zA-Z](.*)"));
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys(dataList.get(j).getLastName());
		Thread.sleep(1000);
		assertTrue("Last Name has non-alphabetic characters",dataList.get(j).getLastName().matches("[a-zA-Z](.*)"));
		new Select(driver.findElement(By.id("gender"))).selectByVisibleText(dataList.get(j).getGender());
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("dobMonth"))).selectByVisibleText(dataList.get(j).getDobMonth());
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("dobDay"))).selectByVisibleText(dataList.get(j).getDobDay());
		Thread.sleep(1000);
		driver.findElement(By.id("dobYear")).clear();
		driver.findElement(By.id("dobYear")).sendKeys(dataList.get(j).getDobYear());
		Thread.sleep(1000);
		assertTrue("DOB year can't be characters",dataList.get(j).getDobYear().matches("[0-9]{4}"));
		driver.findElement(By.id("birthCity")).clear();
		driver.findElement(By.id("birthCity")).sendKeys(dataList.get(j).getBirthCity());
		Thread.sleep(1000);
		assertTrue("DOB City can't be Numeric",dataList.get(j).getBirthCity().matches("[a-zA-Z](.*)"));
		driver.findElement(By.id("postalCode")).clear();
		driver.findElement(By.id("postalCode")).sendKeys(dataList.get(j).getPostalCode());
		Thread.sleep(1000);
		assertTrue("Postal code can't be characters",dataList.get(j).getPostalCode().matches("[0-9](.*)"));
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		//https://account-test.illinoisstate.edu/selfservice/registration/step2
		Thread.sleep(6500);
	}

	public void accountVerificationMethod(List<CommonAutomatedData> dataList,int j) throws InterruptedException, IOException {

		propUtil = new PropertiesUtil();
		input= propUtil.getPropertyFile("url.properties");
		URL2 = propUtil.getPropertyValue("gmail.verification.url", input);
		driver.get(URL2);  
	
		//driver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/");
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
	
	public void accountVerificationMethodNew(List<CommonAutomatedData> dataList,int j) throws InterruptedException, IOException {

		propUtil = new PropertiesUtil();
		input= propUtil.getPropertyFile("url.properties");
		Date date = new Date();
		
		//SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEE, d MMM yyyy hh:mm:ss a");
		//Thursday, 4 Dec 2014 10:48:07 AM
		SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEE, d MMM yyyy h:mm");
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate);
		
		URL3 = propUtil.getPropertyValue("email.catcher.url", input);
		driver.get(URL3); 
		Thread.sleep(2000);
		//driver.get("http://dev-app02.bits.illinoisstate.edu:10250/");
		driver.findElement(By.name("search")).clear();
		driver.findElement(By.name("search")).sendKeys(formattedDate);
		Thread.sleep(1000);
		///html/body/nav/table/tbody/tr[43]/td[1]/html/body/nav/table/tbody/tr[43]/td[1] /html/body/nav 
		//	By.xpath('//*[contains(@class,"selec_option")]/td[@text()="50"]') /html/body/nav/table/tbody/tr[47]
		//display: table-row; //nav[@id='messages']/table/tbody/tr[41]/td[3] /html/body/nav/table/tbody/tr 
		// driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[class='body']"))); 
		//driver.findElement(By.xpath("*//nav[@id='messages']/table/tbody/tr/td[@text='"+formattedDate+"']")).click();
		
		//driver.findElement(By.xpath("*//nav[@id='messages']/table/tbody/tr/td[3]")).click();
		driver.findElement(By.xpath("*//td[contains(text(),'"+formattedDate+"')]")).click();
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[class='body']")));
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
		Thread.sleep(1000);
		driver.findElement(By.id("passwordconfirmation")).clear();
		driver.findElement(By.id("passwordconfirmation")).sendKeys(dataList.get(j).getPassword());
		Thread.sleep(1000);
		driver.findElement(By.id("answer1")).clear();
		driver.findElement(By.id("answer1")).sendKeys(dataList.get(j).getAnswer1());
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("securityQuestion2"))).selectByVisibleText("What is your mother's maiden name?");
		Thread.sleep(1000);
		driver.findElement(By.id("answer2")).clear();
		driver.findElement(By.id("answer2")).sendKeys(dataList.get(j).getAnswer2());
		Thread.sleep(1000);
		driver.findElement(By.id("answer3")).clear();
		driver.findElement(By.id("answer3")).sendKeys(dataList.get(j).getAnswer3());
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		Thread.sleep(10000);

		driver.switchTo().window(tabs2.get(1));
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		//driver.findElement(By.linkText("Sign out")).click();



	}
	public String getUserName(String sql) throws SQLException, IOException {

		DBUtil dbUtil = new DBUtil();
		ResultSet rs = null;
		String userId = null;

		rs= dbUtil.getUserNameSet(sql);

		while (rs.next())
		{
			System.out.println("results: " + rs.getString(1));
			userId = rs.getString(1);
		}

		return userId;

	}

	public void writeToExcel(String fileLocation,String fileName,String workbookName,String userId) throws SQLException, IOException {

		Util Util = new Util();
		Util.writeCell(fileLocation,fileName,workbookName,userId);


	}

}
