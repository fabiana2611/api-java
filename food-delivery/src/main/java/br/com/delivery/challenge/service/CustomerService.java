package br.com.delivery.challenge.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.delivery.challenge.model.entity.Customer;

public interface CustomerService {

	public List<Customer> listCustomers();
	
	public Customer findCustomerById(Long id);
	
	public Customer insertNewCustomer(Customer customer);
	
	public ResponseEntity<Customer> updateCustomer(Long id, Customer customer);
	
	public ResponseEntity<Void> removeCustomer(Long id);
}
