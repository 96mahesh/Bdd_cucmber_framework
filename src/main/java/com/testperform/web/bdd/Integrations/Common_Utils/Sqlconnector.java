package com.testperform.web.bdd.Integrations.Common_Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

//import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Sqlconnector {

	public Statement statement;
	public ResultSet resultSet;
	public Connection connection;

//	String Driver = ConfigReader.getValue("driver");
//	String url = ConfigReader.getValue("DBurl");
//	String user = ConfigReader.getValue("DBusername");
//	String pwd = ConfigReader.getValue("DBpassword");

	public Connection getConnection(String Driver, String DBurl, String DBusername, String DBpassword, String DBname)

	{

		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(DBurl + DBname, DBusername, DBpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;

	}

	// This method is specifically implemented to monster website. not generic
	// method
	@Test
	public void connect_and_insert(String companyname, String jobtitle, String salary) {

		String query = "INSERT INTO company values(?,?,?);";

		Connection con = getConnection(query, jobtitle, salary, query, companyname);

		try {
			PreparedStatement prepareStatement = con.prepareStatement(query);

			prepareStatement.setString(1, companyname);
			prepareStatement.setString(2, jobtitle);
			prepareStatement.setString(3, salary);

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		System.out.println("Insert successs ");

	}

	/// Read data from mysql database

	public String connectDBgetData(String Driver, String DBurl, String DBusername, String DBpassword, String DBname,
			String query, String tablecolumname) throws SQLException {

		connection = getConnection(Driver, DBurl, DBusername, DBpassword, DBname);
		statement = connection.createStatement();
		String SQLResult = "";
		resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			SQLResult = resultSet.getString(tablecolumname);
			System.out.println(SQLResult);

		}
		connection.close();
		return SQLResult;
	}

	public void connectDBupdateData(String Driver, String DBurl, String DBusername, String DBpassword, String DBname,
			String query) throws SQLException {
		connection = getConnection(Driver, DBurl, DBusername, DBpassword, DBname);
		statement = connection.createStatement();
		statement.executeUpdate(query);
		connection.close();
	}

	public void connectDBinsertData(String Driver, String DBurl, String DBusername, String DBpassword, String DBname,
			String query, String tablecolumname) throws SQLException {
		connection = getConnection(Driver, DBurl, DBusername, DBpassword, DBname);

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, tablecolumname);
		prepareStatement.executeUpdate();
		connection.close();

	}

	public void connectDBdeleteData(String Driver, String DBurl, String DBusername, String DBpassword, String DBname,
			String query) throws SQLException {
		connection = getConnection(Driver, DBurl, DBusername, DBpassword, DBname);
		statement = connection.createStatement();
		statement.executeUpdate(query);
		connection.close();

	}

	public void writeDataFromTableToExcel(String Driver, String DBurl, String DBusername, String DBpassword,
			String DBname, String query, String tablecolumname, String sheetname, String sheetcolumname,
			String filepath) throws SQLException, FileNotFoundException {
		List<String> list = new ArrayList<String>();
		XSSFWorkbook workbook;

		workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet(sheetname);
		sheet.createRow(0).createCell(0).setCellValue(sheetcolumname);
		connection = getConnection(Driver, DBurl, DBusername, DBpassword, DBname);
		statement = connection.createStatement();
		String SQLResult = "";
		resultSet = statement.executeQuery(query);
		while (resultSet.next()) {

			java.sql.ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			// int coulumn_type = resultSetMetaData.getColumnTypeName(tablecolumname);
			SQLResult = resultSet.getString(tablecolumname);
			list.add(SQLResult);
			System.out.println(SQLResult);

		}
		connection.close();
		for (int i = 0; i < list.size(); i++) {
			sheet.createRow(i + 1).createCell(0).setCellValue(list.get(i));
		}
		FileOutputStream fos = new FileOutputStream(filepath);
		try {
			workbook.write(fos);
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void WriteDataFromExceltoDB(String Driver, String DBurl, String DBusername, String DBpassword, String DBname,
			String query, String sheetname, String filepath) {

		connection = getConnection(Driver, DBurl, DBusername, DBpassword, DBname);

		try {

			PreparedStatement prepareStatement = connection.prepareStatement(query);
			FileInputStream fileinputStream = new FileInputStream(new File(filepath));
			XSSFWorkbook workbook = new XSSFWorkbook(fileinputStream);
			XSSFSheet sheet = workbook.getSheet(sheetname);
			Iterator iterator = sheet.rowIterator();

			while (iterator.hasNext()) {
				Row row = (Row) iterator.next();
				System.out.println("Row value fetched");
				Iterator<Cell> cellIterator = row.cellIterator();
				System.out.println("Cell Iterator invoked");
				int index = 1;
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					System.out.println("getting cell value");

					// switch (cell.getCellType()) {
					// case Cell.CELL_TYPE_STRING: // handle string columns
					prepareStatement.setString(index, cell.getStringCellValue());
					// break;
					// case Cell.CELL_TYPE_NUMERIC: // handle integer/double columns

					prepareStatement.setInt(index, (int) cell.getNumericCellValue());
					// break;

					index++;

				}

				prepareStatement.executeUpdate();
			}

			fileinputStream.close();
			prepareStatement.close();

			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
