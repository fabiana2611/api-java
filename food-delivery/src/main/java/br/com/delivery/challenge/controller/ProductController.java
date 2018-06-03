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

import br.com.delivery.challenge.model.entity.Product;
import br.com.delivery.challenge.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private static final Logger logger = LogManager.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	public List<Product> list(){
		logger.info("List products...");
		return productService.listProducts();
	}
	
	@GetMapping ("{id}")
	public Product findById(@PathVariable Long id) {
		logger.info("Find product...");
		return productService.findProductById(id);
	}
	
	@PostMapping
	public Product insert(@Valid @RequestBody Product product) {
		logger.info("Add new product...");
		return productService.insertNewProduct(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, 
			@Valid @RequestBody Product product) {
		
		logger.info("Update a product...");
		return productService.updateProduct(id, product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		logger.info("Removing a product...");
		return productService.removeProduct(id);
	}

}
