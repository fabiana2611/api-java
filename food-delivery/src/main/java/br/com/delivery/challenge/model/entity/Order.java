package br.com.delivery.challenge.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.delivery.challenge.model.domain.TypeStatus;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "order_user")
	private Long orderUser;

	@Column(name = "order_address")
	private String orderAddress;

	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "order_status")
	private Integer orderStatus;

	@OneToMany(mappedBy = "orderId")
	private List<OrderItem> orderItems;
	

	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(Long orderUser) {
		this.orderUser = orderUser;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusDescription() {
		return TypeStatus.getByCode(orderStatus).getDescription();
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	

}
