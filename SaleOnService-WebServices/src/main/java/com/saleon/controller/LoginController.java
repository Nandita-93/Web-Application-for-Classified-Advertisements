package com.saleon.controller;

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


@CrossOrigin
@Controller
@RequestMapping("/ws")
public class LoginController {
	
	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Autowired
	UserHistoryRepository userHistoryRepository;

	@RequestMapping(method=RequestMethod.POST,value="/login", consumes = "application/json")
	public ResponseEntity<UserAccountHist> loginCheck(@RequestBody LoginDetail login)
    {
		UserAccount output = null;
		UserAccountHist userDetails = new UserAccountHist();
		
		
		if(login.getUserName() !=null && login.getPassword() != null)
		{
			 output = userAccountRepository.getDetails(login.getUserName(),login.getPassword());
			 
		}
		
		if(output != null)	
		{	
			 UserAccountHist userLastLoginDetails = userHistoryRepository.getLastLoginDetails(login.getUserName());
			 userDetails.setId(output.getId());
			 userDetails.setUserName(output.getUserName());
			 userDetails.setFirstName(output.getFirstName());
			 userDetails.setLastName(output.getLastName());
			 userDetails.setPassword(output.getPassword());
			 userDetails.setEmailId(output.getEmailId());
			 GetLocation loc = new GetLocation();
			 userDetails.setLoginTime();
			 userDetails.setLogoutTime();
			 userDetails.setLoginLocation(loc.getLocation());
			 userHistoryRepository.save(userDetails);
			 
			 if(userLastLoginDetails == null){
				 userLastLoginDetails = userHistoryRepository.getLastLoginDetails(login.getUserName());
			 }
			 
			 return  new ResponseEntity<UserAccountHist>(userLastLoginDetails,HttpStatus.OK);
		}

			return new ResponseEntity<UserAccountHist>(userDetails,HttpStatus.OK);
	
    }

	@RequestMapping(method=RequestMethod.PUT,value="/register")
    public ResponseEntity<Response> register(@RequestBody UserAccount Login)
    {
		
		UserAccount existingUser = null;
		if(Login.getUserName() !=null && Login.getPassword() != null)
		{
			existingUser = userAccountRepository.getUsername(Login.getUserName());
		}
		Response response = new Response();
		
		if(existingUser != null)	
		{
			response.setSuccess(false);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		else
		{
			response.setSuccess(true);
			userAccountRepository.save(Login);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		
    }
	
	@RequestMapping(method=RequestMethod.PUT,value="/profile")
    public ResponseEntity<Response> profile(@RequestBody UserAccount Profile)
    {
		    Response response = new Response();
			response.setSuccess(true);
			UserAccount userDetails = new UserAccount();
			
			userDetails = userAccountRepository.findOne(Profile.getId());
			userDetails.setUserName(Profile.getUserName());
			userDetails.setFirstName(Profile.getFirstName());
			userDetails.setLastName(Profile.getLastName());
			userDetails.setPassword(Profile.getPassword());
			userDetails.setEmailId(Profile.getEmailId());
			userAccountRepository.save(userDetails);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
	


	@RequestMapping(method=RequestMethod.POST,value="/logout", consumes = "application/json")
    public @ResponseBody ResponseEntity<Response> logout(@RequestBody LoginDetail login) throws JSONException
    {
	
		UserAccountHist userDetails = userHistoryRepository.getLastLoginDetails(login.getUserName());
		userDetails.setLogoutTime();
		userHistoryRepository.save(userDetails);
		Response response = new Response();
		response.setSuccess(true);
		return  new ResponseEntity<Response>(response,HttpStatus.OK);
		
    }

}
