package br.com.delivery.challenge.builder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.delivery.challenge.model.domain.TypeStatus;
import br.com.delivery.challenge.model.entity.Order;
import br.com.delivery.challenge.utils.DateUtils;

public class OrderBuilder {

	private Order order;
	
	private static final LocalDate NOW = LocalDate.now();
	private static final LocalDate YESTERDAY = LocalDate.of(NOW.getYear(), NOW.getMonth(), NOW.getDayOfMonth() -1);
	
	private OrderBuilder() {
		
	}
	
	public Order get() {
		return this.order;
	}
	
	public static OrderBuilder createEmptyOrder() {
		OrderBuilder builder = new OrderBuilder();
		builder.order = new Order();
		return builder;
	}
	
	public static OrderBuilder createOneOrder() {
		OrderBuilder builder = new OrderBuilder();
		builder.order = getOneOrder(1L, 1L, TypeStatus.DELIVERED, "Address 1", DateUtils.asDate(NOW));
		return builder;
	}
	
	public static List<Order> createListOrders(){
		Order p1 = getOneOrder(1L, 1L, TypeStatus.DELIVERED, "Address 1", DateUtils.asDate(NOW));
		Order p2 = getOneOrder(2L, 2L, TypeStatus.CANCELED, "Address 2", DateUtils.asDate(YESTERDAY));
		Order p3 = getOneOrder(3L, 2L, TypeStatus.INBOUND, "Address 2", DateUtils.asDate(NOW));
		Order p4 = getOneOrder(4L, 3L, TypeStatus.REJECTED, "Address 4", DateUtils.asDate(NOW));
		
		return Arrays.asList(p1,p2,p3,p4);
	}
	
	private static Order getOneOrder(Long id, Long userId, TypeStatus status, String address, Date date) {
		Order order = new Order();
		order.setOrderId(id);
		order.setOrderUser(userId);
		order.setOrderStatus(status.getCode());
		order.setOrderAddress(address);
		order.setOrderDate(date);
		order.setOrderItems(OrderItemBuilder.createListOrderItems(order));
		return order;
	}

}
