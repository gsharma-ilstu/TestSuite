package isu.data.impl;

import isu.data.GradAutomatedData;
import isu.data.MaintenanceAutomatedData;
import isu.data.UnderGradAutomatedData;
import isu.data.VisitorAutomatedData;
import isu.util.Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class VisitorAutomatedDataImpl {

	HSSFWorkbook workbook;
	FileInputStream fis;
	FileOutputStream fos;
	HSSFSheet worksheet;


	List<VisitorAutomatedData> dataList = new ArrayList<VisitorAutomatedData>()  ;
	Util util = new Util();

	public List<VisitorAutomatedData> setData(HSSFWorkbook workbook,String workbookName){

		worksheet = workbook.getSheet(workbookName.toString());
		if(null==worksheet){
			System.out.println("Worksheet not found for Under Grad Application");
			return null;
		}
		for (int i=0;i<=worksheet.getLastRowNum();i++)
		{
			if(i==0){
				System.out.println("Headers");
			}

			else{
				VisitorAutomatedData data = new VisitorAutomatedData();
				HSSFRow row1 = worksheet.getRow(i);

				/*HSSFCell cellA1 = row1.getCell((short) 0);
				String a1Val = cellA1.getStringCellValue();*/

				if((workbookName.equalsIgnoreCase("DualUnivHS")) || (workbookName.equalsIgnoreCase("DualOther")) )
				{
					data.setUserid(util.getCellValue(row1.getCell((short)0)));
					data.setPwd(util.getCellValue(row1.getCell((short)1)));
					data.setTerm(util.getCellValue(row1.getCell((short)2)));

					dataList.add(data);
				}



			}

		}
		return dataList;
	}


	public static void main(String[] args)  {/*

		int n=100;
		String s=null;

		System.out.println(String.valueOf(n));
		List<UnderGradAutomatedData> dataList1 = new ArrayList<UnderGradAutomatedData>();
		UnderGradAutomatedDataImpl u = new UnderGradAutomatedDataImpl();
		try {
			//dataList1=u.readFile("Q://LEAP_Test_Cases//Automation//Data", "AutomationData.xls", "accCreationVerification");
			dataList1=u.readFile("Q://LEAP_Test_Cases//Automation//Data", "AutomationData.xls", "transferUnderGrad");
			for(int i=0;i<1;i++)
			{
				//System.out.println(dataList1.get(i).getUserid());
				//System.out.println(dataList1.get(i).getPwd());
				//System.out.println(dataList1.get(i).getmNewDOB());
				//System.out.println(dataList1.get(i).getmNewEmail());
				//System.out.println(dataList1.get(i).getmLastName());

					System.out.println(dataList1.get(i).gettSSN());
					System.out.println(dataList1.get(i).gettFamilyPhone());
					System.out.println(dataList1.get(i).gettAppAnswer4());
					System.out.println(dataList1.get(i).gettCardNumber());

			}



			//u.writeCell("Q://LEAP_Test_Cases//Automation//Data", "AutomationData1.xls", "login","fchaes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 */}
}
