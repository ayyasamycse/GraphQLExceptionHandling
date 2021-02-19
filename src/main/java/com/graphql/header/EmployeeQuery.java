package com.graphql.header;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.entity.Employee;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class EmployeeQuery implements GraphQLQueryResolver {

	static Logger logger = Logger.getLogger(EmployeeMutation.class.getName());
	@Autowired
	private EmployeeService headerService;
	
	public List<Employee> getEmployee(){
		String requestId = UUID.randomUUID().toString(); 
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"]  getEmployee in HeaderQuery starts");
		}
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] getEmployee in HeaderQuery ends");
		}
		List<Employee> person = headerService.getEmployee(requestId);
		return person;
		
	}
	
	public Employee getEmployeeById(int employeeId){
		String requestId = UUID.randomUUID().toString(); 
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"]  getEmployeeById in HeaderQuery starts");
		}
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] getEmployeeById in HeaderQuery ends");
		}
		Employee employee = headerService.getEmployeeById(employeeId, requestId);
		return employee;
		
	}
	
}
