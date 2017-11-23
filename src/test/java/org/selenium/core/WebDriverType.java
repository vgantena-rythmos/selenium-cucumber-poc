package org.selenium.core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Enum for available web drivers (i.e., ChromeDriver, EventFiringWebDriver,
 * FirefoxDriver, HtmlUnitDriver, InternetExplorerDriver, RemoteWebDriver,
 * SafariDriver)
 * 
 * @author ravindar.kantareddy
 *
 */
enum WebDriverType {
	CHROME_DRIVER("chrome", ChromeDriver.class), FIREFOX_DRIVER("firefox",
			FirefoxDriver.class), INTERNET_EXPLORER_DRIVER("internet explorer",
			InternetExplorerDriver.class), SAFARI_DRIVER("safari",
			SafariDriver.class), HTMLUNIT_DRIVER("htmlunit",
			HtmlUnitDriver.class);

	private String browserName;
	private Class<?> driverClass;

	private WebDriverType(String browserName, Class<?> driverClass) {
		this.browserName = browserName;
		this.driverClass = driverClass;
	}

	/**
	 * @return the webAgent
	 */
	String getBrowserName() {
		return browserName;
	}

	/**
	 * @param agentName
	 * @param def
	 *            a default driver to return when no matching found for given
	 *            agentName
	 * @return
	 */
	static WebDriverType getWebDriverTypeByAgentName(String agentName,
			WebDriverType def) {
		for (WebDriverType type : WebDriverType.values()) {
			if (type.getBrowserName().equalsIgnoreCase(agentName)) {
				return type;
			}
		}
		return def;
	}

	/**
	 * @return valid web agents
	 */
	static String validwebAgents() {
		StringBuilder sb = new StringBuilder();
		boolean isFirstIteration = true;
		for (WebDriverType type : WebDriverType.values()) {
			if (!isFirstIteration) {
				sb.append(", ");
			}
			sb.append(type.getBrowserName());
			isFirstIteration = false;
		}
		return sb.toString();
	}
}
