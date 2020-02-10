
package com.tek.trp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author raadari
 *
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
	

/**
 * @author raadari
 *
 */
//@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {
	
	private static final Logger logger=LoggerFactory.getLogger(DBConfiguration.class);
	
	private String driverClassName;
	private String url;
	private String username;
	private String password;

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Bean
	public String databaseConnection() {
		logger.info("DB connection");
		logger.info(driverClassName);
		logger.info(url);
		return "DB connection for DEV ";
	}

}