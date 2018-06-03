package br.com.delivery.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;

import br.com.delivery.challenge.builder.CustomerBuilder;
import br.com.delivery.challenge.model.entity.Customer;
import br.com.delivery.challenge.repository.CustomerRepository;
import br.com.delivery.challenge.service.impl.CustomerServiceImpl;

public class CustomerServiceImplTest {

	@InjectMocks
	private CustomerServiceImpl service;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void listCustomers() {

		List<Customer> list = CustomerBuilder.createListCustomers();
				
		given(customerRepository.findAll()).willReturn(list);
		
		// when
        List<Customer> listCustomers = service.listCustomers();
        
        // then
        assertThat(listCustomers).isEqualTo(list);
	}
	
	@Test
	public void findCustomerById() {

		Customer Customer = CustomerBuilder.createOneCustomer().get();
				
		given(customerRepository.findOne(1L)).willReturn(Customer);
		
		// when
        Customer result = service.findCustomerById(1L);
        
        // then
        assertThat(result).isEqualTo(Customer);
	}
	
	@Test
	public void insertNewCustomer() {

		Customer Customer = CustomerBuilder.createOneCustomer().get();
		
		Customer CustomerToIntert = CustomerBuilder.createEmptyCustomer().get();
		
		BeanUtils.copyProperties(Customer, CustomerToIntert, "id");
				
		given(customerRepository.save(CustomerToIntert)).willReturn(Customer);
		
		// when
        Customer result = service.insertNewCustomer(CustomerToIntert);
        
        // then
        assertThat(result).isEqualTo(Customer);
	}
	
	@Test
	public void updateCustomer() {

		Customer customerUpdated = CustomerBuilder.createOneCustomer().get();
		customerUpdated.setName("Description Updated");
		
		Customer CustomerDb = CustomerBuilder.createOneCustomer().get();
				
		given(customerRepository.findOne(customerUpdated.getId())).willReturn(CustomerDb);
		
		CustomerDb.setName("Description Updated");
		
		given(customerRepository.save(CustomerDb)).willReturn(CustomerDb);
		
		// when
		ResponseEntity<Customer> result = service.updateCustomer(customerUpdated.getId(), CustomerDb);
        
        // then
		assertTrue(result.getStatusCode().is2xxSuccessful());
        assertThat(result.getBody().getName()).isEqualTo(customerUpdated.getName());
	}
	
	@Test
	public void removeCustomer() {

		Customer CustomerDb = CustomerBuilder.createOneCustomer().get();
				
		given(customerRepository.findOne(1L)).willReturn(CustomerDb);
		
		// when
		ResponseEntity<Void> result = service.removeCustomer(1L);
        
        // then
		assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNull(result.getBody());
	}
	
}
