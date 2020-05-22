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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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

	//no arg constructor
	public Emp() {
		
	}
	
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

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getFirstNme() {
		return firstNme;
	}

	public void setFirstNme(String firstNme) {
		this.firstNme = firstNme;
	}

	public String getMidinit() {
		return midinit;
	}

	public void setMidinit(String midinit) {
		this.midinit = midinit;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWorkdept() {
		return workdept;
	}

	public void setWorkdept(String workdept) {
		this.workdept = workdept;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getEdLevel() {
		return edLevel;
	}

	public void setEdLevel(int edLevel) {
		this.edLevel = edLevel;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public long getBonus() {
		return bonus;
	}

	public void setBonus(long bonus) {
		this.bonus = bonus;
	}

	public long getComm() {
		return comm;
	}

	public void setComm(long comm) {
		this.comm = comm;
	}


}
