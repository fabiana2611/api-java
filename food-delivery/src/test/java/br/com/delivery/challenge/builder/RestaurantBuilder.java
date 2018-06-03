package br.com.delivery.challenge.builder;

import java.util.Arrays;
import java.util.List;

import br.com.delivery.challenge.model.entity.Restaurant;

public class RestaurantBuilder {

	private Restaurant restaurant;
	
	private RestaurantBuilder() {
		
	}
	
	public Restaurant get() {
		return this.restaurant;
	}
	
	public static RestaurantBuilder createEmptyRestaurant() {
		RestaurantBuilder builder = new RestaurantBuilder();
		builder.restaurant = new Restaurant();
		return builder;
	}
	
	public static RestaurantBuilder createOneRestaurant() {
		RestaurantBuilder builder = new RestaurantBuilder();
		builder.restaurant = getOneRestaurant(1L, "Ana", "Ana@email", "1234");
		return builder;
	}
	
	public static List<Restaurant> createListRestaurants(){
		Restaurant p1 = getOneRestaurant(1L, "Pizzeria", "Chocolate@email", "1234");
		Restaurant p2 = getOneRestaurant(2L, "Hamburgueria", "Pizza@email", "1234");
		Restaurant p3 = getOneRestaurant(3L, "Dudu Burguer", "Bread@email", "1234");
		Restaurant p4 = getOneRestaurant(4L, "Burguer King", "Orange@email", "1234");
		
		return Arrays.asList(p1,p2,p3,p4);
	}
	
	private static Restaurant getOneRestaurant(Long id, String name, String email, String phone) {
		Restaurant customer = new Restaurant();
		customer.setRestaurantId(id);
		customer.setRestaurantName(name);
		customer.setEmail(email);
		customer.setRestaurantPhone(phone);
		customer.setMenu(ProductBuilder.createListProducts());
		return customer;
	}
}
