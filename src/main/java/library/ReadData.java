package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {
	XSSFWorkbook workbook;
	XSSFSheet sheet1;
	//FileOutputStream fout;
	public ReadData (String xlpath) {
		
		try {
			// Read the file by giving the path
			File src = new File(xlpath);
			//all input output related operation is in java.io
			FileInputStream fis = new FileInputStream(src);
					
			//this will load the workbook i.e. full excel sheet 
			workbook = new XSSFWorkbook(fis);
			
			//read first sheet i.e. Sheet index 0 
			//sheet1 = workbook.getSheetAt(0);
		//	fout = new FileOutputStream(src);
			
		}  catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}					
	}
	
	/*public void writeData(int sheetNumber, int row, int col, String valueStr) throws Exception {
		sheet1=workbook.getSheetAt(sheetNumber);
		sheet1.getRow(row).createCell(col).setCellValue(valueStr);
		//fout = new FileOutputStream(src);
		workbook.write(fout);
		workbook.close();
	}*/
	
	public String getData(int sheetNumber, int row, int col) {
		sheet1=workbook.getSheetAt(sheetNumber);
		System.out.println("Sheet ="+sheetNumber+" row ="+row+" Col ="+col);
		String data=sheet1.getRow(row).getCell(col).getStringCellValue();
		return data;
	}
	
	public Date getDate(int sheetNumber, int row, int col) {
		sheet1=workbook.getSheetAt(sheetNumber);
		System.out.println("Sheet ="+sheetNumber+" row ="+row+" Col ="+col);
		Date dateValue=sheet1.getRow(row).getCell(col).getDateCellValue();
		//String dateValue2=sheet1.getRow(row).getCell(col).getRawValue();
		//System.out.println("")
		//String data=dateValue.toString();
		return dateValue;
	}
	public int getRowCount(int sheetIndex) {
		
		//int rowCount=workbook.getSheetAt(sheetIndex)
			//	(sheetIn).getLastRowNum()+1;
		int rowCount=workbook.getSheetAt(sheetIndex).getLastRowNum()+1;
		return rowCount;
	}
	
    public int getSheetCount() {
			
		int getSheetCount=workbook.getNumberOfSheets();
		return getSheetCount;
	}
    
    public int getActiveSheet() {
    	return workbook.getActiveSheetIndex();
    	
    }

}
