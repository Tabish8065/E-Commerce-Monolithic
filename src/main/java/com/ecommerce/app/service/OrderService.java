package com.ecommerce.app.service;

import java.util.List;

import com.ecommerce.app.model.OrderModel;
import com.ecommerce.app.request.OrderModelRequest;
import com.ecommerce.app.response.OrderModelResponse;


public interface OrderService {

	OrderModelResponse addOrder(OrderModelRequest order);
	public OrderModelResponse readOrder(int id);
	public List<OrderModel> readAllOrder();
	public OrderModel updateOrder(OrderModel order);
	public OrderModelResponse deleteOrder(int id);
	boolean getOrderStatusById(int order_id);
}
