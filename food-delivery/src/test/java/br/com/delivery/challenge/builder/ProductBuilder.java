package br.com.delivery.challenge.builder;

import java.util.Arrays;
import java.util.List;

import br.com.delivery.challenge.model.entity.Product;

public class ProductBuilder {

	private Product product;
	
	private ProductBuilder() {
		
	}
	
	public Product get() {
		return this.product;
	}
	
	public static ProductBuilder createEmptyProduct() {
		ProductBuilder builder = new ProductBuilder();
		builder.product = new Product();
		return builder;
	}
	
	public static ProductBuilder createOneProduct() {
		ProductBuilder builder = new ProductBuilder();
		builder.product = getOneProduct(1L, "Product description: 1", "Chocolate", new Double(05.89));
		return builder;
	}
	
	public static List<Product> createListProducts(){
		Product p1 = getOneProduct(1L, "Prod Desc 1", "Chocolate", 10.25d);
		Product p2 = getOneProduct(2L, "Prod Desc 2", "Pizza", 28.44d);
		Product p3 = getOneProduct(3L, "Prod Desc 3", "Bread", 0.89d);
		Product p4 = getOneProduct(4L, "Prod Desc 4", "Orange", 6.89d);
		
		return Arrays.asList(p1,p2,p3,p4);
	}
	
	private static Product getOneProduct(Long id, String description, String name, Double price) {
		Product product = new Product();
		product.setProductId(id);
		product.setProductDescription(description);
		product.setProductName(name);
		product.setProductPrice(price);
		return product;
	}
}
