package com.deepeshuniyal.app.test2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deepeshuniyal.app.test2.domain.Customer2;

@Repository
public interface CustomerRepository2 extends JpaRepository<Customer2, Long> {

	 Customer2 findById(Integer id);
}
