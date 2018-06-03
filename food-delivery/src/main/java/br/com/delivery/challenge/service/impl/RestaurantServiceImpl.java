package br.com.delivery.challenge.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.delivery.challenge.model.entity.Restaurant;
import br.com.delivery.challenge.repository.RestaurantRepository;
import br.com.delivery.challenge.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	private static final Logger logger = LogManager.getLogger(RestaurantServiceImpl.class);
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public List<Restaurant> listRestaurants() {
		logger.info("Retrieval all products...");
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant findRestaurantById(Long id) {
		logger.info("Retrieval on product by id ...");
		return restaurantRepository.findOne(id);
	}

	@Override
	public Restaurant insertNewRestaurant(Restaurant restaurant) {
		logger.info("Insert a new product ...");
		return restaurantRepository.save(restaurant);
	}

	@Override
	public ResponseEntity<Restaurant> updateRestaurant(Long id, Restaurant restaurant) {
		
		logger.info("Update a product ...");
		
		Restaurant restaurantDb = restaurantRepository.findOne(id);
		
		if (restaurantDb == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(restaurant, restaurantDb, "id");
		
		restaurantDb = restaurantRepository.save(restaurantDb);
		
		return ResponseEntity.ok(restaurantDb);
		
	}

	@Override
	public ResponseEntity<Void> removeRestaurant(Long id) {

		Restaurant restaurant = restaurantRepository.findOne(id);
		
		if (restaurant == null) {
			return ResponseEntity.notFound().build();
		}
		
		restaurantRepository.delete(restaurant);
		
		return ResponseEntity.noContent().build();
	}
}
