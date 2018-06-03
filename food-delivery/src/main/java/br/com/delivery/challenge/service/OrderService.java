package br.com.delivery.challenge.service;

import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.delivery.challenge.model.entity.Order;
import br.com.delivery.challenge.model.entity.OrderItem;

public interface OrderService {

	public List<Order> listOrders();
	
	public Order findOrderById(Long id);
	
//	public Order insertNewOrder(Order order);
	
	public ResponseEntity<Order> cancelOrder(Long id);
	
	public ResponseEntity<Collection<OrderItem>> findItensByOrderId(Long id);
}
