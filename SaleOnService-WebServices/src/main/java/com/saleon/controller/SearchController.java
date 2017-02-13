package com.saleon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Qualifier;
import javax.persistence.Cacheable;

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
import org.springframework.cache.annotation.*;
import com.saleon.dao.repositories.*;
import com.saleon.model.*;
import com.saleon.service.GetLocation;
import com.saleon.service.Response;

import scala.annotation.meta.setter;



@Controller

@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	SearchRepository SearchRepository;

	
	@CrossOrigin
	@org.springframework.cache.annotation.Cacheable("defaultCache")
	@RequestMapping(method=RequestMethod.GET,value="/retrieve")
	public ResponseEntity<List> searchPosts(@RequestParam("id")Long id){
		List<Search> searchDetails = SearchRepository.getDetails(id);
		return new ResponseEntity<List>(searchDetails, HttpStatus.OK);
		
	}
	

}
