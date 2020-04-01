package com.deepeshuniyal.app.test1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer1 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String custName;
	private String country;
	
	public Customer1() {		
	}
	
	public Customer1(int id, String custName, String country) {
		this.id = id;
		this.custName = custName;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}	
}
