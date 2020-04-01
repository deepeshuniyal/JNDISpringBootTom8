package com.deepeshuniyal.app.test1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deepeshuniyal.app.test1.domain.Customer1;

@Repository
public interface CustomerRepository1 extends JpaRepository<Customer1, Long> {

	 Customer1 findById(Integer id);
}
