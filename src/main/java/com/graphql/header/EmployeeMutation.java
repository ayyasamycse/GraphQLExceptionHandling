package com.graphql.header;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.entity.ResponseEntity;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class EmployeeMutation implements GraphQLMutationResolver{
	static Logger logger = Logger.getLogger(EmployeeMutation.class.getName());
	@Autowired
	private EmployeeService headerService;
	
	public ResponseEntity createEmployee(String firstName, String lastName, int age, String address){
		String requestId = UUID.randomUUID().toString(); 
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"]  createEmployee in HeaderMutation starts");
		}
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] createEmployee in HeaderMutation ends");
		}
		ResponseEntity entity = headerService.createEmployee(firstName,lastName, age, address, requestId);
		return entity;
	}
	
	public ResponseEntity deleteEmployeeById(int employeeId){
		String requestId = UUID.randomUUID().toString(); 
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"]  deleteEmployeeById in HeaderMutation starts");
		}
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] deleteEmployeeById in HeaderMutation ends");
		}
		ResponseEntity entity = headerService.deleteEmployeeById(employeeId, requestId);
		return entity;
	}
	
}
