package com.saleon.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.saleon.model.PostDetails;

public interface PostDetailsRepository extends CrudRepository<PostDetails, Long> {
    
	@Query("from PostDetails where postUserId = :postUserId and status = 'ACTIVE' ")
    public List<PostDetails> getDetails(@Param("postUserId") Long  postUserId);
    
	@Query("from PostDetails where itemId = :itemId")
    public PostDetails getItem(@Param("itemId") Long  itemId);

}
