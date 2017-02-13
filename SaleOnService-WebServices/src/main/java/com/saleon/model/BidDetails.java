package com.saleon.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "bid_details")
public class BidDetails implements Serializable {
	
	@Id
	@Column(name = "item_id",columnDefinition="numeric")
     private Long itemId ;
	
	
	@Column(name = "bid_user_id",columnDefinition="numeric")
    private Long bidUserId;
	
	
	@Column(name = "price",columnDefinition="decimal")
    private Double basePrice;
	
	@Column(name = "email_id",columnDefinition="char")
    private String emailId;
	
	@Column(name = "date_posted",columnDefinition="datetime")
    private Timestamp datePosted;
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getBidUserId() {
		return bidUserId;
	}

	public void setBidUserId(Long postUserId) {
		this.bidUserId = postUserId;
	}

	public Double getBasePrice() {
		return basePrice;
	}
	
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Timestamp getDatePosted() {
		return datePosted;
	}

	public void setDatePosted() {
		Timestamp datePosted = new Timestamp(System.currentTimeMillis());
		this.datePosted = datePosted;
	}

	
}
