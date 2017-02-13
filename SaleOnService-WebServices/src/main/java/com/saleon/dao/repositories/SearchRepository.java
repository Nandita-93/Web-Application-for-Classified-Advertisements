package com.saleon.dao.repositories;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saleon.model.Search;
import com.saleon.model.UserAccount;

public interface SearchRepository extends CrudRepository<Search, Long> {

	@Query("from Search where status != 'NOT_AVAILABLE' and postUserId != :postUserId  order by datePosted")
     public List<Search> getDetails(@Param("postUserId") Long postUserId);	

}
