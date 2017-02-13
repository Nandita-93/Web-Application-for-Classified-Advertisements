package com.saleon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Qualifier;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.saleon.dao.repositories.*;
import com.saleon.model.*;
import com.saleon.service.GetLocation;
import com.saleon.service.Response;

import scala.annotation.meta.setter;



@Controller

@RequestMapping("/bid")
public class BidController {

	@Autowired
	BidDetailsRepository BidDetailsRepository; 
	
	@CrossOrigin
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/bidItems")
	public ResponseEntity<Response> bidItem(@RequestBody BidDetails bid){
		bid.setDatePosted();
		BidDetailsRepository.save(bid);
		Response response = new Response();
		response.setSuccess(true);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
	}

}
