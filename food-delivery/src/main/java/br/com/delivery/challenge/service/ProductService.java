package br.com.delivery.challenge.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.delivery.challenge.model.entity.Product;

public interface ProductService {

	public List<Product> listProducts();
	
	public Product findProductById(Long id);
	
	public Product insertNewProduct(Product product);
	
	public ResponseEntity<Product> updateProduct(Long id, Product product);
	
	public ResponseEntity<Void> removeProduct(Long id);
}
