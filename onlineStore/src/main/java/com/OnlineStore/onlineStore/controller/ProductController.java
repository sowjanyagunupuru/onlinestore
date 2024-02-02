package com.OnlineStore.onlineStore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineStore.onlineStore.Service.ProductService;
import com.OnlineStore.onlineStore.external.RazorPay;
import com.OnlineStore.onlineStore.model.Cart;
import com.OnlineStore.onlineStore.model.Customer;
import com.OnlineStore.onlineStore.model.LoanData;
import com.OnlineStore.onlineStore.model.Order;
import com.OnlineStore.onlineStore.model.Product;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	RazorPay razorpay;

	/**
	 * mapping to get all the student
	 */
	// @CrossOrigin(origins = "http://localhost:8000", maxAge = 3600)
	@RequestMapping(value = "/getAllProducts", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		System.out.println("entered");
		int num = 1;
		List<Product> prodObj = productService.getAllProducts(num);
		return prodObj;
	}

	@CrossOrigin(origins = "http://localhost:8000", maxAge = 3600)
	@RequestMapping(value = "/getProduct", method = RequestMethod.POST)
	public List<Product> getAllProduct(@RequestBody Map<String, Integer> params) {
		int limit = params.get("limit");
		int offset = params.get("offset");

		List<Product> prodObj = productService.getAllProduct(limit, offset);
		return prodObj;
	}

	@CrossOrigin(origins = "http://localhost:8000", maxAge = 3600)
	@RequestMapping(value = "/getTotalRecords", method = RequestMethod.POST)
	public Integer getTotalRecords() {

		int RecordCount = productService.getTotalRecords();
		return RecordCount;
	}

	@CrossOrigin(origins = "http://localhost:52086", maxAge = 3600)
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public ResponseEntity<?> addToCart(@RequestBody LoanData cart) {
		int result = 0; // productService.addToCart(cart);
		if (result > 1) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}

	}

	@CrossOrigin
	@RequestMapping(value = "/getFromCart", method = RequestMethod.POST)
	public ResponseEntity<?> getFromCart(@RequestBody Customer customer) {
		List<Product> res = productService.getFromCart(customer);
		if (res.size() > 0) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		Cart cart = new Cart();
		cart.setEmpty(true);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
	public ResponseEntity<?> removeFromCart(@RequestBody Cart cart) {
		List<Product> res = productService.removeFromCart(cart);
		if (res.size() > 0) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		cart.setEmpty(true);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/getProductById", method = RequestMethod.POST)
	public ResponseEntity<?> getProductById(@RequestBody Product product) {
		return new ResponseEntity<>(productService.getProductById(product), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public ResponseEntity<?> addOrder(@RequestBody Order order) {
		productService.addOrder(order);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/getOrderId", method = RequestMethod.POST)
	public ResponseEntity<?> getOrderId(@RequestBody Order order) {
		// productService.addOrder(order);
		order.setOrderId(razorpay.createRazorpayOrder(order.getTotal()));
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/getOrders", method = RequestMethod.POST)
	public ResponseEntity<?> getOrders(@RequestBody Customer customer) {
		List<Product> res = productService.getOrders(customer);
		if (res.size() > 0) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		Order order = new Order();
		order.setEmpty(true);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
}
