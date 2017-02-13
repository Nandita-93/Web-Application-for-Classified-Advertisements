package com.saleon.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.saleon.dao.repositories.CartRepository;
import com.saleon.dao.repositories.PostDetailsRepository;
import com.saleon.dao.repositories.UserAccountRepository;
import com.saleon.model.Cart;
import com.saleon.model.PostDetails;
import com.saleon.model.UserAccount;
import com.saleon.service.ItemStatus;
import com.saleon.service.Mail;
import com.saleon.service.Response;


@CrossOrigin
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	PostDetailsRepository postDetailsRepository;
	
	@Autowired
	UserAccountRepository userAcccountRepository;
	
	@RequestMapping(method=RequestMethod.PUT,value="/addtocart", consumes = "application/json")
	public ResponseEntity<Response> addToCart(@RequestBody Cart cartItem)
    {
		cartItem.setStatus(ItemStatus.IN_CART.toString());
		cartRepository.save(cartItem);
		PostDetails item = postDetailsRepository.getItem(cartItem.getItemId());
		item.setStatus(ItemStatus.IN_CART.toString());
		postDetailsRepository.save(item);
		Response response = new Response();
		response.setSuccess(true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    }
	
	@RequestMapping(method=RequestMethod.POST,value="/checkout", consumes = "application/json")
	public ResponseEntity<Response> checkOut(@RequestBody Cart cartItem)
    {
		Cart checkedOutItem = cartRepository.getCartItemDetails(cartItem.getItemId());
		checkedOutItem.setStatus(ItemStatus.CHECKED_OUT.toString());
		checkedOutItem.setQuantity(cartItem.getQuantity());
		cartRepository.save(checkedOutItem);
		PostDetails item = postDetailsRepository.getItem(cartItem.getItemId());
		item.setStatus(ItemStatus.CHECKED_OUT.toString());
		postDetailsRepository.save(item);
		UserAccount postUser = userAcccountRepository.getById(checkedOutItem.getPostUserId());
		UserAccount bidUser = userAcccountRepository.getById(checkedOutItem.getBidUserId());

		ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext("Mail.xml");

	    Mail email = (Mail) context.getBean("mail");
	    email.sendMail(postUser.getEmailId(), checkedOutItem.getItemId(),"post","");
	    email.sendMail(bidUser.getEmailId(), checkedOutItem.getItemId(),"bid",postUser.getFirstName()+" "+postUser.getLastName());
		
		Response response = new Response();
		response.setSuccess(true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    }
	
	@RequestMapping(method=RequestMethod.GET,value="/getcartitems")
	public ResponseEntity<List<Cart>> getCartItems(@RequestParam("postUserId") Long postUserId)
    {
		List<Cart> cartItems = cartRepository.getCartItems(postUserId);
		return new ResponseEntity<List<Cart>>(cartItems,HttpStatus.OK);
    }	
	
	@RequestMapping(method=RequestMethod.POST,value="/removefromcart", consumes = "application/json")
	public ResponseEntity<Response> removeFromCart(@RequestBody Cart cartItem)
    {
		cartRepository.delete(cartItem);;
		PostDetails item = postDetailsRepository.getItem(cartItem.getItemId());
		item.setStatus(ItemStatus.ACTIVE.toString());
		postDetailsRepository.save(item);


		Response response = new Response();
		response.setSuccess(true);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    }
	
}
