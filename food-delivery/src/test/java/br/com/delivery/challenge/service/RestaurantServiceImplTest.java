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

import br.com.delivery.challenge.builder.RestaurantBuilder;
import br.com.delivery.challenge.model.entity.Restaurant;
import br.com.delivery.challenge.repository.RestaurantRepository;
import br.com.delivery.challenge.service.impl.RestaurantServiceImpl;

public class RestaurantServiceImplTest {

	@InjectMocks
	private RestaurantServiceImpl service;
	
	@Mock
	private RestaurantRepository restaurantRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void listRestaurants() {

		List<Restaurant> list = RestaurantBuilder.createListRestaurants();
				
		given(restaurantRepository.findAll()).willReturn(list);
		
		// when
        List<Restaurant> listRestaurants = service.listRestaurants();
        
        // then
        assertThat(listRestaurants).isEqualTo(list);
	}
	
	@Test
	public void findRestaurantById() {

		Restaurant restaurant = RestaurantBuilder.createOneRestaurant().get();
				
		given(restaurantRepository.findOne(1L)).willReturn(restaurant);
		
		// when
        Restaurant result = service.findRestaurantById(1L);
        
        // then
        assertThat(result).isEqualTo(restaurant);
	}
	
	@Test
	public void insertNewRestaurant() {

		Restaurant Restaurant = RestaurantBuilder.createOneRestaurant().get();
		
		Restaurant RestaurantToIntert = RestaurantBuilder.createEmptyRestaurant().get();
		
		BeanUtils.copyProperties(Restaurant, RestaurantToIntert, "id");
				
		given(restaurantRepository.save(RestaurantToIntert)).willReturn(Restaurant);
		
		// when
        Restaurant result = service.insertNewRestaurant(RestaurantToIntert);
        
        // then
        assertThat(result).isEqualTo(Restaurant);
	}
	
	@Test
	public void updateRestaurant() {

		Restaurant restaurantUpdated = RestaurantBuilder.createOneRestaurant().get();
		restaurantUpdated.setRestaurantName("Description Updated");
		
		Restaurant restaurantDb = RestaurantBuilder.createOneRestaurant().get();
				
		given(restaurantRepository.findOne(restaurantUpdated.getRestaurantId())).willReturn(restaurantDb);
		
		restaurantDb.setRestaurantName("Description Updated");
		
		given(restaurantRepository.save(restaurantDb)).willReturn(restaurantDb);
		
		// when
		ResponseEntity<Restaurant> result = service.updateRestaurant(restaurantUpdated.getRestaurantId(), restaurantDb);
        
        // then
		assertTrue(result.getStatusCode().is2xxSuccessful());
        assertThat(result.getBody().getRestaurantName()).isEqualTo(restaurantUpdated.getRestaurantName());
	}
	
	@Test
	public void removeRestaurant() {

		Restaurant restaurantDb = RestaurantBuilder.createOneRestaurant().get();
				
		given(restaurantRepository.findOne(1L)).willReturn(restaurantDb);
		
		// when
		ResponseEntity<Void> result = service.removeRestaurant(1L);
        
        // then
		assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNull(result.getBody());
	}
	
}
