package com.GBHSR.utilites;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtility implements constants{
    
    String path;
    FileInputStream fis;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFRow row;
    XSSFCell cell;
    Properties prop;

    
    public  String getDataFromExcel(String path, String sheetName, int rowNum, int cellNum) throws Throwable
 	{
 		fis =new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(cellNum);
        String value = cell.getStringCellValue().toString();
		return value;
 	}
    
    public  int getrowcount(String excelPath, String sheet) throws Throwable
	{
		fis = new FileInputStream(excelPath);
		workbook = new XSSFWorkbook(fis);
		int rCount = workbook.getSheet(sheet).getLastRowNum();
		return rCount;
	}

    public  int getcellcount(String excelPath, String sheet, int row) throws Throwable
	{
    	fis = new FileInputStream(excelPath);
		workbook = new XSSFWorkbook(fis);
		int cellCount = workbook.getSheet(sheet).getRow(row).getLastCellNum();
		return cellCount;
	}	
	
	public String getproperty(String configpath, String key) throws Throwable
	{
		FileInputStream fis  = new FileInputStream(configpath);
		prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(configpath, key);
		return value;
		
	}

}