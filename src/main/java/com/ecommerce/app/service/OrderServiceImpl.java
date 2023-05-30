package com.ecommerce.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dto.OrderedProduct;
import com.ecommerce.app.model.OrderModel;
import com.ecommerce.app.model.ProductModel;
import com.ecommerce.app.model.UserModel;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.request.OrderModelRequest;
import com.ecommerce.app.response.OrderModelResponse;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Transactional
	@Override
	public OrderModelResponse addOrder(OrderModelRequest orderReq) {
		// TODO Auto-generated method stub
		
		//Fetch that if user is present or not
		UserModel user = userService.readUser(orderReq.userId());
		
		//Fetch List of products
		List<ProductModel> products = new ArrayList<>();
		orderReq.orderedProduct().stream()
			.forEach(id -> {
				ProductModel prod = productService.readProduct(id);
				products.add(prod);
				productService.decrementQuantityByOne(id);
			});
		
		//Calculating the total amount of order
		double totalAmount = products.stream()
				.mapToDouble(product -> product.getPrice())
				.sum();
		
		//Feeding the above in the OrderModel
		OrderModel orderModel = new OrderModel();
		orderModel.setUser(orderReq.userId());
		orderModel.setPrice(totalAmount);
		orderModel.setActive(true);
		orderModel.setPlacedDate(LocalDateTime.now());
		orderModel.setOrderedProduct(orderReq.orderedProduct());
		
		//Saving to repository
		int orderId = orderRepository.save(orderModel).getOrder_id();
		
		//Preparing the record for the response as custom object
		List<OrderedProduct> orderedProduct = products.stream()
				.map(product -> new OrderedProduct(
						product.getProduct_id(),
						product.getName(),
						product.getPrice())
				)
				.collect(Collectors.toList());
		
		OrderModelResponse response = new OrderModelResponse(orderId, user.getUser_id(), 
				user.getFirstName()+" "+user.getLastName(), 
				orderModel.getPlacedDate(), totalAmount, 
				orderModel.isActive()?"Active":"Not Active", 
						orderedProduct);
		
		return response;
	}

	@Override
	public OrderModelResponse readOrder(int id) {
		// TODO Auto-generated method stub
		
		//Read the order by id
		OrderModel order = orderRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Order with id "+id+" is not present.") );
		
		//Fetch the product details form product Service
		List<OrderedProduct> orderedProducts = order.getOrderedProduct().stream()
				.map(product_id -> {
					ProductModel product = productService.readProduct(product_id);
					return new OrderedProduct(product.getProduct_id(), product.getName(), product.getPrice());
				}).toList();
		
		//Fetch the user details of the id
		UserModel user = userService.readUser(order.getUser());
		
		//Preparing the record for the response as custom object
		OrderModelResponse response = new OrderModelResponse(order.getOrder_id(), user.getUser_id(), 
				user.getFirstName()+" "+user.getLastName(), 
				order.getPlacedDate(), order.getPrice(), 
				order.isActive()?"Active":"Not Active", 
						orderedProducts);
		return response;
	}

	@Override
	public List<OrderModel> readAllOrder() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public OrderModel updateOrder(OrderModel order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderModelResponse deleteOrder(int id) {
		// TODO Auto-generated method stub
		orderRepository.returnOrder(id);
		
		OrderModelResponse response = this.readOrder(id);
		
		//Increment the product by one
		response.orderedProduct().stream()
			.forEach(product -> {
				productService.incrementQuantityByOne(product.productId());
			});
		
		return response;
	}

	@Override
	public boolean getOrderStatusById(int order_id) {
		return orderRepository.readOrderStatus(order_id);
	}

}
