package com.saleon.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.saleon.model.UserAccount;


public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

	@Query("from UserAccount where userName = :userName and password = :password")
     public UserAccount getDetails(@Param("userName") String userName, @Param("password")String password);

	@Query("from UserAccount where userName = :userName")
     public UserAccount getUsername(@Param("userName") String userName);
	
	@Query("from UserAccount where id =:id")
    public UserAccount getById(@Param("id") Long id);
	
}
