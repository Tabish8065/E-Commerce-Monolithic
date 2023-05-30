package com.ecommerce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.model.ProductModel;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
	
	@Transactional
	@Modifying
	@Query(value="UPDATE product_model SET quantity_available = quantity_available + 1 WHERE product_id = :product_id",nativeQuery = true)
	public void incrementQuantityByOne(@Param("product_id") int product_id);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE product_model SET quantity_available = quantity_available + 1 WHERE product_id = :product_id",nativeQuery = true)
	public void decrementQuantityByOne(@Param("product_id") int product_id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE product_model SET quantity_available = 0 WHERE product_id = :product_id", nativeQuery = true)
	public void deleteById(@Param("product_id") int product_id);

}
