/*
 * Copyright 2012-2020 the original author or authors.
 *
 * Copyright IBM Corp. 2020 All Rights Reserved   
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
