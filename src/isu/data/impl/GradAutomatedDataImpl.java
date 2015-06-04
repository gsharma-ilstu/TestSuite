package isu.data.impl;

import isu.data.GradAutomatedData;
import isu.util.Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class GradAutomatedDataImpl {

	HSSFWorkbook workbook;
	FileInputStream fis;
	FileOutputStream fos;
	HSSFSheet worksheet;


	List<GradAutomatedData> dataList = new ArrayList<GradAutomatedData>()  ;
	Util util = new Util();

	public List<GradAutomatedData> setData(HSSFWorkbook workbook,String workbookName){
		
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
				GradAutomatedData data = new GradAutomatedData();
				HSSFRow row1 = worksheet.getRow(i);

				/*HSSFCell cellA1 = row1.getCell((short) 0);
				String a1Val = cellA1.getStringCellValue();*/

				if((workbookName.equalsIgnoreCase("createApplication")) || (workbookName.equalsIgnoreCase("createMusicApp")))
				{
					data.setUserid(util.getCellValue(row1.getCell((short)0)));
					data.setPwd(util.getCellValue(row1.getCell((short)1)));
					data.setPrefFirstName(util.getCellValue(row1.getCell((short)2)));
					data.setBirthCountry(util.getCellValue(row1.getCell((short)3)));
					data.setBirthPlace(util.getCellValue(row1.getCell((short)4)));
					data.setHomeAddress1(util.getCellValue(row1.getCell((short)5)));
					data.setHomeAddressCity(util.getCellValue(row1.getCell((short)6)));
					data.setHomeAddressState(util.getCellValue(row1.getCell((short)7)));
					data.setHomeAddressPostal(util.getCellValue(row1.getCell((short)8)));
					data.setPhoneType(util.getCellValue(row1.getCell((short)9)));
					data.setPhone(util.getCellValue(row1.getCell((short)10)));
					data.setEmail(util.getCellValue(row1.getCell((short)11)));
					data.setISURelative(util.getCellValue(row1.getCell((short)12)));
					data.setAdmitTerm(util.getCellValue(row1.getCell((short)13)));
					data.setSearchOne(util.getCellValue(row1.getCell((short)14)));
					data.setAppAnswer0(util.getCellValue(row1.getCell((short)15)));
					data.setRecomFirstName(util.getCellValue(row1.getCell((short)16)));
					data.setRecomLastName(util.getCellValue(row1.getCell((short)17)));
					data.setRecomWorkEmail(util.getCellValue(row1.getCell((short)18)));
					data.setRecomFirstName1(util.getCellValue(row1.getCell((short)19)));
					data.setRecomLastName1(util.getCellValue(row1.getCell((short)20)));
					data.setRecomWorkEmail1(util.getCellValue(row1.getCell((short)21)));
					data.setRecomFirstName2(util.getCellValue(row1.getCell((short)22)));
					data.setRecomLastName2(util.getCellValue(row1.getCell((short)23)));
					data.setRecomWorkEmail2(util.getCellValue(row1.getCell((short)24)));
					data.setSchoolState(util.getCellValue(row1.getCell((short)25)));
					data.setDegree(util.getCellValue(row1.getCell((short)26)));
					data.setSchoolState2(util.getCellValue(row1.getCell((short)27)));
					data.setDegree2(util.getCellValue(row1.getCell((short)28)));
					dataList.add(data);
				}

		
				if(workbookName.equalsIgnoreCase("maintainApplication"))
				{
					data.setmUserId(util.getCellValue(row1.getCell((short)0)));
					data.setmPwd(util.getCellValue(row1.getCell((short)1)));
					data.setmLastName(util.getCellValue(row1.getCell((short)2)));
					data.setmFirstName(util.getCellValue(row1.getCell((short)3)));
					data.setmNewDOB(util.getCellValue(row1.getCell((short)4)));
					data.setmNewEmail(util.getCellValue(row1.getCell((short)5)));
					data.setmInstitution(util.getCellValue(row1.getCell((short)6)));
					data.setmAcadCareer(util.getCellValue(row1.getCell((short)7)));
					data.setmTestComponent(util.getCellValue(row1.getCell((short)8)));
					data.setmScore(util.getCellValue(row1.getCell((short)9)));

					dataList.add(data);
				}
					if(workbookName.equalsIgnoreCase("transferUnderGrad") || workbookName.equalsIgnoreCase("readmitGrad") )
				{
					data.settUserId(util.getCellValue(row1.getCell((short)0)));
					data.settPwd(util.getCellValue(row1.getCell((short)1)));
					data.settPrefFirstName(util.getCellValue(row1.getCell((short)2)));
					data.settPrefLastName(util.getCellValue(row1.getCell((short)3)));
					data.settBirthCountry(util.getCellValue(row1.getCell((short)4)));
					data.settBirthPlace(util.getCellValue(row1.getCell((short)5)));
					data.settSSN(util.getCellValue(row1.getCell((short)6)));
					data.settHomeAddress1(util.getCellValue(row1.getCell((short)7)));
					data.settHomeAddressCity(util.getCellValue(row1.getCell((short)8)));
					data.settHomeAddressState(util.getCellValue(row1.getCell((short)9)));
					data.settHomeAddressPostal(util.getCellValue(row1.getCell((short)10)));
					data.settPhone(util.getCellValue(row1.getCell((short)11)));
					data.settEmail(util.getCellValue(row1.getCell((short)12)));
					data.settFamilyFirstName(util.getCellValue(row1.getCell((short)13)));
					data.settFamilyLastName(util.getCellValue(row1.getCell((short)14)));
					data.settFamilyRelation(util.getCellValue(row1.getCell((short)15)));
					data.settFamilAddress1(util.getCellValue(row1.getCell((short)16)));
					data.settFamilyAddressCity(util.getCellValue(row1.getCell((short)17)));
					data.settFamilyAddressState(util.getCellValue(row1.getCell((short)18)));
					data.settFamilyAddressPostal(util.getCellValue(row1.getCell((short)19)));
					data.settFamilyEmail(util.getCellValue(row1.getCell((short)20)));
					data.settFamilyPhone(util.getCellValue(row1.getCell((short)21)));
					data.settResidencyState(util.getCellValue(row1.getCell((short)22)));
					data.settAdmitTerm(util.getCellValue(row1.getCell((short)23)));
					data.settAcadLevel(util.getCellValue(row1.getCell((short)24)));
					data.settAppAnswer0(util.getCellValue(row1.getCell((short)25)));
					data.settAppAnswer1(util.getCellValue(row1.getCell((short)26)));
					data.settAppAnswer2(util.getCellValue(row1.getCell((short)27)));
					data.settAppAnswer3(util.getCellValue(row1.getCell((short)28)));
					data.settAppAnswer4(util.getCellValue(row1.getCell((short)29)));
					data.settAppAnswer5(util.getCellValue(row1.getCell((short)30)));
					data.settAppAnswer6(util.getCellValue(row1.getCell((short)31)));
					data.settAppAnswer7(util.getCellValue(row1.getCell((short)32)));
					data.settAppAnswer8(util.getCellValue(row1.getCell((short)33)));
					data.settAppAnswer9(util.getCellValue(row1.getCell((short)34)));
					data.settTest(util.getCellValue(row1.getCell((short)35)));
					data.settScore0(util.getCellValue(row1.getCell((short)36)));
					data.settScore1(util.getCellValue(row1.getCell((short)37)));
					data.settScore2(util.getCellValue(row1.getCell((short)38)));
					data.settScore3(util.getCellValue(row1.getCell((short)39)));
					data.settScore4(util.getCellValue(row1.getCell((short)40)));
					data.settPayMethod(util.getCellValue(row1.getCell((short)41)));
					data.settCardNumber(util.getCellValue(row1.getCell((short)42)));
					data.settNameOnCard(util.getCellValue(row1.getCell((short)43)));
					data.settCVV(util.getCellValue(row1.getCell((short)44)));
					
					dataList.add(data);
				}
				
				if((workbookName.equalsIgnoreCase("maintainTransferUnderGrad")) ||(workbookName.equalsIgnoreCase("maintainReadmitUnderGrad")))
				{
					data.setMtUserId(util.getCellValue(row1.getCell((short)0)));
					data.setMtPwd(util.getCellValue(row1.getCell((short)1)));
					data.setMtLastName(util.getCellValue(row1.getCell((short)2)));
					data.setMtFirstName(util.getCellValue(row1.getCell((short)3)));
					data.setMtNewNameType(util.getCellValue(row1.getCell((short)4)));
					data.setMtNewLastName(util.getCellValue(row1.getCell((short)5)));
					data.setMtNewFirstName(util.getCellValue(row1.getCell((short)6)));
					data.setMtAddNameType(util.getCellValue(row1.getCell((short)7)));
					data.setMtAddLastName(util.getCellValue(row1.getCell((short)8)));
					data.setMtAddFirstName(util.getCellValue(row1.getCell((short)9)));
					data.setMtCheckListItem(util.getCellValue(row1.getCell((short)10)));
					data.setMtInstitution(util.getCellValue(row1.getCell((short)10)));
					data.setMtAcadCareer(util.getCellValue(row1.getCell((short)10)));
					data.setMtProgAction(util.getCellValue(row1.getCell((short)10)));
					data.setMtAcadPlan(util.getCellValue(row1.getCell((short)10)));
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
