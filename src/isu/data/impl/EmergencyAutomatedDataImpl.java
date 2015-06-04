package isu.data.impl;

import isu.data.CommonAutomatedData;
import isu.data.EmergencyAutomatedData;
import isu.util.Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class EmergencyAutomatedDataImpl {

	HSSFWorkbook workbook;
	FileInputStream fis;
	FileOutputStream fos;
	HSSFSheet worksheet;

	List<EmergencyAutomatedData> dataList = new ArrayList<EmergencyAutomatedData>();
	Util util = new Util();

	public List<EmergencyAutomatedData> setData(HSSFWorkbook workbook, String[] workbookNames) {


		if (null == workbookNames) {
			System.out.println("Workbook name not found for Under Grad Application");
			return null;
		} 	
		else {

			
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
						EmergencyAutomatedData data = new EmergencyAutomatedData();
						if (workbookNames[j].equalsIgnoreCase("Sheet1")) {
						
							data.setEMPLID(util.getCellValue(row1.getCell((short)0)));
							data.setAcadCareer(util.getCellValue(row1.getCell((short)1)));
							data.setStdntCrNbr(util.getCellValue(row1.getCell((short)2)));
							data.setAcadProg(util.getCellValue(row1.getCell((short)3)));
							data.setEffdt(util.getCellValue(row1.getCell((short)4)));
							data.setActionReason(util.getCellValue(row1.getCell((short)5)));
							data.setAdmitTerm(util.getCellValue(row1.getCell((short)6)));
							
						}
						dataList.add(data);
					}
					
				}

			}
			return dataList;
		}
	}

	
}
