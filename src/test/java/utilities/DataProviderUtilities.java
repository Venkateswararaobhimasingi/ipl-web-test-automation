package utilities;

import org.testng.annotations.DataProvider;

public class DataProviderUtilities {
	ExcelUtility excelUtility;
	
	@DataProvider(name="iplTeamData")
	public Object[][] getTeamData(){
		
		String excelPath=".\\testData\\TeamData_IPL_TestCase02.xlsx";
		String sheetName="Sheet1";
		
		excelUtility=new ExcelUtility();
		Object[][] data=excelUtility.getTeamData(excelPath, sheetName);
		
		return data;
		
	}

}
