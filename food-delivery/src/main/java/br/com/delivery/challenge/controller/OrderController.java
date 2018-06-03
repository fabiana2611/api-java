package br.com.delivery.challenge.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.delivery.challenge.model.entity.Order;
import br.com.delivery.challenge.model.entity.OrderItem;
import br.com.delivery.challenge.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	private static final Logger logger = LogManager.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/all")
	public List<Order> list(){
		logger.info("List Order...");
		return orderService.listOrders();
	}
	
	@GetMapping ("{id}")
	public Order findById(@PathVariable Long id) {
		logger.info("Find Customer...");
		return orderService.findOrderById(id);
	}
	
	@GetMapping ("/{id}/itens")
	public ResponseEntity<Collection<OrderItem>> findProductsByOrderId(@PathVariable Long id) {
		logger.info("Find Customer...");
		return orderService.findItensByOrderId(id);
	}
	
//	@PostMapping
//	public Order insert(@Valid @RequestBody Order order) {
//		logger.info("Add new Customer...");
//		return orderService.insertNewOrder(order);
//	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> cancel(@PathVariable Long id) {
		logger.info("Cancel a Customer...");
		return orderService.cancelOrder(id);
	}

}
