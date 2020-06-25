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

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Tony Fitzgerald
 * 
 * jpa repository defintion
 *
 */
public interface EmployeeRepository extends CrudRepository<Emp, String> {

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
