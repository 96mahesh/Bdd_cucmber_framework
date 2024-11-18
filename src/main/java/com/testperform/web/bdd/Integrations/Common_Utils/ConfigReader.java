package com.testperform.web.bdd.Integrations.Common_Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.testperform.web.bdd.enums.EnvType;

public class ConfigReader {
	private static final String SEND_EMAIL_TO_USERS = "send_email_to_users";
	private static final String OVERRIDE_REPORTS = "override_reports";
	private static final String SKIPPED_STEPS_SCREENSHOT = "skipped_steps_screenshot";
	private static final String PASSED_STEPS_SCREENSHOT = "passed_steps_screenshot";
	private static final String FAILED_STEPS_SCREENSHOT = "failed_steps_screenshot";
	private static final String RETRY_FAILED_TESTS = "retry_failed_tests";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String BASE_URL = "baseUrl";
	private static final String REQUEST_DETAILS_IN_REPORTS = "request_details_in_reports";

	private static final String ENV = "env";
	private static final String CONFIG_PROPERTIES = "_config.properties";

	/* Default config file is stg_config.properties */
	private static final String STG_CONFIG_PROPERTIES = "stg" + CONFIG_PROPERTIES;
	private static final String PROD_CONFIG_PROPERTIES = "prod" + CONFIG_PROPERTIES;
	private static final String QA_CONFIG_PROPERTIES = "qa" + CONFIG_PROPERTIES;
	private static final String INT_CONFIG_PROPERTIES = "int" + CONFIG_PROPERTIES;

	private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/Configs/";

	private Properties properties;
	// private final Properties properties;
	private static ConfigReader configReader;

	private ConfigReader() {
		String env = System.getProperty(ENV, EnvType.STAGE.toString());

		switch (EnvType.valueOf(env)) {
		/* Only STAGE is working, Rest are taken for example */

		case STAGE: {
			properties = getConfigPropertyFile(STG_CONFIG_PROPERTIES);
			break;
		}
		case INT: {
			properties = getConfigPropertyFile(INT_CONFIG_PROPERTIES);
			break;
		}
		case QA: {
			properties = getConfigPropertyFile(QA_CONFIG_PROPERTIES);
			break;
		}
		case PRODUCTION: {
			properties = getConfigPropertyFile(PROD_CONFIG_PROPERTIES);
			break;
		}
		default: {
			throw new IllegalStateException("Invalid EnvType: " + env);
		}
		}

	}

	private Properties getConfigPropertyFile(String configFile) {
		return PropertyUtils.propertyLoader(RESOURCES_PATH + configFile);
	}

	private String getPropertyValue(String propertyKey) {
		String prop = properties.getProperty(propertyKey);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property " + propertyKey + " is not specified in the config.properties file");
		}
	}

	public static ConfigReader getInstance() {
		if (configReader == null) {
			configReader = new ConfigReader();
		}
		return configReader;
	}

	public String getBaseUrl() {
		return getPropertyValue(BASE_URL);
	}

	public String getUsername() {
		return getPropertyValue(USERNAME);
	}

	public String getPassword() {
		return getPropertyValue(PASSWORD);
	}

	public String getFailedStepsScreenshot() {
		return getPropertyValue(FAILED_STEPS_SCREENSHOT);
	}

	public String getPassedStepsScreenshot() {
		return getPropertyValue(PASSED_STEPS_SCREENSHOT);
	}

	public String getSkippedStepsScreenshot() {
		return getPropertyValue(SKIPPED_STEPS_SCREENSHOT);
	}

	public String getRetryFailedTests() {
		return getPropertyValue(RETRY_FAILED_TESTS);
	}

	public String getOverrideReports() {
		return getPropertyValue(OVERRIDE_REPORTS);
	}

	public String getSendEmailToUsers() {
		return getPropertyValue(SEND_EMAIL_TO_USERS);
	}

	public String getRequestDetailsInReports() {
		return getPropertyValue(REQUEST_DETAILS_IN_REPORTS);
	}

	/*
	 * loadPropertyFile method used for loading the properties file
	 * 
	 * @Param filePath
	 */
	public static Properties loadPropertyFile(String filePath) {
		// Read from properties file
		File file = new File(filePath);
		Properties prop = new Properties();

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
			prop.load(fileInput);
		} catch (Exception e) {
			// LogUtil.errorLog(ConfigReader.class, "Caught the exception", e);

		}
		return prop;

	}

	/**
	 * will get sting value from properties file
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {

		Properties prop = loadPropertyFile("src/test/resources/Configs/stg_config.properties");

		return prop.getProperty(key);
	}

	/**
	 * will get int value from properties file
	 * 
	 * @param key
	 * @return
	 */
	public static int getIntValue(String key) {
		Properties prop = loadPropertyFile("src/test/resources/Configs/stg_config.properties");

		String strKey = prop.getProperty(key);

		return Integer.parseInt(strKey);
	}

	/**
	 * will get sting value from properties file
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue2(String path, String key) {

		Properties prop = loadPropertyFile(path);

		return prop.getProperty(key);
	}

	/**
	 * will set sting value from properties file
	 * 
	 * @param key
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws IOException
	 */
	public static void setStringValue(String path, String key, String value) throws IOException {

		FileOutputStream output = new FileOutputStream(path);

		Properties prop = new Properties();

		// set the properties value
		prop.setProperty(key, value);

		// save properties to project root folder
		prop.store(output, null);
		output.close();

	}

	public static void moveFile(String outFileName, String sourcePath, String destinationPath, String fileextention)
			throws IOException {
		File source = new File(sourcePath);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String ts = sdf.format(source.lastModified());
		File destination = new File(destinationPath + outFileName + ts);
		FileUtils.copyFile(source, destination);
		System.out.println(" new file name is " + outFileName);
	}

}
