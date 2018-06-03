package br.com.delivery.challenge.builder;

import java.util.Arrays;
import java.util.List;

import br.com.delivery.challenge.model.entity.Customer;

public class CustomerBuilder {

	private Customer customer;
	
	private CustomerBuilder() {
		
	}
	
	public Customer get() {
		return this.customer;
	}
	
	public static CustomerBuilder createEmptyCustomer() {
		CustomerBuilder builder = new CustomerBuilder();
		builder.customer = new Customer();
		return builder;
	}
	
	public static CustomerBuilder createOneCustomer() {
		CustomerBuilder builder = new CustomerBuilder();
		builder.customer = getOneCustomer(1L, "Ana", "Ana@email", "1234");
		return builder;
	}
	
	public static List<Customer> createListCustomers(){
		Customer p1 = getOneCustomer(1L, "Ana", "Chocolate@email", "1234");
		Customer p2 = getOneCustomer(2L, "Maria", "Pizza@email", "1234");
		Customer p3 = getOneCustomer(3L, "Jose", "Bread@email", "1234");
		Customer p4 = getOneCustomer(4L, "Antonio", "Orange@email", "1234");
		
		return Arrays.asList(p1,p2,p3,p4);
	}
	
	private static Customer getOneCustomer(Long id, String name, String email, String pws) {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(name);
		customer.setEmail(email);
		customer.setPassword(pws);
		return customer;
	}
}
