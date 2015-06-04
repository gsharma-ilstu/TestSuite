package isu.grad;

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

public class MaintainGradMastersInArtsApp {

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
		/*ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		System.setProperty("webdriver.firefox.bin", "Q://LEAP_Test_Cases//Automation//Tools//Mozilla Firefox//firefox.exe");
		profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");  
		profile.setPreference("capability.policy.default.Window.frameElement.get","allAccess");  */
		driver = new FirefoxDriver();
		baseUrl = "https://account-test.illinoisstate.edu/selfservice/registration/begin";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void MaintainGradMastersInArtsAppTest()  {

		try{ 
			String[] workBookNames={"maintainApplication","maintainTransferGrad","maintainReadmitGrad"};
			Util util = new Util();
			MaintenanceUtil maintUtil = new MaintenanceUtil();
			maintDataList = new ArrayList<MaintenanceAutomatedData>();
			maintDataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data", "GraduateAutomationData.xls", workBookNames,true,false);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows "+maintDataList.size());
			for(int j=0;j<1;j++)
			{

				String whandle = driver.getWindowHandle();
				//Change DOB & Email address
				maintUtil.changeDOBandEmail(maintDataList,j,whandle,driver);
			   
				//change GRE score
				maintUtil.changeScore(maintDataList,j,whandle,driver);
			    
				// change Names and checklist
				maintUtil.changeNamesAndChecklist(maintDataList,j,whandle,driver);
				
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


		try{ 

			String[] workBookNames={"maintainApplication","maintainTransferUnderGrad","maintainReadmitUnderGrad"};
			Util util = new Util();
			MaintenanceUtil maintUtil = new MaintenanceUtil();
			/*List<MaintenanceAutomatedData> maintDataList = new ArrayList<MaintenanceAutomatedData>();
			maintDataList=util.readFile("Q://LEAP_Test_Cases//Automation//Data", "AutomationData.xls", workBookNames,true);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Total rows "+maintDataList.size());*/
			for(int j=0;j<1;j++)
			{

				String whandle = driver.getWindowHandle();
				maintDataList.get(j).setmNewDOB(currentDOBValue);
				maintDataList.get(j).setmNewEmail(currentEmailValue);
				//Revert DOB & Email address
				maintUtil.changeDOBandEmail(maintDataList,j,whandle,driver);
			   
			}

		}
		catch(InterruptedException ie){
			System.err.println("Error in Thread in Login User" +ie.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		driver.quit();
	}

	/*public void changeDOBandEmail(List<UnderGradAutomatedData> accountCreationDataList,int j,String whandle) throws InterruptedException{
		driver.get("https://csisuvtst.oracleoutsourcing.com/psp/TISUVJ/EMPLOYEE/HRMS/?cmd=logout");
	    assertEquals("Oracle | PeopleSoft Enterprise Sign-in", driver.getTitle());
	    driver.findElement(By.id("userid")).clear();
	    driver.findElement(By.id("userid")).sendKeys(accountCreationDataList.get(j).getmUserId());
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys(accountCreationDataList.get(j).getmPwd());
	    driver.findElement(By.name("Submit")).click();
	    Thread.sleep(3000);
	    assertEquals("Employee-facing registry content", driver.getTitle());
	    driver.findElement(By.id("pthnavbca_PORTAL_ROOT_OBJECT")).click();
	    driver.findElement(By.id("fldra_HCCC_BUILD_COMMUNITY")).click();
	    driver.findElement(By.id("fldra_HCCC_PERSONAL_INFORMATION")).click();
	    driver.findElement(By.linkText("Add/Update a Person")).click();
	    
	    Thread.sleep(5000);
	    assertEquals("Add/Update a Person", driver.getTitle());
	    driver.switchTo().frame("TargetContent");
	    driver.findElement(By.id("#ICClear")).click();
	    driver.findElement(By.id("PEOPLE_SRCH_LAST_NAME_SRCH")).click();;
	    driver.findElement(By.id("PEOPLE_SRCH_LAST_NAME_SRCH")).clear();
	    driver.findElement(By.id("PEOPLE_SRCH_LAST_NAME_SRCH")).sendKeys(accountCreationDataList.get(j).getmLastName());
	    driver.findElement(By.id("PEOPLE_SRCH_FIRST_NAME_SRCH")).click();
	    driver.findElement(By.id("PEOPLE_SRCH_FIRST_NAME_SRCH")).clear();
	    driver.findElement(By.id("PEOPLE_SRCH_FIRST_NAME_SRCH")).sendKeys(accountCreationDataList.get(j).getmFirstName());
	    driver.findElement(By.id("#ICSearch")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("RESULT2$0")).click();
	    Thread.sleep(4000);
	    currentDOB = driver.findElement(By.id("DERIVED_PERDATA_BIRTHDATE"));
	    currentDOBValue = currentDOB.getAttribute("value");
	    driver.findElement(By.id("DERIVED_PERDATA_BIRTHDATE")).clear();
	    driver.findElement(By.id("DERIVED_PERDATA_BIRTHDATE")).sendKeys(accountCreationDataList.get(j).getmNewDOB());
	    Thread.sleep(1000);
	    WebElement element = driver.findElement(By.id("SCC_EMAIL_H_EMAIL_ADDR$0"));
	    System.out.println(">>>>>>>>>>>>>>>>>"+element.getAttribute("value"));
	    currentEmail =driver.findElement(By.id("SCC_EMAIL_H_EMAIL_ADDR$0"));
	    currentEmailValue = currentEmail.getAttribute("value");
	    driver.findElement(By.id("SCC_EMAIL_H_EMAIL_ADDR$0")).clear();
	    driver.findElement(By.id("SCC_EMAIL_H_EMAIL_ADDR$0")).sendKeys(accountCreationDataList.get(j).getmNewEmail());
	    driver.findElement(By.id("#ICSave")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.id("#ICList")).click();
	    driver.switchTo().window(whandle);
	}
	
	public void changeScore(List<UnderGradAutomatedData> accountCreationDataList,int j,String whandle) throws InterruptedException{
		Thread.sleep(2000);
	    driver.findElement(By.id("pthnavbca_PORTAL_ROOT_OBJECT")).click();
	    driver.findElement(By.id("HCAD_STUDENT_ADMISSIONS")).click();
	    driver.findElement(By.id("fldra_HCAD_APPLICATION_MAINTENANCE")).click();
	    driver.findElement(By.cssSelector("#crefli_HC_ADM_APPL_MAINTNCE_GBL1 > a.ptntop")).click();
	    Thread.sleep(1000);
	    assertEquals("Maintain Applications", driver.getTitle());
	    driver.switchTo().frame("TargetContent");
	    driver.findElement(By.id("#ICClear")).click();
	    driver.findElement(By.id("ADM_MAINT_SCTY_INSTITUTION")).clear();
	    driver.findElement(By.id("ADM_MAINT_SCTY_INSTITUTION")).sendKeys(accountCreationDataList.get(j).getmInstitution());
	    driver.findElement(By.id("ADM_MAINT_SCTY_ACAD_CAREER")).clear();
	    driver.findElement(By.id("ADM_MAINT_SCTY_ACAD_CAREER")).sendKeys(accountCreationDataList.get(j).getmAcadCareer());
	    driver.findElement(By.id("ADM_MAINT_SCTY_LAST_NAME_SRCH")).clear();
	    driver.findElement(By.id("ADM_MAINT_SCTY_LAST_NAME_SRCH")).sendKeys(accountCreationDataList.get(j).getmLastName());
	    driver.findElement(By.id("ADM_MAINT_SCTY_FIRST_NAME_SRCH")).clear();
	    driver.findElement(By.id("ADM_MAINT_SCTY_FIRST_NAME_SRCH")).sendKeys(accountCreationDataList.get(j).getmFirstName());
	    driver.findElement(By.id("#ICSearch")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.id("SEARCH_RESULT1")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//div[@id='PSTAB']/table/tbody/tr/td[5]/a/span/abbr")).click();
	    new Select(driver.findElement(By.id("ADM_GO_WRK_ADM_APL_MAINT_PB"))).selectByVisibleText("Test Results");
	    driver.findElement(By.id("ADM_GO_WRK_ADM_GO_PB")).click();
	    Thread.sleep(4000);
	    driver.findElement(By.name("STDNT_TEST_COMP$new$2$$img$0")).click();
	    driver.findElement(By.id("STDNT_TEST_COMP_TEST_COMPONENT$3")).clear();
	    driver.findElement(By.id("STDNT_TEST_COMP_TEST_COMPONENT$3")).sendKeys(accountCreationDataList.get(j).getmTestComponent());
	    driver.findElement(By.id("STDNT_TEST_COMP_SCORE$3")).click();
	    driver.findElement(By.id("STDNT_TEST_COMP_SCORE$3")).clear();
	    driver.findElement(By.id("STDNT_TEST_COMP_SCORE$3")).sendKeys(accountCreationDataList.get(j).getmScore());
	    driver.findElement(By.name("STDNT_TEST_COMP_TEST_DT$prompt$img$3")).click();
	    new Select(driver.findElement(By.name("PTYear"))).selectByVisibleText("2013");
	    driver.findElement(By.linkText("11")).click();
	    driver.findElement(By.id("STDNT_TEST_COMP_LS_DATA_SOURCE$3")).click();
	    new Select(driver.findElement(By.id("STDNT_TEST_COMP_LS_DATA_SOURCE$3"))).selectByVisibleText("Self-Rpted");
	    driver.findElement(By.xpath("(//option[@value='SLF'])[5]")).click();
	    driver.findElement(By.id("STDNT_TEST_COMP_EXT_ACAD_LEVEL$3")).click();
	    new Select(driver.findElement(By.id("STDNT_TEST_COMP_EXT_ACAD_LEVEL$3"))).selectByVisibleText("Unknown");
	    driver.findElement(By.xpath("(//option[@value='0'])[5]")).click();
	    driver.findElement(By.id("#ICSave")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.id("#ICList")).click();
	    driver.switchTo().window(whandle);
	    driver.findElement(By.id("pthdr2logout")).click();
	    assertEquals("Oracle | PeopleSoft Enterprise Sign-in", driver.getTitle());
	}
	public void changeNamesAndChecklist(List<UnderGradAutomatedData> accountCreationDataList,int j,String whandle) throws InterruptedException{
		driver.get("https://csisuvtst.oracleoutsourcing.com/psp/TISUVJ/EMPLOYEE/HRMS/?cmd=logout");
		Thread.sleep(1000);
		assertEquals("Oracle | PeopleSoft Enterprise Sign-in", driver.getTitle());
		driver.findElement(By.id("userid")).clear();
		driver.findElement(By.id("userid")).sendKeys(accountCreationDataList.get(j).getMtUserId());
		driver.findElement(By.id("pwd")).clear();
		driver.findElement(By.id("pwd")).sendKeys(accountCreationDataList.get(j).getMtPwd());
		driver.findElement(By.name("Submit")).click();
		Thread.sleep(1000);
		assertEquals("Employee-facing registry content", driver.getTitle());
		driver.findElement(By.id("pthnavbca_PORTAL_ROOT_OBJECT")).click();
		Thread.sleep(500);
		driver.findElement(By.id("fldra_HCCC_BUILD_COMMUNITY")).click();
		Thread.sleep(500);
		driver.findElement(By.id("fldra_HCCC_PERSONAL_INFORMATION")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Add/Update a Person")).click();
		Thread.sleep(1000);
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _parent | 30000]]
		assertEquals("Add/Update a Person", driver.getTitle());
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame | TargetContent | ]]
		driver.switchTo().frame("TargetContent");
		driver.findElement(By.id("#ICClear")).click();
		driver.findElement(By.id("PEOPLE_SRCH_LAST_NAME_SRCH")).clear();
		driver.findElement(By.id("PEOPLE_SRCH_LAST_NAME_SRCH")).sendKeys(accountCreationDataList.get(j).getMtLastName());
		driver.findElement(By.id("PEOPLE_SRCH_FIRST_NAME_SRCH")).clear();
		driver.findElement(By.id("PEOPLE_SRCH_FIRST_NAME_SRCH")).sendKeys(accountCreationDataList.get(j).getMtFirstName());
		driver.findElement(By.id("#ICSearch")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("DERIVED_PERDATA_NAME_LINK")).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("DERIVED_SCC_NM_NAME_TYPE"))).selectByVisibleText(accountCreationDataList.get(j).getMtNewNameType());
		Thread.sleep(500);
		driver.findElement(By.id("DERIVED_SCC_NM_FIRST_NAME")).clear();
		driver.findElement(By.id("DERIVED_SCC_NM_FIRST_NAME")).sendKeys(accountCreationDataList.get(j).getMtNewFirstName());
		driver.findElement(By.id("DERIVED_SCC_NM_LAST_NAME")).clear();
		driver.findElement(By.id("DERIVED_SCC_NM_LAST_NAME")).sendKeys(accountCreationDataList.get(j).getMtNewLastName());
		driver.findElement(By.id("DERIVED_SCC_NM_SCC_ADDR_SUBMT_BTN")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("#ICSave")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("DERIVED_PERDATA_NAME_LINK")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.id("DERIVED_SCC_NM_NAME_TYPE"))).selectByVisibleText(accountCreationDataList.get(j).getMtAddNameType());
		driver.findElement(By.id("DERIVED_SCC_NM_FIRST_NAME")).clear();
		driver.findElement(By.id("DERIVED_SCC_NM_FIRST_NAME")).sendKeys(accountCreationDataList.get(j).getMtAddFirstName());
		driver.findElement(By.id("DERIVED_SCC_NM_LAST_NAME")).clear();
		driver.findElement(By.id("DERIVED_SCC_NM_LAST_NAME")).sendKeys(accountCreationDataList.get(j).getMtAddLastName());
		driver.findElement(By.id("DERIVED_SCC_NM_SCC_ADDR_SUBMT_BTN")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("#ICSave")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("#ICList")).click();
		driver.switchTo().window(whandle);
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
		driver.findElement(By.id("pthnavbca_HCCC_BUILD_COMMUNITY")).click();
		Thread.sleep(500);
		driver.findElement(By.id("fldra_HCCC_CHECKLISTS")).click();
		Thread.sleep(500);
		driver.findElement(By.id("fldra_HCCC_CHECKLISTS_PERSON")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Checklist Management - Person")).click();
		Thread.sleep(500);
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame | TargetContent | ]]
		driver.switchTo().frame("TargetContent");
		driver.findElement(By.id("#ICClear")).click();
		driver.findElement(By.id("CHKLST_SRCH_LAST_NAME_SRCH")).clear();
		driver.findElement(By.id("CHKLST_SRCH_LAST_NAME_SRCH")).sendKeys(accountCreationDataList.get(j).getMtLastName());
		driver.findElement(By.id("CHKLST_SRCH_FIRST_NAME_SRCH")).clear();
		driver.findElement(By.id("CHKLST_SRCH_FIRST_NAME_SRCH")).sendKeys(accountCreationDataList.get(j).getMtFirstName());
		driver.findElement(By.id("#ICSearch")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("RESULT15$0")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("abbr.PTUNDERLINE")).click();
		driver.findElement(By.name("$ICField$13$$new$3$$img$0")).click();
		driver.findElement(By.id("PERSON_CHK_ITEM_CHKLST_ITEM_CD$4")).clear();
		driver.findElement(By.id("PERSON_CHK_ITEM_CHKLST_ITEM_CD$4")).sendKeys(accountCreationDataList.get(j).getMtCheckListItem());
		driver.findElement(By.id("PERSON_CHK_ITEM_DUE_DT$4")).clear();
		currentDueDate = driver.findElement(By.id("PERSON_CHK_ITEM_DUE_DT$1"));
		currentDudeDateValue = currentDueDate.getAttribute("value");
		driver.findElement(By.id("PERSON_CHK_ITEM_DUE_DT$4")).sendKeys(currentDudeDateValue);
		driver.findElement(By.id("#ICSave")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("#ICList")).click();
		driver.switchTo().window(whandle);
	}
	public void changeAcadPlanAndProcessAdmit(List<UnderGradAutomatedData> accountCreationDataList,int j,String whandle) throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.id("pthnavbca_PORTAL_ROOT_OBJECT")).click();
		Thread.sleep(500);
		driver.findElement(By.id("fldra_HCAD_STUDENT_ADMISSIONS")).click();
		Thread.sleep(500);
		driver.findElement(By.id("fldra_HCAD_APPLICATION_MAINTENANCE")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("#crefli_HC_ADM_APPL_MAINTNCE_GBL1 > a.ptntop")).click();
		Thread.sleep(500);
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _self | 30000]]
		assertEquals("Maintain Applications", driver.getTitle());
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame | TargetContent | ]]
		driver.switchTo().frame("TargetContent");
		driver.findElement(By.id("#ICClear")).click();
		driver.findElement(By.id("ADM_MAINT_SCTY_INSTITUTION")).clear();
		driver.findElement(By.id("ADM_MAINT_SCTY_INSTITUTION")).sendKeys(accountCreationDataList.get(j).getMtInstitution());
		driver.findElement(By.id("ADM_MAINT_SCTY_ACAD_CAREER")).clear();
		driver.findElement(By.id("ADM_MAINT_SCTY_ACAD_CAREER")).sendKeys(accountCreationDataList.get(j).getMtAcadCareer());
		driver.findElement(By.id("ADM_MAINT_SCTY_LAST_NAME_SRCH")).clear();
		driver.findElement(By.id("ADM_MAINT_SCTY_LAST_NAME_SRCH")).sendKeys(accountCreationDataList.get(j).getMtLastName());
		driver.findElement(By.id("ADM_MAINT_SCTY_FIRST_NAME_SRCH")).clear();
		driver.findElement(By.id("ADM_MAINT_SCTY_FIRST_NAME_SRCH")).sendKeys(accountCreationDataList.get(j).getMtFirstName());
		driver.findElement(By.id("#ICSearch")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("RESULT11$0")).click();
		Thread.sleep(1000);
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame | TargetContent | ]]
		driver.findElement(By.id("#ICCorrection")).click();
		Thread.sleep(1500);
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]
		driver.findElement(By.xpath("//div[@id='PSTAB']/table/tbody/tr/td[4]/a/span")).click();
		driver.findElement(By.id("ADM_APPL_PROG_PROG_ACTION$0")).clear();
		driver.findElement(By.id("ADM_APPL_PROG_PROG_ACTION$0")).sendKeys(accountCreationDataList.get(j).getMtProgAction());
		driver.findElement(By.id("ADM_APPL_PROG_PROG_REASON$0")).clear();
		driver.findElement(By.id("ADM_APPL_PROG_PROG_REASON$0")).sendKeys("REIN");
		driver.findElement(By.id("ADM_APPL_PLAN_ACAD_PLAN$0")).clear();
		driver.findElement(By.id("ADM_APPL_PLAN_ACAD_PLAN$0")).sendKeys(accountCreationDataList.get(j).getMtAcadPlan());
		driver.findElement(By.id("#ICSave")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("#ICList")).click();
		driver.switchTo().window(whandle);
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
		driver.findElement(By.id("pthdr2logout")).click();
		Thread.sleep(1500);
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _parent | 30000]]
		assertEquals("Oracle | PeopleSoft Enterprise Sign-in", driver.getTitle());
	}*/
}
