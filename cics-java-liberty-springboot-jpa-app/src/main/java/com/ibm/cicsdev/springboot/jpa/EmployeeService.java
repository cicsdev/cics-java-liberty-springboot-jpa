/* Licensed Materials - Property of IBM                                   */
/*                                                                        */
/* SAMPLE                                                                 */
/*                                                                        */
/* (c) Copyright IBM Corp. 2020 All Rights Reserved                       */
/*                                                                        */
/* US Government Users Restricted Rights - Use, duplication or disclosure */
/* restricted by GSA ADP Schedule Contract with IBM Corp                  */
/*                                                                        */

package com.ibm.cicsdev.springboot.jpa;

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
 * @Service Marks a class as providing business logic
 */

@Service
public class EmployeeService 
{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * @return a list of employees (all employees in the table EMP)
	 */
	public List<Emp> selectAll() 
	{
		// define a new list and iterate through each data item adding it to the list
		List<Emp> employees = new ArrayList<>();  
		employeeRepository.findAll().forEach(employees::add);		
		return employees;
	}

	
	/**
	 * Return all rows for a specific employee number
	 * 
	 * @param empNo
	 * @return a list of employees with the employee number specified
	 */
	public Optional<Emp> selectWhereEmpno(String empNo) 
	{			
		return employeeRepository.findById(empNo);
	}


	/**
	 * Add a new employee.
	 * 
	 * Firstname and lastname are passed in for demonstration purposes all the other fields are set by this method
	 * 
	 * @param fName - employee first name
	 * @param lName - employee last name
	 * @return a message indicating the result of adding an employee record
	 */
	public String addEmployee(String fName, String lName) 
	{
		// generate an empNo between 300000 and 999999
		int max = 999999;
		int min = 300000;
		String empno = String.valueOf((int) Math.round((Math.random()*((max-min)+1))+min));

		// for demo purposes hard code all the remaining fields (except first name and last name) 
		String midInit = "A";
		String workdept = "E21";
		String phoneNo = "1234";
				
		String hireDate = LocalDate.now().toString();
				
		String job = "Engineer";
		int edLevel =3 ;
		String sex ="M";
		String birthDate = "1999-01-01" ;
		long salary = 20000;
		long bonus= 1000;
		long comm = 1000;
		
		Emp newEmployee = new Emp(empno,fName,midInit,lName,workdept,phoneNo,hireDate,job,edLevel,sex,birthDate,salary,bonus,comm);
		try 
		{
			employeeRepository.save(newEmployee);		
			return "employee " + empno + " added";
		} 
		catch (Exception e) 
		{
			return "employee insert failed try again";
		}
	}


	/**
	 *  Delete an employee based on the empNo passed in
	 *  
	 * @param empNo
	 * @return a message indicating the result of deleting an employee record
	 */
	public String deleteEmployee(String empNo)	
	{
		try 
		{
			employeeRepository.deleteById(empNo);

			return "employee " + empNo + " deleted";
		} 
		catch (Exception e) 
		{
			return "employee delete failed try again";
		}
	}


	/**
	 * Update a specified employee's salary based on the empNo passed to the salary passed in.
	 * 
	 * @param employeeToUpdate
	 * @param newSalary
	 * @return a message indicating the result of updating an employee record
	 */
	public String updateEmployee(String employeeToUpdate, long newSalary) 
	{		
		Emp emp = null;
		
		// Get the employee and modify salary
		if (employeeRepository.existsById(employeeToUpdate)) 
		{
			emp = employeeRepository.getOne(employeeToUpdate);
			emp.setSalary(newSalary);
		} 
		else
		{
			return "Employee " + employeeToUpdate + " does not exist";
		}
		
		// Save the employee details back
		try 
		{
			employeeRepository.save(emp);
			return "Employee " + employeeToUpdate + " salary updated";
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			return "Employee " + employeeToUpdate + " update failed - try again";
		}
	}		
		
		
	/**
	 * @param empNo
	 * @return an employee record based on the employee number passed 
	 */
	public Emp selectWhereEmpno1(String empNo) 
	{
		return employeeRepository.findByEmpNo(empNo);
	}
}
