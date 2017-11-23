package org.selenium.core;

import java.util.Iterator;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to build and manage the Application configuration. <br>
 * <br>
 * This utility loads the application configuration from config.xml file under
 * project resources. <br>
 * <br>
 * This is designed to support loading configurations from multiple resources
 * using <i>Apache Commons Configuration</i> libraries. i.e., properties files,
 * xml files, ini files, JNDI, a database, etc. Please remember that
 * configuration files included first can override properties in configuration
 * files included later.
 * 
 * @author venkateswarlu.gantena
 *
 */
public class AppConfigManager {
	private static final Logger log = LoggerFactory.getLogger(AppConfigManager.class);

	private static final Configuration applicationConfig;

	static {
		// Load configuration from config.xml file
		try {
			applicationConfig = getApplicationConfiguration("config.xml");
			printConfiguration(applicationConfig);
		} catch (ConfigurationException e) {
			String msg = String
					.format("Error in AppConfigManager while loading ApplicationConfiguration from %1$s file",
							"config.xml");
			log.error(msg, e);
			throw new IllegalStateException(msg, e);
		}
	}

	/**
	 * Get reference to Configuration. Allows looking up configuration
	 * parameters.
	 * 
	 * @return
	 */
	public static Configuration getConfiguration() {
		return applicationConfig;
	}

	/**
	 * Returns a single CombinedConfiguration object by combining various
	 * configuration sources defined in given centralized configuration file
	 * 
	 * @param fileName
	 *            The xml configuration file with multiple configuration
	 *            definitions
	 * 
	 * @return a single CombinedConfiguration object by combining various
	 *         configuration sources defined in given centralized configuration
	 *         file.
	 * 
	 * @throws ConfigurationException
	 */
	static Configuration getApplicationConfiguration(String fileName)
			throws ConfigurationException {
		DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder(
				fileName);
		return builder.getConfiguration();
	}

	static Configuration getTestSuiteConfiguration(String fileName)
			throws ConfigurationException {
		XMLConfiguration config = new XMLConfiguration(fileName);
		config.setExpressionEngine(new XPathExpressionEngine());
		return config;
	}

	/**
	 * @param config
	 */
	private static void printConfiguration(Configuration config) {
		Iterator<String> keys = config.getKeys();
		while (keys.hasNext()) {
			String key = keys.next();
			log.info(String.format("%1$s = %2$s", key, config.getString(key)));
		}
		/*
		 * log.info("databases.database(0).url = " +
		 * config.getString("databases.database(0).url"));
		 * log.info("databases.database(1).url = " +
		 * config.getString("databases/database[name = 'production']/url"));
		 */
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Added main method to help troubleshooting issues with
		// AppConfigManager...
	}
}
