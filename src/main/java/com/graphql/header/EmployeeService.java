package com.graphql.header;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql.entity.Employee;
import com.graphql.entity.ResponseEntity;
import com.graphql.exception.DataNotFoundException;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository repository;

	public EmployeeService(final EmployeeRepository headerRepository) {
		this.repository = headerRepository ;
	}
	static Logger logger = Logger.getLogger(EmployeeService.class.getName());

	public ResponseEntity createEmployee(String firstName, String lastName, int age, String address, String requestId) {
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] createEmployee in service class starts");
		}
		repository.saveAndFlush(new Employee(firstName,lastName,age,address));
		ResponseEntity message = new ResponseEntity();
		message.setMessage("Employee details has been created successfully!!");
		return message;
	}

	public List<Employee> getEmployee(String requestId) {
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] getEmployee in service class starts");
		}
		return this.repository.findAll().stream().collect(Collectors.toList());
	}

	public Employee getEmployeeById(int employeeId, String requestId) {
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] getEmployeeById in service class starts");
		}
		return this.repository.findById(employeeId).
				orElseThrow(() -> new DataNotFoundException("Failure",404, " Employee not found for employeeId Id : "+employeeId));
	}

	public ResponseEntity deleteEmployeeById(int employeeId, String requestId) {
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] getEmployeeById in service class starts");
		}
		Optional<Employee> employee = this.repository.findById(employeeId);
		if(employee.isPresent()) {
			this.repository.deleteById(employeeId);
			ResponseEntity message = new ResponseEntity();
			message.setMessage("Employee details has been deleted successfully for employee id : "+employeeId);
			return message;
		}else {
			throw new DataNotFoundException("Failure",404, " Employee not found for employeeId Id : "+employeeId);
		}
	}

}
