package com.OnlineStore.onlineStore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineStore.onlineStore.Service.LoginService;
import com.OnlineStore.onlineStore.model.Customer;
import com.OnlineStore.onlineStore.model.Login;

import java.util.List;

@RestController
public class LoginController {

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;

	/**
	 * mapping to get all the student
	 */
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Customer customer) {
		logger.debug("login");
		return new ResponseEntity<>(loginService.login(customer), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ResponseEntity<?> signUp(@RequestBody Customer customer) {
		logger.debug("login");
		return new ResponseEntity<>(loginService.signUp(customer), HttpStatus.OK);
	}
}
