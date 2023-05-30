package com.ecommerce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.model.OrderModel;

import jakarta.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

	@Query(value = "SELECT is_active FORM order_model WHERE order_id = :order_id"
			, nativeQuery = true)
	public boolean readOrderStatus(@Param("order_id") int order_id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE order_model SET is_active = false WHERE order_id = :order_id",
	nativeQuery = true)
	public void returnOrder(@Param("order_id") int order_id);

}
