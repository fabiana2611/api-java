package br.com.delivery.challenge.builder;

import java.util.Arrays;
import java.util.List;

import br.com.delivery.challenge.model.entity.Order;
import br.com.delivery.challenge.model.entity.OrderItem;

public class OrderItemBuilder {

	private OrderItem orderItem;
	
	private OrderItemBuilder() {
		
	}
	
	public OrderItem get() {
		return this.orderItem;
	}
	
	public static OrderItemBuilder createEmptyOrderItem() {
		OrderItemBuilder builder = new OrderItemBuilder();
		builder.orderItem = new OrderItem();
		return builder;
	}
	
	public static OrderItemBuilder createOneOrderItem(Order order) {
		OrderItemBuilder builder = new OrderItemBuilder();
		builder.orderItem = getOneOrderItem(1L, order, 1);
		return builder;
	}
	
	public static List<OrderItem> createListOrderItems(Order order){
		OrderItem p1 = getOneOrderItem(1L, order, 1);
		OrderItem p2 = getOneOrderItem(2L, order, 2);
		OrderItem p3 = getOneOrderItem(3L, order, 1);
		OrderItem p4 = getOneOrderItem(4L, order, 1);
		
		return Arrays.asList(p1,p2,p3,p4);
	}
	
	private static OrderItem getOneOrderItem(Long id, Order order, Integer quantity) {
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderItemId(id);
		orderItem.setOrderId(order.getOrderId());
		orderItem.setProduct(ProductBuilder.createOneProduct().get());
		orderItem.setQuantity(quantity);
		return orderItem;
	}
}
