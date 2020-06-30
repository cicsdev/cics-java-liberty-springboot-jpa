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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class which retrieves the data requested by the REST controller
 * 
 * @Autowired Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities
 * @Service MArks a class as providing business logic
 */

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * @return a list of employees (all employees in the table EMP)
	 */
	public List<Emp> selectAll() {
		System.out.println("selectAll: EmployeeService");
		List<Emp> employees = new ArrayList<>();  //define a new list
		employeeRepository.findAll()                   //iterate through each data item
		.forEach(employees::add);                      // and add to list
		System.out.println("returning from selectAll");
		return employees;

	}

	/**
	 * @param empNo
	 * @return a list of employees with the employee number specified
	 */
	public Optional<Emp> selectWhereEmpno(String empNo) {
		/*
		 * Return all rows for a specific employee number
		 */
		
		return employeeRepository.findById(empNo);
	}


	/**
	 * @param fName - employee first name
	 * @param lName - employee last name
	 * @return a message indicating the result of adding an employee record
	 */
	public String addEmployee(String fName, String lName) {
		/*
		 *  Add a new employee.
		 *      Firstname and lastname are passed in 
		 *      
		 *      for demonstration purposes all the other fields are set by this method
		 *      
		 */

		//generate an empNo between 300000 and 999999
		int max = 999999;
		int min = 300000;
		String empno = String.valueOf((int) Math.round((Math.random()*((max-min)+1))+min));

		//for demo purposes hard code all the remaining fields (except first name and last name) 
		String midInit = "A";
		String workdept = "E21";
		String phoneNo = "1234";
		LocalDate today = LocalDate.now();
		String hireDate = today.toString();
				
		String job = "Engineer";
		int edLevel =3 ;
		String sex ="M";
		String birthDate = "1999-01-01" ;
		long salary = 20000;
		long bonus= 1000;
		long comm = 1000;
		
		Emp newEmployee = new Emp(empno,fName,midInit,lName,workdept,phoneNo,hireDate,job,edLevel,sex,birthDate,salary,bonus,comm);

		try {
			employeeRepository.save(newEmployee);
			
			return "employee " + empno + " added";
		} catch (Exception e) {
			return "employee insert failed try again";
		}

	}


	/**
	 * @param empNo
	 * @return a message indicating the result of deleting an employee record
	 */
	public String deleteEmployee(String empNo)	{
		/*
		 *  Delete an employee based on the empNo passed in
		 *  
		 */
		try {
			employeeRepository.deleteById(empNo);

			return "employee " + empNo + " deleted";
		} catch (Exception e) {

			return "employee delete failed try again";
		}
	}


	/**
	 * @param employeeToUpdate
	 * @param newSalary
	 * @return a message indicating the result of updating an employee record
	 */
	public String updateEmployee(String employeeToUpdate, long newSalary) {
		/*
		 * Update a specified employee's salary based on the empNo passed to the salary passed in.
		 * 
		 */
		
		Emp emp = null;
		if (employeeRepository.existsById(employeeToUpdate)) {
			emp = employeeRepository.getOne(employeeToUpdate);
			emp.setSalary(newSalary);
		} else
		{
			return "Employee " + employeeToUpdate + " does not exist";
		}
		
		try {
			employeeRepository.save(emp);
			return "Employee " + employeeToUpdate + " salary updated";
		} catch (Exception e) {
			System.out.println(e);
			return "Employee " + employeeToUpdate + " update failed - try again";
		}

	}		
		
		
	/**
	 * @param empNo
	 * @return an employee record based on the employee number passed 
	 */
	public Emp selectWhereEmpno1(String empNo) {
		return employeeRepository.findByEmpNo(empNo);
	}
}
