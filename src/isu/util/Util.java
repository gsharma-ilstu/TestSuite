package isu.util;

import isu.data.CommonAutomatedData;
import isu.data.EmergencyAutomatedData;
import isu.data.GradAutomatedData;
import isu.data.MaintenanceAutomatedData;
import isu.data.UnderGradAutomatedData;
import isu.data.VisitorAutomatedData;
import isu.data.impl.CommonAutomatedDataImpl;
import isu.data.impl.EmergencyAutomatedDataImpl;
import isu.data.impl.GradAutomatedDataImpl;
import isu.data.impl.MaintenanceAutomatedDataImpl;
import isu.data.impl.UnderGradAutomatedDataImpl;
import isu.data.impl.VisitorAutomatedDataImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class Util {

	private WebDriver driver;
	private String baseUrl;

	HSSFWorkbook workbook;
	FileInputStream fis;
	FileOutputStream fos;
	HSSFSheet worksheet;

	List<UnderGradAutomatedData> dataList = new ArrayList<UnderGradAutomatedData>();
	List<GradAutomatedData> gradDataList = new ArrayList<GradAutomatedData>();
	List<MaintenanceAutomatedData> maintDataList = new ArrayList<MaintenanceAutomatedData>();
	List<CommonAutomatedData> commonDataList = new ArrayList<CommonAutomatedData>();
	List<VisitorAutomatedData> visitorDataList = new ArrayList<VisitorAutomatedData>();
	List<EmergencyAutomatedData> emergencyDataList = new ArrayList<EmergencyAutomatedData>();

	public List readFile(String fileLocation, String fileName, String[] workbookNames, boolean isMaintenance,
			boolean isAccCreation) throws IOException {

		// fis = new FileInputStream("Q://LEAP_Test_Cases//AutomationData.xls");
		fis = new FileInputStream(fileLocation + "//" + fileName);

		workbook = new HSSFWorkbook(fis);
		// HSSFSheet worksheet = workbook.getSheet("accCreationVerification");

		// worksheet = workbook.getSheet(workbookNames);

		 
		if(fileName.equalsIgnoreCase("CommonAutomationData.xls")){
			if (isAccCreation) {
				CommonAutomatedDataImpl accDataImpl = new CommonAutomatedDataImpl();
				commonDataList = accDataImpl.setData(workbook, workbookNames);
				return commonDataList;
			}else{
				CommonAutomatedDataImpl accDataImpl = new CommonAutomatedDataImpl();
				commonDataList = accDataImpl.setData(workbook, workbookNames);
				return commonDataList;
			}
		}
		else if (fileName.equalsIgnoreCase("UnderGradAutomationData.xls")) {
			if (isMaintenance) {
				MaintenanceAutomatedDataImpl maintDataImpl = new MaintenanceAutomatedDataImpl();
				maintDataList = maintDataImpl.setData(workbook, workbookNames);
				return maintDataList;
			} else {
				UnderGradAutomatedDataImpl underGradImpl = new UnderGradAutomatedDataImpl();
				dataList = underGradImpl.setData(workbook, workbookNames[0]);
				return dataList;
			}
		}
		else if (fileName.equalsIgnoreCase("VisitorAutomationData.xls")) {
			if (isMaintenance) {
				MaintenanceAutomatedDataImpl maintDataImpl = new MaintenanceAutomatedDataImpl();
				maintDataList = maintDataImpl.setData(workbook, workbookNames);
				return maintDataList;
			} else {
				VisitorAutomatedDataImpl visitorDataImpl = new VisitorAutomatedDataImpl();
				visitorDataList = visitorDataImpl.setData(workbook, workbookNames[0]);
				return visitorDataList;
			}
		}else if (fileName.equalsIgnoreCase("GraduateAutomationData.xls")) {
			if (isMaintenance) {
				MaintenanceAutomatedDataImpl maintDataImpl = new MaintenanceAutomatedDataImpl();
				maintDataList = maintDataImpl.setData(workbook, workbookNames);
				return maintDataList;
			} else {
				GradAutomatedDataImpl gradImpl = new GradAutomatedDataImpl();
				gradDataList = gradImpl.setData(workbook, workbookNames[0]);
				return gradDataList;
			}
		} else if (fileName.equalsIgnoreCase("EmergencyAutomationData.xls") || fileName.equalsIgnoreCase("EmergencyAutomationData1.xls") || fileName.equalsIgnoreCase("EmergencyAutomationData2.xls")) {
			if (isMaintenance) {
				EmergencyAutomatedDataImpl emergencyDataImpl = new EmergencyAutomatedDataImpl();
				emergencyDataList = emergencyDataImpl.setData(workbook, workbookNames);
				return emergencyDataList;
			} else {
				
				return null;
			}
		}
		else {
			return null;
		}
		

	}

	public void writeCell(String fileLocation, String fileName, String workbookName, String userId) throws IOException {

		fis = new FileInputStream(new File(fileLocation + "//" + fileName));

		workbook = new HSSFWorkbook(fis);
		worksheet = workbook.getSheet(workbookName);
		HSSFRow row = worksheet.getRow((short) 1);
		HSSFCell cell = row.getCell((short) 0);

		String oldValue = cell.getStringCellValue();
		cell.setCellValue(userId.toUpperCase());
		String newValue = cell.getStringCellValue();
		System.out.println(workbookName + " workbook of" + fileName + " Updated from Old Value :" + oldValue
				+ " to New Value:" + newValue);

		fis.close(); // Close the InputStream
		fos = new FileOutputStream(new File(fileLocation + "//" + fileName)); // Open
																				// FileOutputStream
																				// to
																				// write
																				// updates
		workbook.write(fos); // write changes
		fos.close(); // close the stream
		System.out.println("writing User id completed");
	}

	public String getCellValue(HSSFCell cell) {
		int type;
		Object result;
		type = cell.getCellType();

		switch (type) {

		case Cell.CELL_TYPE_NUMERIC: // numeric value in Excel
		case Cell.CELL_TYPE_FORMULA: // precomputed value based on formula
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());

				String dateFmt = cell.getCellStyle().getDataFormatString();
				result = new CellDateFormatter(dateFmt).format(date);
			} else {
				result = (long) cell.getNumericCellValue();
			}
			break;
		case Cell.CELL_TYPE_STRING: // String Value in Excel
			result = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			result = "";
		case Cell.CELL_TYPE_BOOLEAN: // boolean value
			result = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_ERROR:
		default:
			throw new RuntimeException("There is no support for this type of cell");
		}

		return result.toString();
	}

	public void checkElement(WebDriver driver, final String element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				while (true) {
					try {
						System.out.println("Executed");
						return (driver.findElement(By.id("'" + element + "'")).isDisplayed())
								|| (driver.findElement(By.id("'" + element + "'")).isEnabled());

					}

					catch (StaleElementReferenceException ex) {
						System.out.println(element + " not found");
						return false;
					} catch (ElementNotFoundException ex) {
						System.out.println(element + " not found");
						return false;
					}
				}
			}
		});

	}

	public boolean testResultSet(WebDriver driver, final String element) {
		WebElement currentResultSet;
		String currentResultSetValue;
		currentResultSet = driver.findElement(By
				.xpath("/html/body/form/div[4]/div[4]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/span[2]"));
		currentResultSetValue = currentResultSet.getAttribute("value");
		System.out.println(currentResultSetValue);
		return (driver.findElement(By.id("'" + element + "'")).isDisplayed())
				|| (driver.findElement(By.id("'" + element + "'")).isEnabled());
	}

	public WebDriver createProfileBeforeTest() {
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		System.setProperty("webdriver.firefox.bin",
				"Q://LEAP_Test_Cases//Automation//Tools//Mozilla Firefox//firefox.exe");
		profile.setPreference("capability.policy.default.Window.QueryInterface", "allAccess");
		profile.setPreference("capability.policy.default.Window.frameElement.get", "allAccess");
		driver = new FirefoxDriver(profile);
		baseUrl = "https://account-test.illinoisstate.edu/selfservice/registration/begin";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;
	}

	public static void main(String[] args) {

		int n = 100;
		String s = null;

		String[] workBookNames = { "maintainApplication", "maintainTransferUnderGrad", "maintainReadmitUnderGrad" };
		System.out.println(String.valueOf(n));
		List<UnderGradAutomatedData> dataList1 = new ArrayList<UnderGradAutomatedData>();
		Util u = new Util();
		try {
			// dataList1=u.readFile("Q://LEAP_Test_Cases//Automation//Data",
			// "AutomationData.xls", "accCreationVerification");
			dataList1 = u.readFile("Q://LEAP_Test_Cases//Automation//Data", "AutomationData.xls", workBookNames, true,
					false);
			for (int i = 0; i < 1; i++) {
				// System.out.println(dataList1.get(i).getUserid());
				// System.out.println(dataList1.get(i).getPwd());
				// System.out.println(dataList1.get(i).getmNewDOB());
				// System.out.println(dataList1.get(i).getmNewEmail());
				// System.out.println(dataList1.get(i).getmLastName());

				System.out.println(dataList1.get(i).gettSSN());
				System.out.println(dataList1.get(i).gettFamilyPhone());
				System.out.println(dataList1.get(i).gettAppAnswer4());
				System.out.println(dataList1.get(i).gettCardNumber());

			}

			// u.writeCell("Q://LEAP_Test_Cases//Automation//Data",
			// "AutomationData1.xls", "login","fchaes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
