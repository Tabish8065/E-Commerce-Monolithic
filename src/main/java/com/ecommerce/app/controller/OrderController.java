package com.ecommerce.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.model.OrderModel;
import com.ecommerce.app.request.OrderModelRequest;
import com.ecommerce.app.response.OrderModelResponse;
import com.ecommerce.app.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService service;


	public static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@PostMapping("/register")
	public OrderModelResponse bookOrder(@RequestBody OrderModelRequest order) {
		logger.info("OrderController -> bookOrder : "+order);
		return service.addOrder(order);
	}
	
	@GetMapping("/getById/{id}")
	public OrderModelResponse retriveOrderById(@PathVariable int id) {
		logger.info("OrderController -> retriveOrderById : "+id);
		return service.readOrder(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAll")
	public List<OrderModel> retriveAllOrder(){
		logger.info("OrderController -> retriveAllOrder");
		return service.readAllOrder();
	}
	
	@DeleteMapping("/delete/{id}")
	public OrderModelResponse deleteOrder(@PathVariable int id) {
		logger.info("OrderController -> deleteOrder : "+id);
		return service.deleteOrder(id);
	}
	

}
