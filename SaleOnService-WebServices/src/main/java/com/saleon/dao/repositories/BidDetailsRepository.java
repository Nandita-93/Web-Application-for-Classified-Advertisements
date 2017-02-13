package com.saleon.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.saleon.model.BidDetails;
import com.saleon.model.PostDetails;
import com.saleon.model.UserAccount;


public interface BidDetailsRepository extends CrudRepository<BidDetails, Long> {
	
	
}