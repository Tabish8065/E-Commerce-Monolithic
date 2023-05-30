package com.ecommerce.app.response;

import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce.app.dto.OrderedProduct;

public record OrderModelResponse(int orderId, int userId, String userName, LocalDateTime placedDate, double totalAmount, String status, List<OrderedProduct> orderedProduct) {

}
