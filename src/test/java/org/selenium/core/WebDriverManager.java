package org.selenium.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * A Manager class that manages the WebDriver instance per thread
 * 
 * This class essentially uses a concept in java called ThreadLocal variables.
 * 
 * This class has been purposefully given only package visibility, so that:<br>
 * - Only framework classes will have access to it.<br>
 * - The client using this framework don't have to access this class directly.<br>
 * 
 * @author venkateswarlu.gantena
 *
 */
class WebDriverManager {
	// Thread local variable containing reference to WebDriver instance for each
	// thread
	private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected WebDriver initialValue() {
			return WebDriverFactory.createInstance();
		}
	};

	/**
	 * Return reference to an instance of WebDriver for that specific thread.
	 * 
	 * @return the webDriver
	 */
	static WebDriver getWebDriver() {
		return webDriver.get();
	}

	/**
	 * Removes the instance of WebDriver for that specific thread.
	 * 
	 * @return the webDriver
	 */
	static void removeWebDriver() {
		webDriver.get().quit();
		webDriver.remove();
	}

	/**
	 * Register a listener to given WebDriver instance.
	 * 
	 * @param webDriver
	 * @param eventListener
	 */
	static EventFiringWebDriver registerEventListener(WebDriver webDriver,
			WebDriverEventListener eventListener) {
		// Creating EventFiringWebDriver instance and Register the Listener to
		// it...
		return new EventFiringWebDriver(webDriver).register(eventListener);
	}
}
