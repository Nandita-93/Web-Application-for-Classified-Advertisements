package com.saleon.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "post_details")
public class PostDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "item_id",columnDefinition="numeric")
    private Long itemId ;
	
	@OneToMany
	@JoinColumn(name = "item_id")
	private List<BidDetails> bid;
	
 	@Column(name = "item_name",columnDefinition="char")
    private String itemName ;
	
	@Column(name = "post_user_id",columnDefinition="numeric")
    private Long postUserId;
	
	@Column(name = "category",columnDefinition="char")
    private String category;
	
	@Column(name = "location",columnDefinition="char")
    private String location;
	
	@Column(name = "date_posted",columnDefinition="datetime")
    private Timestamp datePosted;

	@Column(name = "base_price",columnDefinition="decimal")
    private Double basePrice;
	
	@Column(name = "status",columnDefinition="char")
    private String status;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getPostUserId() {
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

	public Timestamp getDatePosted() {
		return datePosted;
	}

	public void setDatePosted() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.datePosted = timestamp;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BidDetails> getBid() {
		return bid;
	}

	public void setBid(List<BidDetails> bid) {
		this.bid = bid;
	}

	public void setDatePosted(Timestamp datePosted) {
		this.datePosted = datePosted;
	}	

}




