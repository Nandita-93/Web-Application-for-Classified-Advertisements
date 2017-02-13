package com.saleon.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Qualifier;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.saleon.dao.repositories.*;
import com.saleon.model.*;
import com.saleon.service.GetLocation;
import com.saleon.service.ItemStatus;
import com.saleon.service.Response;

import scala.annotation.meta.setter;


@CrossOrigin
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	PostDetailsRepository postDetailsRepository;
	
	@Autowired
	BidDetailsRepository bidDetailsRepository;
	
	@RequestMapping(method=RequestMethod.PUT,value="/post", consumes = "application/json")
	public ResponseEntity<Response> postItem(@RequestBody PostDetails itemDetails)
    {
		itemDetails.setDatePosted();
		itemDetails.setStatus(ItemStatus.ACTIVE.toString());
		postDetailsRepository.save(itemDetails);
		Response response = new Response();
		response.setSuccess(true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    }
	

	@RequestMapping(method=RequestMethod.GET,value = "/bids")
	public ResponseEntity<List<BidDetails>> getBids(@RequestParam("id")Long id)
    {
		List<PostDetails> items = postDetailsRepository.getDetails(id);
		List<BidDetails> bids = new ArrayList<BidDetails>();
		
		for (PostDetails x : items) {
			bids.addAll(x.getBid());			
		}

		return new ResponseEntity<List<BidDetails>>(bids,HttpStatus.OK);
    }
	
}
