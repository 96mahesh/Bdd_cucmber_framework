/**
 * @author Manojprabakaran
   Methods - 

/***************************************************/

package com.testperform.web.bdd.Integrations.Common_Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class DBconnection {

//	JavascriptExecutor javascriptExecutor = (JavascriptExecutor) DriverFactory.getInstance().getWebDriver();
//	Actions action = new Actions(DriverFactory.getInstance().getWebDriver());

	public String handle;
	public Statement stmt;
	public ResultSet rs;
	public Connection con;

	public String ConnectDBGetdata(String dBURL, String dBName, String dBPassword, String dBUserName, String Query01,
			String DataColumnName) throws Throwable {

		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://" + dBURL + ";DatabaseName= " + dBName + ";user=" + dBUserName
				+ ";password=" + dBPassword + "";

		con = DriverManager.getConnection(connectionUrl);
		stmt = con.createStatement();
		String SQLResult = null;
		String SQL = Query01;
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			SQLResult = rs.getString(DataColumnName);
			System.out.println(SQLResult);

		}
		con.close();
		return SQLResult;
	}

	public void ConnectDBUpdatedata(String dBURL, String dBName, String dBPassword, String dBUserName, String Query01)
			throws Throwable {

		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://" + dBURL + ";DatabaseName= " + dBName + ";user=" + dBUserName
				+ ";password=" + dBPassword + "";

		con = DriverManager.getConnection(connectionUrl);
		stmt = con.createStatement();
		String SQLResult = null;
		String SQL = Query01;
		stmt.execute(SQL);
		con.close();

	}

//------------------------------------------------------------------------------------------------------------------------------------------------

	/*--------------------Craated below SQL method to get value from query if results doesnt have any column name Ex: count of rows */
	public String ConnectDBGetdatawithoutDatacolumnname(String dBURL, String dBName, String dBPassword,
			String dBUserName, String Query01) throws Throwable {

		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://" + dBURL + ";DatabaseName= " + dBName + ";user=" + dBUserName
				+ ";password=" + dBPassword + "";

		con = DriverManager.getConnection(connectionUrl);
		stmt = con.createStatement();
		String SQLResult = null;
		String SQL = Query01;
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			SQLResult = rs.getString(1);
			System.out.println(SQLResult);

		}
		con.close();
		return SQLResult;
	}

	/*------------------------------DB Method to get Data---------------------------------------------------*/
	public String ConnectDB_VerifyColums(String dBURL, String dBName, String dBPassword, String dBUserName,
			String Query, String col1, String col2, String col3) throws Throwable {

		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://" + dBURL + ";DatabaseName= " + dBName + ";user=" + dBUserName
				+ ";password=" + dBPassword + "";

		con = DriverManager.getConnection(connectionUrl);
		stmt = con.createStatement();
		String SQLResult = null;
		String SQL = Query;
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			SQLResult = rs.getString(col1);
			SQLResult = rs.getString(col2);
			System.out.println("col1 " + rs.getString(col1));
			System.out.println("col2  " + rs.getString(col2));
			System.out.println("Row " + rs.getRow());// Entire row

			System.out.println("CusAccountNAICSCodeID :" + rs.getString(1) + " ,AccountID" + rs.getString(2)
					+ " , NAICSCodeID :" + rs.getString(3));
		}
		con.close();
		return SQLResult;
	}

}
