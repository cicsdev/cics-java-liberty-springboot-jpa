/* Licensed Materials - Property of IBM                                   */
/*                                                                        */
/* SAMPLE                                                                 */
/*                                                                        */
/* (c) Copyright IBM Corp. 2020 All Rights Reserved                       */
/*                                                                        */
/* US Government Users Restricted Rights - Use, duplication or disclosure */
/* restricted by GSA ADP Schedule Contract with IBM Corp                  */
/*                                                                        */

package com.ibm.cicsdev.springboot.emp.jpa;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class - used to create DataSource bean 
 *    and a Bean to set property spring.jpa.hibernate.naming.physical-strategy
 */
@Configuration
public class Config {
	// name the dataSource jndi name
	private static final String DATA_SOURCE = "jdbc/jdbcDataSource-bean";

	/**
	 * @return set the DataSource the application will use
	 */
	@Bean
	public DataSource dataSource() {			
		try {
			// Look up the connection factory from Liberty
			DataSource ds = InitialContext.doLookup(DATA_SOURCE);
			return ds;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	} 
	
	/**
	 * @return new PhysicalNamingStrategyStandardImpl();
	 * 
	 * spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
	 * 
	 */
	@Bean
	public PhysicalNamingStrategy physical() {
	    return new PhysicalNamingStrategyStandardImpl();
	}
}
