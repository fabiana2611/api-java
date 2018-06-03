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

import br.com.delivery.challenge.model.entity.Restaurant;
import br.com.delivery.challenge.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	private static final Logger logger = LogManager.getLogger(RestaurantController.class);
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/all")
	public List<Restaurant> list(){
		logger.info("List Customers...");
		return restaurantService.listRestaurants();
	}
	
	@GetMapping ("{id}")
	public Restaurant findById(@PathVariable Long id) {
		logger.info("Find Customer...");
		return restaurantService.findRestaurantById(id);
	}
	
	@PostMapping
	public Restaurant insert(@Valid @RequestBody Restaurant restaurant) {
		logger.info("Add new Customer...");
		return restaurantService.insertNewRestaurant(restaurant);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> update(@PathVariable Long id, 
			@Valid @RequestBody Restaurant restaurant) {
		
		logger.info("Update a Customer...");
		return restaurantService.updateRestaurant(id, restaurant);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		logger.info("Removing a Customer...");
		return restaurantService.removeRestaurant(id);
	}

}
