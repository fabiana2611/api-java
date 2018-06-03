package br.com.delivery.challenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.delivery.challenge.model.entity.Customer;
import br.com.delivery.challenge.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/all")
	public List<Customer> list(){
		logger.info("List Customers...");
		return customerService.listCustomers();
	}
	
	@GetMapping ("{id}")
	public Customer findById(@PathVariable Long id) {
		logger.info("Find Customer...");
		return customerService.findCustomerById(id);
	}
	
	@PostMapping
	public Customer insert(@Valid @RequestBody Customer customer) {
		logger.info("Add new Customer...");
		return customerService.insertNewCustomer(customer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable Long id, 
			@Valid @RequestBody Customer customer) {
		
		logger.info("Update a Customer...");
		return customerService.updateCustomer(id, customer);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		logger.info("Removing a Customer...");
		return customerService.removeCustomer(id);
	}

}
