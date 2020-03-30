package com.deepeshuniyal.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.deepeshuniyal.model.Customer;

@Repository
public class SpringTestDAO {
	
	@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate jdbcTemplate1;
	
	@Autowired
	@Qualifier("jdbcTemplate2")
	private JdbcTemplate jdbcTemplate2;
	
	private static final String SQL = "select * from customers";
	
	public List<Customer> isData1() {
		
		List<Customer> customers = new ArrayList<Customer>();

		List<Map<String, Object>> rows = jdbcTemplate1.queryForList(SQL);
		
		for (Map<String, Object> row : rows) {
			Customer customer = new Customer();
			
			customer.setCustNo((int)row.get("Cust_id"));
			customer.setCustName((String)row.get("Cust_name"));
			customer.setCountry((String)row.get("Country"));
			
			customers.add(customer);
		}

		return customers;
		
	}
	
public List<Customer> isData2() {
		
		List<Customer> customers = new ArrayList<Customer>();

		List<Map<String, Object>> rows = jdbcTemplate2.queryForList(SQL);
		
		for (Map<String, Object> row : rows) {
			Customer customer = new Customer();
			
			customer.setCustNo((int)row.get("Cust_id"));
			customer.setCustName((String)row.get("Cust_name"));
			customer.setCountry((String)row.get("Country"));
			
			customers.add(customer);
		}

		return customers;
		
	}
}