package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	FileInputStream fi;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	
	public Object[][] getTeamData(String excelPath,String sheetName) {	
		try {
		
		fi= new FileInputStream(excelPath);
		workbook= new XSSFWorkbook(fi);
		sheet= workbook.getSheet(sheetName);
		
		int rowCount= sheet.getLastRowNum();
		int colCount= sheet.getRow(0).getLastCellNum();
		
		Object[][] data= new Object[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) {
			row=sheet.getRow(i);
			for(int j=0;j<colCount;j++) {
				if(row.getCell(j)!= null) {
				    data[i-1][j]= row.getCell(j).toString();
				} 
				else {
				    data[i-1][j] = "";
				}
			}
		}

		workbook.close();
		fi.close();
		
		return data;	
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;	
	}

}
