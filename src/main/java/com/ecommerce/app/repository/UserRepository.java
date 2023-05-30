package com.ecommerce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	UserModel findByEmail(String email);
}
