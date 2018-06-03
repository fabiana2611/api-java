package br.com.delivery.challenge.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.delivery.challenge.model.entity.Product;
import br.com.delivery.challenge.repository.ProductRepository;
import br.com.delivery.challenge.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> listProducts() {
		logger.info("Retrieval all products...");
		return productRepository.findAll();
	}

	@Override
	public Product findProductById(Long id) {
		logger.info("Retrieval on product by id ...");
		return productRepository.findOne(id);
	}

	@Override
	public Product insertNewProduct(Product product) {
		logger.info("Insert a new product ...");
		return productRepository.save(product);
	}

	@Override
	public ResponseEntity<Product> updateProduct(Long id, Product product) {
		
		logger.info("Update a product ...");
		
		Product productDb = productRepository.findOne(id);
		
		if (productDb == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(product, productDb, "id");
		
		productDb = productRepository.save(productDb);
		
		return ResponseEntity.ok(productDb);
		
	}

	@Override
	public ResponseEntity<Void> removeProduct(Long id) {

		Product product = productRepository.findOne(id);
		
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		
		productRepository.delete(product);
		
		return ResponseEntity.noContent().build();
	}
}
