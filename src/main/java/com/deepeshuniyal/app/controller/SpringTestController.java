package com.deepeshuniyal.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepeshuniyal.app.repository.SpringTestDAO;
import com.deepeshuniyal.model.Customer;

@RestController
public class SpringTestController {
	
	@Autowired
	public SpringTestDAO dao;
	

	@RequestMapping("/get-cust-info1")
	public List<Customer> customerInformation1() {
		
		List<Customer> customers = dao.isData1();		 
		
		return customers;
	}
	
	@RequestMapping("/get-cust-info2")
	public List<Customer> customerInformation2() {
		
		List<Customer> customers = dao.isData2();		 
		
		return customers;
	}
}

//URL: http://localhost:8080/springboot-jndi-lookup/get-cust-info