package com.saleon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_accounts")
public class UserAccount {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id",columnDefinition="numeric")
     private Long id;
	
	@Column(name = "first_name",columnDefinition="char")
    private String firstName;
	
	@Column(name = "last_name",columnDefinition="char")
    private String lastName;
	
	@Column(name = "user_name",columnDefinition="char")
    private String userName;
	
	@Column(name = "email_id",columnDefinition="char")
    private String emailId;
	
	@Column(name = "password",columnDefinition="char")
    private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
	
}
