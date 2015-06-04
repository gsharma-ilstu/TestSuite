package isu.data.impl;

import isu.data.MaintenanceAutomatedData;
import isu.util.Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class MaintenanceAutomatedDataImpl {

	HSSFWorkbook workbook;
	FileInputStream fis;
	FileOutputStream fos;
	HSSFSheet worksheet;

	List<MaintenanceAutomatedData> maintDataList = new ArrayList<MaintenanceAutomatedData>();
	Util util = new Util();

	public List<MaintenanceAutomatedData> setData(HSSFWorkbook workbook, String[] workbookNames) {


		if (null == workbookNames) {
			System.out.println("Workbook name not found for Under Grad Application");
			return null;
		} 	
		else {

			MaintenanceAutomatedData data = new MaintenanceAutomatedData();
			for (int j = 0; j < workbookNames.length; j++) {
			
				//worksheet = workbook.getSheet('"'+workbookNames[j].toString()+'"');
				worksheet = workbook.getSheet(workbookNames[j].toString());
				if (null == worksheet) {
					System.out.println("Worksheet not found for Under Grad Application");
					return null;
				}

				for (int i = 0; i <= worksheet.getLastRowNum(); i++)
				{
					if (i == 0) {
						System.out.println("Headers");
					}
					else	
					{
						HSSFRow row1 = worksheet.getRow(i);

						if ((workbookNames[j].equalsIgnoreCase("maintainApplication"))) {
							data.setmUserId(util.getCellValue(row1.getCell((short) 0)));
							data.setmPwd(util.getCellValue(row1.getCell((short) 1)));
							data.setmLastName(util.getCellValue(row1.getCell((short) 2)));
							data.setmFirstName(util.getCellValue(row1.getCell((short) 3)));
							data.setmNewDOB(util.getCellValue(row1.getCell((short) 4)));
							data.setmNewEmail(util.getCellValue(row1.getCell((short) 5)));
							data.setmInstitution(util.getCellValue(row1.getCell((short) 6)));
							data.setmAcadCareer(util.getCellValue(row1.getCell((short) 7)));
							data.setmTestComponent(util.getCellValue(row1.getCell((short) 8)));
							data.setmScore(util.getCellValue(row1.getCell((short) 9)));

							maintDataList.add(data);
						}

						if ((workbookNames[j].equalsIgnoreCase("maintainTransferUnderGrad"))
								|| (workbookNames[j].equalsIgnoreCase("maintainReadmitUnderGrad")) || (workbookNames[j].equalsIgnoreCase("maintainUnivHS"))) {
							data.setMtUserId(util.getCellValue(row1.getCell((short) 0)));
							data.setMtPwd(util.getCellValue(row1.getCell((short) 1)));
							data.setMtLastName(util.getCellValue(row1.getCell((short) 2)));
							data.setMtFirstName(util.getCellValue(row1.getCell((short) 3)));
							data.setMtNewNameType(util.getCellValue(row1.getCell((short) 4)));
							data.setMtNewLastName(util.getCellValue(row1.getCell((short) 5)));
							data.setMtNewFirstName(util.getCellValue(row1.getCell((short) 6)));
							data.setMtAddNameType(util.getCellValue(row1.getCell((short) 7)));
							data.setMtAddLastName(util.getCellValue(row1.getCell((short) 8)));
							data.setMtAddFirstName(util.getCellValue(row1.getCell((short) 9)));
							data.setMtCheckListItem(util.getCellValue(row1.getCell((short) 10)));
							data.setMtInstitution(util.getCellValue(row1.getCell((short) 10)));
							data.setMtAcadCareer(util.getCellValue(row1.getCell((short) 10)));
							data.setMtProgAction(util.getCellValue(row1.getCell((short) 10)));
							data.setMtAcadPlan(util.getCellValue(row1.getCell((short) 10)));
							maintDataList.add(data);
						}
					}
				}

			}
			return maintDataList;
		}
	}

	public static void main(String[] args) {/*
	 * 
	 * int n=100; String s=null;
	 * 
	 * System.out.println(String.valueOf(
	 * n)); List<UnderGradAutomatedData>
	 * dataList1 = new
	 * ArrayList<UnderGradAutomatedData
	 * >(); UnderGradAutomatedDataImpl u
	 * = new
	 * UnderGradAutomatedDataImpl(); try
	 * { //dataList1=u.readFile(
	 * "Q://LEAP_Test_Cases//Automation//Data"
	 * , "AutomationData.xls",
	 * "accCreationVerification");
	 * dataList1=u.readFile(
	 * "Q://LEAP_Test_Cases//Automation//Data"
	 * , "AutomationData.xls",
	 * "transferUnderGrad"); for(int
	 * i=0;i<1;i++) {
	 * //System.out.println
	 * (dataList1.get(i).getUserid());
	 * //
	 * System.out.println(dataList1.get
	 * (i).getPwd());
	 * //System.out.println
	 * (dataList1.get(i).getmNewDOB());
	 * /
	 * /System.out.println(dataList1.get
	 * (i).getmNewEmail());
	 * //System.out.
	 * println(dataList1.get
	 * (i).getmLastName());
	 * 
	 * System.out.println(dataList1.get(i
	 * ).gettSSN());
	 * System.out.println(dataList1
	 * .get(i).gettFamilyPhone());
	 * System
	 * .out.println(dataList1.get(i
	 * ).gettAppAnswer4());
	 * System.out.println
	 * (dataList1.get(i
	 * ).gettCardNumber());
	 * 
	 * }
	 * 
	 * 
	 * 
	 * //u.writeCell(
	 * "Q://LEAP_Test_Cases//Automation//Data"
	 * , "AutomationData1.xls",
	 * "login","fchaes"); } catch
	 * (IOException e) { // TODO
	 * Auto-generated catch block
	 * e.printStackTrace(); }
	 */
	}
}
