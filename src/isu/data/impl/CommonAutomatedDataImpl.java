package isu.data.impl;

import isu.data.CommonAutomatedData;
import isu.util.Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class CommonAutomatedDataImpl {

	HSSFWorkbook workbook;
	FileInputStream fis;
	FileOutputStream fos;
	HSSFSheet worksheet;

	List<CommonAutomatedData> commonDataList = new ArrayList<CommonAutomatedData>();
	Util util = new Util();

	public List<CommonAutomatedData> setData(HSSFWorkbook workbook, String[] workbookNames) {


		if (null == workbookNames) {
			System.out.println("Workbook name not found for Under Grad Application");
			return null;
		} 	
		else {

			CommonAutomatedData data = new CommonAutomatedData();
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

						if (workbookNames[j].equalsIgnoreCase("accCreationVerification")) {
							data.setEmailForCreation(util.getCellValue(row1.getCell((short)0)));
							data.setEmailConfirmation(util.getCellValue(row1.getCell((short)1)));
							data.setFirstName(util.getCellValue(row1.getCell((short)2)));
							data.setMiddleName(util.getCellValue(row1.getCell((short)3)));
							data.setLastName(util.getCellValue(row1.getCell((short)4)));
							data.setGender(util.getCellValue(row1.getCell((short)5)));
							data.setDobMonth(util.getCellValue(row1.getCell((short)6)));
							data.setDobDay(util.getCellValue(row1.getCell((short)7)));
							data.setDobYear(util.getCellValue(row1.getCell((short)8)));
							data.setBirthCity(util.getCellValue(row1.getCell((short)9)));
							data.setPostalCode(util.getCellValue(row1.getCell((short)10)));
							data.setPersonalEmail(util.getCellValue(row1.getCell((short)11)));
							data.setPersonalPasswd(util.getCellValue(row1.getCell((short)12)));
							data.setInitials(util.getCellValue(row1.getCell((short)13)));
							data.setPassword(util.getCellValue(row1.getCell((short)14)));
							data.setAnswer1(util.getCellValue(row1.getCell((short)15)));
							data.setAnswer2(util.getCellValue(row1.getCell((short)16)));
							data.setAnswer3(util.getCellValue(row1.getCell((short)17)));
							data.setAnswer3(util.getCellValue(row1.getCell((short)18)));

							commonDataList.add(data);
						}
						
						if (workbookNames[j].equalsIgnoreCase("passwordChange")) {
							data.setUlid(util.getCellValue(row1.getCell((short)0)));
							data.setPass(util.getCellValue(row1.getCell((short)1)));
							data.setNewPassword(util.getCellValue(row1.getCell((short)2)));

							commonDataList.add(data);
						}

					}
				}

			}
			return commonDataList;
		}
	}

	
}
