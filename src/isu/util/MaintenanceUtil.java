package isu.util;

import static org.junit.Assert.assertEquals;
import isu.data.MaintenanceAutomatedData;
import isu.data.UnderGradAutomatedData;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class MaintenanceUtil {
	
	private WebDriver driver;
	
	WebElement currentDOB;
	WebElement currentEmail;
	String currentDOBValue;
	String currentEmailValue;
	WebElement currentDueDate;
	String currentDudeDateValue;

	PropertiesUtil propUtil = new PropertiesUtil();
	InputStream input= propUtil.getPropertyFile("url.properties");
	Util util = new Util();
		
	public String getURL() throws IOException{
		return propUtil.getPropertyValue("cs.test.url", input);
		
	}
	public void changeDOBandEmail(List<MaintenanceAutomatedData> maintDataList,int j,String whandle,WebDriver driver) throws InterruptedException, IOException{
	
		
		if(null==driver)
		{
			throw new InterruptedException("Driver not found in changeDOBandEmail ");
		}
		else{
		driver.get(getURL());	
		//driver.get("https://csisuvtst.oracleoutsourcing.com/psp/TISUVJ/EMPLOYEE/HRMS/?cmd=logout");
	    assertEquals("Oracle | PeopleSoft Enterprise Sign-in", driver.getTitle());
	    driver.findElement(By.id("userid")).clear();
	    driver.findElement(By.id("userid")).sendKeys(maintDataList.get(j).getmUserId());
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys(maintDataList.get(j).getmPwd());
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
	    driver.findElement(By.id("PEOPLE_SRCH_LAST_NAME_SRCH")).sendKeys(maintDataList.get(j).getmLastName());
	    driver.findElement(By.id("PEOPLE_SRCH_FIRST_NAME_SRCH")).click();
	    driver.findElement(By.id("PEOPLE_SRCH_FIRST_NAME_SRCH")).clear();
	    driver.findElement(By.id("PEOPLE_SRCH_FIRST_NAME_SRCH")).sendKeys(maintDataList.get(j).getmFirstName());
	    driver.findElement(By.id("#ICSearch")).click();
	    Thread.sleep(1000);
	 
	    /*if(driver.findElement(By.id("RESULT2$0")).isDisplayed())
	    {
	    driver.findElement(By.id("RESULT2$0")).click();
	    }*/
	    
	    Thread.sleep(4000);
	    currentDOB = driver.findElement(By.id("DERIVED_PERDATA_BIRTHDATE"));
	    currentDOBValue = currentDOB.getAttribute("value");
	    driver.findElement(By.id("DERIVED_PERDATA_BIRTHDATE")).clear();
	    driver.findElement(By.id("DERIVED_PERDATA_BIRTHDATE")).sendKeys(maintDataList.get(j).getmNewDOB());
	    Thread.sleep(1000);
	  /*  WebElement element = driver.findElement(By.id("SCC_EMAIL_H_EMAIL_ADDR$0"));
	    System.out.println(">>>>>>>>>>>>>>>>>"+element.getAttribute("value"));*/
	    currentEmail =driver.findElement(By.id("SCC_EMAIL_H_EMAIL_ADDR$0"));
	    currentEmailValue = currentEmail.getAttribute("value");
	    driver.findElement(By.id("SCC_EMAIL_H_EMAIL_ADDR$0")).clear();
	    driver.findElement(By.id("SCC_EMAIL_H_EMAIL_ADDR$0")).sendKeys(maintDataList.get(j).getmNewEmail());
	    driver.findElement(By.id("#ICSave")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.id("#ICList")).click();
	    driver.switchTo().window(whandle);
		}
	}
	
	public void changeScore(List<MaintenanceAutomatedData> maintDataList,int j,String whandle,WebDriver driver) throws InterruptedException{
		if(null == driver)
		{
			throw new InterruptedException("Driver not found in changeScore ");
		}
		else{
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
	    driver.findElement(By.id("ADM_MAINT_SCTY_INSTITUTION")).sendKeys(maintDataList.get(j).getmInstitution());
	    driver.findElement(By.id("ADM_MAINT_SCTY_ACAD_CAREER")).clear();
	    driver.findElement(By.id("ADM_MAINT_SCTY_ACAD_CAREER")).sendKeys(maintDataList.get(j).getmAcadCareer());
	    driver.findElement(By.id("ADM_MAINT_SCTY_LAST_NAME_SRCH")).clear();
	    driver.findElement(By.id("ADM_MAINT_SCTY_LAST_NAME_SRCH")).sendKeys(maintDataList.get(j).getmLastName());
	    driver.findElement(By.id("ADM_MAINT_SCTY_FIRST_NAME_SRCH")).clear();
	    driver.findElement(By.id("ADM_MAINT_SCTY_FIRST_NAME_SRCH")).sendKeys(maintDataList.get(j).getmFirstName());
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
	    driver.findElement(By.id("STDNT_TEST_COMP_TEST_COMPONENT$3")).sendKeys(maintDataList.get(j).getmTestComponent());
	    driver.findElement(By.id("STDNT_TEST_COMP_SCORE$3")).click();
	    driver.findElement(By.id("STDNT_TEST_COMP_SCORE$3")).clear();
	    driver.findElement(By.id("STDNT_TEST_COMP_SCORE$3")).sendKeys(maintDataList.get(j).getmScore());
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
	}
	public void changeNamesAndChecklist(List<MaintenanceAutomatedData> maintDataList,int j,String whandle,WebDriver driver) throws InterruptedException, IOException{
		if(null == driver){
			throw new InterruptedException("Driver not found in changeNamesAndChecklist ");
		}
		else{
			
		driver.get(getURL());		
		//driver.get("https://csisuvtst.oracleoutsourcing.com/psp/TISUVJ/EMPLOYEE/HRMS/?cmd=logout");
		Thread.sleep(1000);
		assertEquals("Oracle | PeopleSoft Enterprise Sign-in", driver.getTitle());
		driver.findElement(By.id("userid")).clear();
		driver.findElement(By.id("userid")).sendKeys(maintDataList.get(j).getMtUserId());
		driver.findElement(By.id("pwd")).clear();
		driver.findElement(By.id("pwd")).sendKeys(maintDataList.get(j).getMtPwd());
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
		Thread.sleep(500);
		driver.findElement(By.id("#ICClear")).click();
		Thread.sleep(500);
		driver.findElement(By.id("PEOPLE_SRCH_LAST_NAME_SRCH")).clear();
		driver.findElement(By.id("PEOPLE_SRCH_LAST_NAME_SRCH")).sendKeys(maintDataList.get(j).getMtLastName());
		Thread.sleep(500);
		driver.findElement(By.id("PEOPLE_SRCH_FIRST_NAME_SRCH")).clear();
		driver.findElement(By.id("PEOPLE_SRCH_FIRST_NAME_SRCH")).sendKeys(maintDataList.get(j).getMtFirstName());
		Thread.sleep(500);
		driver.findElement(By.id("#ICSearch")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("DERIVED_PERDATA_NAME_LINK")).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("DERIVED_SCC_NM_NAME_TYPE"))).selectByVisibleText(maintDataList.get(j).getMtNewNameType());
		Thread.sleep(500);
		driver.findElement(By.id("DERIVED_SCC_NM_FIRST_NAME")).clear();
		driver.findElement(By.id("DERIVED_SCC_NM_FIRST_NAME")).sendKeys(maintDataList.get(j).getMtNewFirstName());
		Thread.sleep(500);
		driver.findElement(By.id("DERIVED_SCC_NM_LAST_NAME")).clear();
		driver.findElement(By.id("DERIVED_SCC_NM_LAST_NAME")).sendKeys(maintDataList.get(j).getMtNewLastName());
		Thread.sleep(500);
		driver.findElement(By.id("DERIVED_SCC_NM_SCC_ADDR_SUBMT_BTN")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("#ICSave")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("DERIVED_PERDATA_NAME_LINK")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.id("DERIVED_SCC_NM_NAME_TYPE"))).selectByVisibleText(maintDataList.get(j).getMtAddNameType());
		Thread.sleep(500);
		driver.findElement(By.id("DERIVED_SCC_NM_FIRST_NAME")).clear();
		driver.findElement(By.id("DERIVED_SCC_NM_FIRST_NAME")).sendKeys(maintDataList.get(j).getMtAddFirstName());
		Thread.sleep(500);
		driver.findElement(By.id("DERIVED_SCC_NM_LAST_NAME")).clear();
		driver.findElement(By.id("DERIVED_SCC_NM_LAST_NAME")).sendKeys(maintDataList.get(j).getMtAddLastName());
		Thread.sleep(500);
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
		Thread.sleep(500);
		driver.findElement(By.id("CHKLST_SRCH_LAST_NAME_SRCH")).clear();
		driver.findElement(By.id("CHKLST_SRCH_LAST_NAME_SRCH")).sendKeys(maintDataList.get(j).getMtLastName());
		Thread.sleep(500);
		driver.findElement(By.id("CHKLST_SRCH_FIRST_NAME_SRCH")).clear();
		driver.findElement(By.id("CHKLST_SRCH_FIRST_NAME_SRCH")).sendKeys(maintDataList.get(j).getMtFirstName());
		Thread.sleep(500);
		driver.findElement(By.id("#ICSearch")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("RESULT15$0")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("abbr.PTUNDERLINE")).click();
		Thread.sleep(500);
		driver.findElement(By.name("$ICField$13$$new$3$$img$0")).click();
		Thread.sleep(500);
		driver.findElement(By.id("PERSON_CHK_ITEM_CHKLST_ITEM_CD$4")).clear();
		driver.findElement(By.id("PERSON_CHK_ITEM_CHKLST_ITEM_CD$4")).sendKeys(maintDataList.get(j).getMtCheckListItem());
		Thread.sleep(500);
		driver.findElement(By.id("PERSON_CHK_ITEM_DUE_DT$4")).clear();
		currentDueDate = driver.findElement(By.id("PERSON_CHK_ITEM_DUE_DT$1"));
		currentDudeDateValue = currentDueDate.getAttribute("value");
		driver.findElement(By.id("PERSON_CHK_ITEM_DUE_DT$4")).sendKeys(currentDudeDateValue);
		Thread.sleep(500);
		driver.findElement(By.id("#ICSave")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("#ICList")).click();
		driver.switchTo().window(whandle);
		}
	}
	public void changeAcadPlanAndProcessAdmit(List<MaintenanceAutomatedData> maintDataList,int j,String whandle,WebDriver driver) throws InterruptedException{
		if(null == driver){
			throw new InterruptedException("Driver not found in changeAcadPlanAndProcessAdmit ");
		}
		else{
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
		Thread.sleep(500);
		driver.findElement(By.id("ADM_MAINT_SCTY_INSTITUTION")).clear();
		driver.findElement(By.id("ADM_MAINT_SCTY_INSTITUTION")).sendKeys(maintDataList.get(j).getMtInstitution());
		Thread.sleep(500);
		driver.findElement(By.id("ADM_MAINT_SCTY_ACAD_CAREER")).clear();
		driver.findElement(By.id("ADM_MAINT_SCTY_ACAD_CAREER")).sendKeys(maintDataList.get(j).getMtAcadCareer());
		Thread.sleep(500);
		driver.findElement(By.id("ADM_MAINT_SCTY_LAST_NAME_SRCH")).clear();
		driver.findElement(By.id("ADM_MAINT_SCTY_LAST_NAME_SRCH")).sendKeys(maintDataList.get(j).getMtLastName());
		Thread.sleep(500);
		driver.findElement(By.id("ADM_MAINT_SCTY_FIRST_NAME_SRCH")).clear();
		driver.findElement(By.id("ADM_MAINT_SCTY_FIRST_NAME_SRCH")).sendKeys(maintDataList.get(j).getMtFirstName());
		Thread.sleep(500);
		driver.findElement(By.id("#ICSearch")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("RESULT11$0")).click();
		Thread.sleep(1000);
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame | TargetContent | ]]
		driver.findElement(By.id("#ICCorrection")).click();
		Thread.sleep(1500);
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]
		driver.findElement(By.xpath("//div[@id='PSTAB']/table/tbody/tr/td[4]/a/span")).click();
		Thread.sleep(500);
		driver.findElement(By.id("ADM_APPL_PROG_PROG_ACTION$0")).clear();
		driver.findElement(By.id("ADM_APPL_PROG_PROG_ACTION$0")).sendKeys(maintDataList.get(j).getMtProgAction());
		Thread.sleep(500);
		driver.findElement(By.id("ADM_APPL_PROG_PROG_REASON$0")).clear();
		driver.findElement(By.id("ADM_APPL_PROG_PROG_REASON$0")).sendKeys("REIN");
		Thread.sleep(500);
		driver.findElement(By.id("ADM_APPL_PLAN_ACAD_PLAN$0")).clear();
		driver.findElement(By.id("ADM_APPL_PLAN_ACAD_PLAN$0")).sendKeys(maintDataList.get(j).getMtAcadPlan());
		Thread.sleep(500);
		driver.findElement(By.id("#ICSave")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("#ICList")).click();
		driver.switchTo().window(whandle);
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
		driver.findElement(By.id("pthdr2logout")).click();
		Thread.sleep(1500);
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _parent | 30000]]
		assertEquals("Oracle | PeopleSoft Enterprise Sign-in", driver.getTitle());
		}
	}
	
}
	/*public static void main(String[] args)  {

		int n=100;
		String s=null;

		System.out.println(String.valueOf(n));
		List<UnderGradAutomatedData> dataList1 = new ArrayList<UnderGradAutomatedData>();
		MaintenanceUtil u = new MaintenanceUtil();
		
	}*/

