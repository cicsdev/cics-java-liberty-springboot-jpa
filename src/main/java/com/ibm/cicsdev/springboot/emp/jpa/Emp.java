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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * class representing the EMP table
 */

@Entity
public class Emp {
	
	@Id	
	@Column(name = "EMPNO")
	private String empNo;
	private String firstNme;
	private String midinit;
	private String lastName;
	private String workdept;
	private String phoneNo;
	private String hireDate;
	private String job;
	private int edLevel;
	private String sex;
	private String birthDate;
	private long salary;
	private long bonus;
	private long comm;

	/**
	 * no arg constructor
	 */
	public Emp() {
		
	}
	
	/**
	 * @param empNo		- employee Number 			- 6 characters
	 * @param firstNme 	- employee first name 		- 12 characters
	 * @param midinit	- employee middle initial	- 1 character
	 * @param lastName	- employee last name		- 15 characters
	 * @param workdept	- employee work department 	- 3 characters
	 * @param phoneNo	- employee phone number 	- 4 characters
	 * @param hireDate	- employee hire date
	 * @param job		- job title					- 8 characters
	 * @param edLevel	- employee education level	- integer (1,2, or 3)
	 * @param sex		- employee gender 			- 1 character
	 * @param birthDate	- employee birth date
	 * @param salary	- employee salary amount	- decimal (9,2)
	 * @param bonus		- employee bonus amount		- decimal (9,2)
	 * @param comm		- employee commission amount-decimal (9,2)
	 */
	public Emp(String empNo, String firstNme, String midinit, String lastName, String workdept, String phoneNo,
			String hireDate, String job, int edLevel, String sex, String birthDate, long salary, long bonus, long comm) {
		super();
		this.empNo = empNo;
		this.firstNme = firstNme;
		this.midinit = midinit;
		this.lastName = lastName;
		this.workdept = workdept;
		this.phoneNo = phoneNo;
		this.hireDate = hireDate;
		this.job = job;
		this.edLevel = edLevel;
		this.sex = sex;
		this.birthDate = birthDate;
		this.salary = salary;
		this.bonus = bonus;
		this.comm = comm;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + 
				", firstName=" + firstNme + 
				", midinit=" + midinit + 
				", lastName=" + lastName + 
				", workdept=" + workdept + 
				", phoneNo=" + phoneNo + 
				", hireDate=" + hireDate + 
				", job=" + job + 
				", edLevel=" + edLevel + 
				", sex=" + sex + 
				", birthDate=" + birthDate + 
				", salary=" + salary + 
				", bonus=" + bonus + 
				", comm=" + comm + "]";
	}

	/**
	 * @return Employee number 
	 */
	public String getEmpNo() {
		return empNo;
	}

	/**
	 * @param empNo 
	 * @param set employee number
	 */
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	/**
	 * @return Employee first name
	 */
	public String getFirstNme() {
		return firstNme;
	}

	/**
	 * @param firstNme - set Employee first name
	 */
	public void setFirstNme(String firstNme) {
		this.firstNme = firstNme;
	}

	/**
	 * @return EMployee middle initial
	 */
	public String getMidinit() {
		return midinit;
	}

	/**
	 * @param midinit set Employee middle initial
	 */
	public void setMidinit(String midinit) {
		this.midinit = midinit;
	}

	/**
	 * @return Employee Last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName set Employee Last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return Employee work department
	 */
	public String getWorkdept() {
		return workdept;
	}

	/**
	 * @param workdept - set Employee work department
	 */
	public void setWorkdept(String workdept) {
		this.workdept = workdept;
	}

	/**
	 * @return Employee phone number
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo - set Employee phone number
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return Employee hire date
	 */
	public String getHireDate() {
		return hireDate;
	}

	/**
	 * @param hireDate - set Employee hire date
	 */
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	/**
	 * @return Employee job title
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job - set Employee job title
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return Employee education level
	 */
	public int getEdLevel() {
		return edLevel;
	}

	/**
	 * @param edLevel - set Employee education level
	 */
	public void setEdLevel(int edLevel) {
		this.edLevel = edLevel;
	}

	/**
	 * @return Employee gender
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex - set Employee gender
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return Employee birth date
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate - set Employee birth date
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return Employee salary
	 */
	public long getSalary() {
		return salary;
	}

	/**
	 * @param salary - set Employee salary
	 */
	public void setSalary(long salary) {
		this.salary = salary;
	}

	/**
	 * @return Employee bonus amount
	 */
	public long getBonus() {
		return bonus;
	}

	/**
	 * @param bonus - set Employee bonus amount
	 */
	public void setBonus(long bonus) {
		this.bonus = bonus;
	}

	/**
	 * @return Employee commission amount
	 */
	public long getComm() {
		return comm;
	}

	/**
	 * @param comm - set Employee commission amount
	 */
	public void setComm(long comm) {
		this.comm = comm;
	}


}
