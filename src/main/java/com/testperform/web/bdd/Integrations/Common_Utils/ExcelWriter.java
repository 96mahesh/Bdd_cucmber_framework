//package com.mobiotics.web.bdd.Integrations.Common_Utils;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelWriter {
//
//	FileOutputStream fileoutputstream1;
//	FileInputStream fileinputstream;
//	XSSFWorkbook workbook;
//	XSSFSheet sheet;//,sheet1;
//	XSSFRow row;
//	XSSFCell cell;
//
//	public void writeToExcel(String sheet1, int rownumber, int columnnumber, String value) throws IOException {
//
//		fileinputstream = new FileInputStream("C:\\Users\\User\\Desktop\\VAuth_Logins.xlsx");
//		workbook = new XSSFWorkbook(fileinputstream);
//		//sheet.createRow(rownumber);
//		sheet = workbook.getSheet(sheet1);
//		sheet.getRow(rownumber).createCell(columnnumber).setCellValue(value);
//		System.out.println(value);
//		fileoutputstream1 = new FileOutputStream("C:\\Users\\User\\Desktop\\VAuth_Logins.xlsx");
//		workbook.write(fileoutputstream1);
//		fileoutputstream1.close();
//		workbook.close();
//
//	}
//}

