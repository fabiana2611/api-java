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

import br.com.delivery.challenge.builder.ProductBuilder;
import br.com.delivery.challenge.model.entity.Product;
import br.com.delivery.challenge.repository.ProductRepository;
import br.com.delivery.challenge.service.impl.ProductServiceImpl;

public class ProductServiceImplTest {

	@InjectMocks
	private ProductServiceImpl service;
	
	@Mock
	private ProductRepository productRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void listProducts() {

		List<Product> list = ProductBuilder.createListProducts();
				
		given(productRepository.findAll()).willReturn(list);
		
		// when
        List<Product> listProducts = service.listProducts();
        
        // then
        assertThat(listProducts).isEqualTo(list);
	}
	
	@Test
	public void findProductById() {

		Product product = ProductBuilder.createOneProduct().get();
				
		given(productRepository.findOne(1L)).willReturn(product);
		
		// when
        Product result = service.findProductById(1L);
        
        // then
        assertThat(result).isEqualTo(product);
	}
	
	@Test
	public void insertNewProduct() {

		Product product = ProductBuilder.createOneProduct().get();
		
		Product productToIntert = ProductBuilder.createEmptyProduct().get();
		
		BeanUtils.copyProperties(product, productToIntert, "id");
				
		given(productRepository.save(productToIntert)).willReturn(product);
		
		// when
        Product result = service.insertNewProduct(productToIntert);
        
        // then
        assertThat(result).isEqualTo(product);
	}
	
	@Test
	public void updateProduct() {

		Product productUpdated = ProductBuilder.createOneProduct().get();
		productUpdated.setProductDescription("Description Updated");
		
		Product productDb = ProductBuilder.createOneProduct().get();
				
		given(productRepository.findOne(productUpdated.getProductId())).willReturn(productDb);
		
		productDb.setProductDescription("Description Updated");
		
		given(productRepository.save(productDb)).willReturn(productDb);
		
		// when
		ResponseEntity<Product> result = service.updateProduct(productUpdated.getProductId(), productDb);
        
        // then
		assertTrue(result.getStatusCode().is2xxSuccessful());
        assertThat(result.getBody().getProductDescription()).isEqualTo(productUpdated.getProductDescription());
	}
	
	@Test
	public void removeProduct() {

		Product productDb = ProductBuilder.createOneProduct().get();
				
		given(productRepository.findOne(1L)).willReturn(productDb);
		
		// when
		ResponseEntity<Void> result = service.removeProduct(1L);
        
        // then
		assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNull(result.getBody());
	}
	
}
