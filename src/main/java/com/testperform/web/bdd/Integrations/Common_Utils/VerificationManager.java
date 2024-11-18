package com.testperform.web.bdd.Integrations.Common_Utils;

import org.testng.Assert;

import com.testperform.web.bdd.Integrations.Report_Utils.ReportManager;

public class VerificationManager {

	public static void validateResponse(Object actual, Object expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			ReportManager.logPass(
					message + "   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
		} catch (AssertionError assertionError) {
			ReportManager.logFail(
					message + "   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
			Assert.fail(message);
		}
	}

	public static void validateResponse(boolean result, String message) {
		try {
			Assert.assertTrue(result);
			ReportManager.logPass("<b><i>" + message + "</b></i>");
			// report.info("PASS : " + message);
		} catch (AssertionError assertionError) {
//			report.error("FAIL : " + message);
			ReportManager.logFail("<b><i>" + message + "</b></i>");
			Assert.fail(message);
		}
	}

}
