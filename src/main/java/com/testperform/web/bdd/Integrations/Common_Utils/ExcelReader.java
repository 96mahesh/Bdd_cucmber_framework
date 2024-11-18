package com.testperform.web.bdd.Integrations.Common_Utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	FileInputStream fileinputstream;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	public String getCellData(int rownumber, int columnnumber) throws IOException {
		fileinputstream = new FileInputStream(
				(System.getProperty("user.dir") + "\\src\\test\\resources\\files\\Book.xlsx"));
		workbook = new XSSFWorkbook(fileinputstream);
		sheet = workbook.getSheet("Excel_sheetname");
		row = sheet.getRow(rownumber);
		cell = row.getCell(columnnumber);

		DataFormatter formatter = new DataFormatter();
		String data = "";
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("The cell is empty/ some issue with excel");
		}
		workbook.close();
		fileinputstream.close();
		return data;
	}

}
