package com.microservice.poc.controller;

import com.microservice.poc.exception.ResourceNotFoundException;
import com.microservice.poc.model.Customer;
import com.microservice.poc.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for mapping HTTP requests.
 *
 * @author rohitnathani
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    private static final String CUSTOMERS = "/customers";

    @Autowired
    private CustomerRepository customerRepository;

    // Get All Customers
    @GetMapping(CUSTOMERS)
    public List<Customer> getAllCustomers() {
        LOG.info("###### Inside getAllCustomers method ######");
        return customerRepository.findAll();
    }

    // Create a new Customer
    @PostMapping(CUSTOMERS)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        LOG.info("###### Inside POST method ######");
        return customerRepository.save(customer);
    }

    // Get a Single Customer by passing the Id
    @GetMapping(CUSTOMERS + "/{id}")
    public Customer getCustomerById(@PathVariable(value = "id") Long custId) {
        LOG.info("###### Inside getCustomerById method ######");
        return customerRepository.findById(custId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", custId));
    }

    // Update a Customer
    @PutMapping(CUSTOMERS + "/{id}")
    public Customer updateNote(@PathVariable(value = "id") Long custId,
                               @Valid @RequestBody Customer customerDetails) {
        LOG.info("##### Inside UPDATE method #####");

        Customer customer = customerRepository.findById(custId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", custId));

        customer.setAddress(customerDetails.getAddress());
        customer.setName(customerDetails.getName());

        Customer updatedCustomer = customerRepository.save(customer);
        return updatedCustomer;
    }

    // Delete a Customer
    @DeleteMapping(CUSTOMERS + "/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long custId) {
        LOG.info("##### Inside DELETE method #####");
        Customer customer = customerRepository.findById(custId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", custId));

        customerRepository.delete(customer);

        return ResponseEntity.ok().build();
    }
}
