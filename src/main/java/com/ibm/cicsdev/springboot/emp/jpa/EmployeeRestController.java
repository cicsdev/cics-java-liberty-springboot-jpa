package com.ibm.cicsdev.springboot.emp.jpa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeRestController {
	/*    
	 *  REST controller used to direct incoming requests to the correct business service.
	 *  
	 *  In a real world application some of these functions would most likely be done by a POST
	 *    request. For simplicity all requests to this sample application are done with a GET request
	 *    
	 */

	@Autowired  
	private EmployeeService employeeService;

	// Simple endpoint - returns date and time - simple test of the application
	@RequestMapping("/") 
	@ResponseBody
	public String Index()
	{    
		Date myDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss.SSSSSS");
		String myDateString = sdf.format(myDate);
		return "Hello from employee service controller (EMP JPA Application). Date/Time: " + myDateString;
	}
	
	/*
	 *    example url http://<server>:<port>/allRows
	 */
	@RequestMapping(value={"/allRows","/allRows/"})
	public List<Emp> getAllRows() throws NamingException {
		System.out.println("getAllRows :EmployeeRESTController");
		return employeeService.selectAll();
	}
	
//	/*
//	 *    example url http://<server>:<port>/allRows2
//	 */
////	@RequestMapping("/allRows2/")
//	@RequestMapping(value={"/allRows2","/allRows2/"})
//	public List<Employee> getAllRows2() throws NamingException {
//		return employeeService.selectAllUsingBeanDataSource();
//	}
	/*
	 *    example url http://<server>:<port>/oneEmployee/000100
	 */
	@RequestMapping("/oneEmployee/{empno}")
	public Optional<Emp> oneEmployee(@PathVariable String empno) {
		return employeeService.selectWhereEmpno(empno);
	}
	
	/*
	 *    example url http://<server>:<port>/addEmployee/Tony/Fitzgerald
	 */
	@RequestMapping("/addEmployee/{firstName}/{lastName}")
	@ResponseBody
	public String addEmp(@PathVariable String firstName , @PathVariable String lastName) {
		String result = employeeService.addEmployee(firstName,lastName);
		return result;
	}
	
	/*
	 *    example url http://<server>:<port>/deleteEmployee/368620
	 */
	@RequestMapping("/deleteEmployee/{empNo}")
	@ResponseBody
	public String delEmployee(@PathVariable String empNo) {
		String result = employeeService.deleteEmployee(empNo);
		return result;
	}
	
	/*
	 *  example url http://<server>:<port>/updateEmployee/368620/33333
	 */
	@RequestMapping("/updateEmployee/{empNo}/{newSalary}")
	@ResponseBody
	public String updateEmp(@PathVariable String empNo, @PathVariable long newSalary) {
		//Emp empToUpd = employeeService.selectWhereEmpno1(empNo);
		
		String result = employeeService.updateEmployee(empNo, newSalary);
		return result;
	}
	
		
}
