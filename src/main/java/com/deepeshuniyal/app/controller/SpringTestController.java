package com.deepeshuniyal.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepeshuniyal.app.repository.SpringTestDAO;
import com.deepeshuniyal.app.test1.domain.Customer1;
import com.deepeshuniyal.app.test2.domain.Customer2;



@RestController
public class SpringTestController {
	
	@Autowired
	public SpringTestDAO dao;
	

	@GetMapping("/get-cust-info-test1")
	public List<Customer1> customerInformation1() {
		
		List<Customer1> customers = dao.isData1();		 
		
		return customers;
	}
	
	@RequestMapping("/get-cust-info-test2")
	public List<Customer2> customerInformation2() {
		
		List<Customer2> customers = dao.isData2();		 
		
		return customers;
	}
}

//URL: http://localhost:8080/springboot-jndi-lookup/get-cust-info