package org.selenium.core;

/**
 * Enum for Application Configuration Parameter Keys <br>
 * <br>
 * Please have matching enums for all application configuration parameters used
 * in this application. <br>
 * Defining and using the parameter keys from here will easier maintaining the
 * keys.
 * 
 * @author venkateswarlu.gatena
 */
public enum AppConfigParamKeys {
	REMOTE_ADDRESS("remoteAddress", String.class), 
	WEB_AGENT("webAgent", String.class), 
	WEB_DRIVER_LOCATION_IE("webDriverLocationIE", String.class), 
	WEB_DRIVER_LOCATION_CHROME("webDriverLocationChrome", String.class),  
	APPLICATION_URL("applicationURL", String.class), 
	APPLICATION_USER_ID("applicationUserId", String.class), 
	APPLICATION_PASSWORD("applicationPassword", String.class), 
	IMPLICITLY_WAIT_IN_SECONDS("implicitlyWaitInSeconds", String.class),  
	FIREFOX_PROFILE_NAME("firefoxProfileName", String.class), 			
	DB_JDBC_DRIVER("db.jdbc.driver", String.class),
	DB_JDBC_URL("db.jdbc.url", String.class),
	DB_USER("db.user", String.class),
	DB_PASSWORD("db.password", String.class),
	NUM_OF_THREADS("numOfThreads", Integer.class);

	private String parameterKey;
	private Class<?> parameterValueType;

	private AppConfigParamKeys(String key, Class<?> type) {
		this.parameterKey = key;
		this.parameterValueType = type;
	}

	public String getKey() {
		return this.parameterKey;
	}

	public Class<?> getValueType() {
		return this.parameterValueType;
	}
}
