package com.saleon.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saleon.model.UserAccount;
import com.saleon.model.UserAccountHist;

@Repository
public interface UserHistoryRepository extends CrudRepository<UserAccountHist, Long>{
	
	@Query("from UserAccountHist where userName = :userName")
     public UserAccountHist getLastLoginDetails(@Param("userName") String userName);
	
}
