package com.saleon.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post_details")
public class Search {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "item_id",columnDefinition="numeric")
	 private Long itemId;
	
	@Column(name = "item_name",columnDefinition="char")
	private String itemName;
	
	@Column(name = "post_user_id",columnDefinition="numeric")
	 private Long postUserId;
	
	@Column(name = "category",columnDefinition="char")
	private String category;
	
	@Column(name = "location",columnDefinition="char")
	private String location;
	
	@Column(name = "date_posted",columnDefinition="datetime")
	private Date datePosted;
	
	@Column(name = "base_price",columnDefinition="decimal")
	private double basePrice;
	
	@Column(name = "status",columnDefinition="char")
	private String status;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long id) {
		this.itemId = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getPostUserID() {
		return postUserId;
	}

	public void setPostUserId(Long postUserId) {
		this.postUserId = postUserId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	public Date getDatePosted() {
		return datePosted;
	}
	
	public void setDatePosted(Date dateposted) {
		this.datePosted = dateposted;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
