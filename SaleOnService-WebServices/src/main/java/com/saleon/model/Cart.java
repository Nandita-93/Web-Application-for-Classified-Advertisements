package com.saleon.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@Column(name = "item_id",columnDefinition="numeric")
     private Long itemId ;
		
	@Column(name = "bid_user_id",columnDefinition="numeric")
    private Long bidUserId;
	
	
	@Column(name = "post_user_id",columnDefinition="numeric")
    private Long postUserId;
	
	@Column(name = "price",columnDefinition="decimal")
    private Double basePrice;
	
	@Column(name = "quantity",columnDefinition="numeric")
    private Double quantity;

	@Column(name = "status",columnDefinition="char")
    private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getBidUserId() {
		return bidUserId;
	}

	public void setBidUserId(Long bidUserId) {
		this.bidUserId = bidUserId;
	}

	public Long getPostUserId() {
		return postUserId;
	}

	public void setPostUserId(Long postUserId) {
		this.postUserId = postUserId;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	
	
}
