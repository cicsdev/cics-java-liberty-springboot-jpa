package com.ibm.cicsdev.springboot.emp.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@ServletComponentScan
public class RelationalDataAccessApplication extends SpringBootServletInitializer  {
	
	public static void main(String args[]) {
		System.out.println(">>> starting application Employee");
		SpringApplication.run(RelationalDataAccessApplication.class, args);
	}
	

}
