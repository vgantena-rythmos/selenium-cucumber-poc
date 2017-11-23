package org.selenium.core;

import static org.selenium.core.AppConfigManager.getConfiguration;
import static org.selenium.core.WebDriverType.getWebDriverTypeByAgentName;
import static org.selenium.core.WebDriverType.validwebAgents;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Factory class to create and return instance of WebDriver
 * 
 * This class has been purposefully given only package visibility, so that:<br>
 * - Only framework classes will have access to it.<br>
 * - The client using this framework don't have to access this class directly.<br>
 * 
 * @author ravindar.kantareddy
 *
 */
class WebDriverFactory {
	private static final Logger log = LoggerFactory
			.getLogger(WebDriverFactory.class);

	private static WebDriverType webDriverType;

	private static URL remoteAddress;

	static {
		String remoteAddrURL = getConfiguration().getString(
				AppConfigParamKeys.REMOTE_ADDRESS.getKey());
		if (remoteAddrURL != null
				&& !remoteAddrURL.trim().equalsIgnoreCase("NA")) {
			try {
				remoteAddress = new URL(getConfiguration().getString(
						AppConfigParamKeys.REMOTE_ADDRESS.getKey()));
			} catch (MalformedURLException e) {
				String msg = "Confiuration error: invalid value for remoteAddress property = "
						+ getConfiguration().getString(
								AppConfigParamKeys.REMOTE_ADDRESS.getKey());
				log.error(msg, e);
				throw new IllegalStateException(msg, e);
			}
		}
		String webAgent = getConfiguration().getString(
				AppConfigParamKeys.WEB_AGENT.getKey());
		if (webAgent == null) {
			// The webAgent system property not present, exit...
			String message = String
					.format("The web agent name must be supplied via the %s system property or selenium.properties file; i.e., %s",
							AppConfigParamKeys.WEB_AGENT.getKey(),
							validwebAgents());
			throw new IllegalStateException(message);
		}
		WebDriverFactory.webDriverType = getWebDriverTypeByAgentName(webAgent,
				null);
		if (WebDriverFactory.webDriverType == null) {
			String message = String
					.format("Invalid value (%s) for %s system property; Value must be one of these: %s.",
							webAgent, AppConfigParamKeys.WEB_AGENT.getKey(),
							validwebAgents());
			throw new IllegalStateException(message);
		}
	}

	/**
	 * Creates and returns an instance of WebDriver using given configuration
	 * instructions. <br>
	 * <br>
	 * Typically, the {@link WebDriverManager} request a new WebDriver instance
	 * once per each thread.
	 * 
	 * @return instance of WebDriver
	 */
	static WebDriver createInstance() {
		WebDriver webDriver = null;
		switch (WebDriverFactory.webDriverType) {
		case CHROME_DRIVER:
			webDriver = getChromeDriver();
			break;
		case FIREFOX_DRIVER:
			webDriver = getFirefoxDriver();
			break;
		case INTERNET_EXPLORER_DRIVER:
			webDriver = getInternetExplorerDriver();
			break;
		case SAFARI_DRIVER:
			webDriver = getSafariDriver();
			break;
		default:
			webDriver = new HtmlUnitDriver();
			break;
		}
		long implicitlyWaitInSeconds = getConfiguration().getLong(
				AppConfigParamKeys.IMPLICITLY_WAIT_IN_SECONDS.getKey());
		if (implicitlyWaitInSeconds > 0) {
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts()
					.implicitlyWait(implicitlyWaitInSeconds, TimeUnit.SECONDS);
		}
		return webDriver;
	}

	/**
	 * Create an instance of SafariDriver
	 * 
	 * @return
	 */
	private static SafariDriver getSafariDriver() {
		return new SafariDriver();
	}

	/**
	 * Create an instance of InternetExplorerDriver
	 * 
	 * @return
	 */
	private static WebDriver getInternetExplorerDriver() {
		if (remoteAddress != null) {
			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();
			capabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			return new RemoteWebDriver(remoteAddress, capabilities);
		} else {
			String webDriverLocationIE = getConfiguration().getString(
					AppConfigParamKeys.WEB_DRIVER_LOCATION_IE.getKey());
			if (webDriverLocationIE == null) {
				throw new IllegalStateException(
						String.format(
								"The IE webdirver location name must be supplied via the %s system property or selenium.properties file",
								AppConfigParamKeys.WEB_DRIVER_LOCATION_IE
										.getKey()));
			}
			System.setProperty("webdriver.ie.driver", webDriverLocationIE);
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability(
					InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
					true);
			caps.setCapability("ensureCleanSession", true);
			return new InternetExplorerDriver(caps);
		}
	}

	/**
	 * Create an instance of FirefoxDriver
	 * 
	 * @return
	 */
	private static WebDriver getFirefoxDriver() {
		if (remoteAddress != null) {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxDriver.PROFILE, new FirefoxProfile());
			return new RemoteWebDriver(remoteAddress, capabilities);
		} else {
			String firefoxProfileName = getConfiguration().getString(
					AppConfigParamKeys.FIREFOX_PROFILE_NAME.getKey());
			if (firefoxProfileName != null) {
				ProfilesIni profilesIni = new ProfilesIni();
				FirefoxProfile firefoxProfile = profilesIni
						.getProfile(firefoxProfileName);
/*				try {
					System.out.println(firefoxProfile.toJson());
				} catch (IOException e) {
					e.printStackTrace();
				}*/
				return new FirefoxDriver((Capabilities) firefoxProfile);
			}
			FirefoxProfile profile= new FirefoxProfile();
/*			profile.setPreference("browser.startup.homepage", "www.google.com");
			profile.setPreference("intl.accept_languages", "no,en-us,en");
			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(false);*/
			return new FirefoxDriver((Capabilities) profile);
		}
	}

	/**
	 * Create an instance of ChromeDriver
	 * 
	 * @return
	 */
	private static WebDriver getChromeDriver() {
		if (remoteAddress != null) {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			return new RemoteWebDriver(remoteAddress, capabilities);
		} else {
			String webDriverLocationChrome = getConfiguration().getString(
					AppConfigParamKeys.WEB_DRIVER_LOCATION_CHROME.getKey());
			if (webDriverLocationChrome == null) {
				throw new IllegalStateException(
						String.format(
								"The IE webdirver location name must be supplied via the %s system property or selenium.properties file",
								AppConfigParamKeys.WEB_DRIVER_LOCATION_CHROME
										.getKey()));
			}
			System.setProperty("webdriver.chrome.driver",
					webDriverLocationChrome);
			return new ChromeDriver();
		}
	}
}
