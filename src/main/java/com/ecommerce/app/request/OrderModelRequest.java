package com.ecommerce.app.request;

import java.util.List;

public record OrderModelRequest(int userId, List<Integer> orderedProduct) {

}
