package br.com.delivery.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;

import br.com.delivery.challenge.builder.OrderBuilder;
import br.com.delivery.challenge.model.domain.TypeStatus;
import br.com.delivery.challenge.model.entity.Order;
import br.com.delivery.challenge.model.entity.OrderItem;
import br.com.delivery.challenge.repository.OrderRepository;
import br.com.delivery.challenge.service.impl.OrderServiceImpl;

public class OrderServiceImplTest {

	@InjectMocks
	private OrderServiceImpl service;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findOrderById() {

		Order order = OrderBuilder.createOneOrder().get();
				
		given(orderRepository.findOne(1L)).willReturn(order);
		
		// when
        Order result = service.findOrderById(1L);
        
        // then
        assertThat(result).isEqualTo(order);
	}
	
	@Test
	public void findItensByOrderId() {

		Order order = OrderBuilder.createOneOrder().get();
				
		given(orderRepository.findOne(1L)).willReturn(order);
		
		// when
		ResponseEntity<Collection<OrderItem>> result = service.findItensByOrderId(1L);
        
        // then
        assertThat(result.getBody()).isEqualTo(order.getOrderItems());
	}
	
//	@Test
//	public void insertNewOrder() {
//
//		Order order = OrderBuilder.createOneOrder().get();
//		
//		Order orderToIntert = OrderBuilder.createEmptyOrder().get();
//		
//		BeanUtils.copyProperties(order, orderToIntert, "id");
//				
//		given(orderRepository.save(orderToIntert)).willReturn(order);
//		
//		// when
//        Order result = service.insertNewOrder(orderToIntert);
//        
//        // then
//        assertThat(result).isEqualTo(order);
//	}
	
	@Test
	public void cancelOrder() {

		Order order = OrderBuilder.createOneOrder().get();
		
		given(orderRepository.findOne(order.getOrderId())).willReturn(order);
		
		Order orderCancel = OrderBuilder.createEmptyOrder().get();
		
		BeanUtils.copyProperties(order, orderCancel); 
				
		orderCancel.setOrderStatus(TypeStatus.CANCELED.getCode());
		
		given(orderRepository.save(order)).willReturn(orderCancel);
		
		// when
		ResponseEntity<Order> result = service.cancelOrder(order.getOrderId());
		
		  // then
        assertThat(result.getBody().getOrderStatus()).isEqualTo(TypeStatus.CANCELED.getCode());
	}
	
}
