package com.ibm.cicsdev.springboot.emp.jpa;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Emp, String> {

	 public Emp findByEmpNo(String empNo); 
	 
	 @Modifying
	 @Query("update Emp e set e.salary = ?2 where e.empNo = ?1")
	 public int setNewSalary(String empNo, long newSalary);

	public Emp getOne(String employeeToUpdate);
	
}
