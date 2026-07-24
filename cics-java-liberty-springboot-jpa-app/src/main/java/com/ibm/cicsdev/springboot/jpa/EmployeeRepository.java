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

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * JPA repository definition
 *
 */
public interface EmployeeRepository extends CrudRepository<Emp, String> 
{
	/**
	 * @param empNo
	 * @return employees with employee number empNo 
	 */
	public Emp findByEmpNo(String empNo); 

	/**
	 * @param empNo
	 * @param newSalary
	 * @return integer
	 */
	@Modifying
	@Query("update Emp e set e.salary = ?2 where e.empNo = ?1")
	public int setNewSalary(String empNo, long newSalary);

	/**
	 * @param employeeToUpdate
	 * @return one employee 
	 */
	public Emp getOne(String employeeToUpdate);

}
