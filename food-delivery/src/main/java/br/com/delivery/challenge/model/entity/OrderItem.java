package br.com.delivery.challenge.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="order_items")
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="order_item_id")
    private Long orderItemId;
    
	@Column(name = "order_item_order")
    private Long orderId;
    
	@ManyToOne 
	@JoinColumn(name = "order_item_product") 
    private Product product;
    
    @Column(name="order_item_quantity")
    private Integer quantity;
    
    

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

    public Integer getQuantity() {
        return quantity;
    }

    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
