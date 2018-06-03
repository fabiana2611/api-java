package br.com.delivery.challenge.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.delivery.challenge.model.entity.Customer;
import br.com.delivery.challenge.repository.CustomerRepository;
import br.com.delivery.challenge.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> listCustomers() {
		logger.info("Retrieval all customers...");
		return customerRepository.findAll();
	}

	@Override
	public Customer findCustomerById(Long id) {
		logger.info("Retrieval on customer by id ...");
		return customerRepository.findOne(id);
	}

	@Override
	public Customer insertNewCustomer(Customer customer) {
		logger.info("Insert a new customer ...");
		return customerRepository.save(customer);
	}

	@Override
	public ResponseEntity<Customer> updateCustomer(Long id, Customer customer) {
		
		logger.info("Update a customers ...");
		
		Customer customerDb = customerRepository.findOne(id);
		
		if (customerDb == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(customer, customerDb, "id");
		
		customerDb = customerRepository.save(customerDb);
		
		return ResponseEntity.ok(customerDb);
		
	}

	@Override
	public ResponseEntity<Void> removeCustomer(Long id) {

		Customer customer = customerRepository.findOne(id);
		
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		
		customerRepository.delete(customer);
		
		return ResponseEntity.noContent().build();
	}
}
