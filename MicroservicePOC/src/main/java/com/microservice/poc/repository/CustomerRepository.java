package com.microservice.poc.repository;

import com.microservice.poc.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for customer CRUD operations.
 *
 * @author rohitnathani
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
