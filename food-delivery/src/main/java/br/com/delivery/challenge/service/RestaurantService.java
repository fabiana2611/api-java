package br.com.delivery.challenge.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.delivery.challenge.model.entity.Restaurant;

public interface RestaurantService {

	public List<Restaurant> listRestaurants();
	
	public Restaurant findRestaurantById(Long id);
	
	public Restaurant insertNewRestaurant(Restaurant rOestaurant);
	
	public ResponseEntity<Restaurant> updateRestaurant(Long id, Restaurant restaurant);
	
	public ResponseEntity<Void> removeRestaurant(Long id);
}
