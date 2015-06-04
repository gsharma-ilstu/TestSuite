package isu.visitor;

import static org.junit.Assert.assertEquals;
import isu.data.GradAutomatedData;
import isu.data.MaintenanceAutomatedData;
import isu.data.UnderGradAutomatedData;
import isu.util.MaintenanceUtil;
import isu.util.Util;

import java.io.FileInputStream;
import java.io.IOException;
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

public class MaintainDualUnivHS {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	WebElement currentDOB;
	WebElement currentEmail;
	String currentDOBValue;
	String currentEmailValue;
	WebElement currentDueDate;
	String currentDudeDateValue;
	List<MaintenanceAutomatedData> maintDataList;
	
	
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
	public void MaintainGradMastersInArtsAppTest()  {

		try{ 
			String[] workBookNames={"maintainDualUnivHS","maintainTransferGrad","maintainReadmitGrad"};
			Util util = new Util();
			MaintenanceUtil maintUtil = new MaintenanceUtil();
			maintDataList = new ArrayList<MaintenanceAutomatedData>();
			maintDataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data", "VisitorAutomationData.xls", workBookNames,true,false);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows "+maintDataList.size());
			for(int j=0;j<1;j++)
			{

				String whandle = driver.getWindowHandle();
				//Change DOB & Email address
				//maintUtil.changeDOBandEmail(maintDataList,j,whandle,driver);
			   
				//change GRE score
				//maintUtil.changeScore(maintDataList,j,whandle,driver);
			    
				// change Names and checklist
				//maintUtil.changeNamesAndChecklist(maintDataList,j,whandle,driver);
				
				//change Acad plan and process admit
				maintUtil.changeAcadPlanAndProcessAdmit(maintDataList,j,whandle,driver);
			     			
				
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
