package br.com.delivery.challenge.service.impl;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.delivery.challenge.model.domain.TypeStatus;
import br.com.delivery.challenge.model.entity.Order;
import br.com.delivery.challenge.model.entity.OrderItem;
import br.com.delivery.challenge.repository.OrderRepository;
import br.com.delivery.challenge.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Order> listOrders() {
		logger.info("Retrieval all orders...");
		return orderRepository.findAll();
	}
	
	@Override
	public Order findOrderById(Long id) {
		logger.info("Finde one order by id...");
		return orderRepository.findOne(id);
	}

//	@Override
//	public Order insertNewOrder(Order order) {
//		logger.info("Insert a new order ...");
//		return orderRepository.save(order);
//	}

	@Override
	public ResponseEntity<Order> cancelOrder(Long id) {

		logger.info("Cancel the order by id ...");
		
		Order orderDb = orderRepository.findOne(id);
		
		if (orderDb == null) {
			return ResponseEntity.notFound().build();
		}
		
		orderDb.setOrderStatus(TypeStatus.CANCELED.getCode());
		
		orderDb = orderRepository.save(orderDb);
		
		return ResponseEntity.ok(orderDb);
	}

	@Override
	public ResponseEntity<Collection<OrderItem>> findItensByOrderId(Long id) {
		
		Order order = orderRepository .findOne(id);

		if (order != null) {
			return new ResponseEntity<>(order.getOrderItems(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
