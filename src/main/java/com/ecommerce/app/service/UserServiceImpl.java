package com.ecommerce.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.model.UserModel;
import com.ecommerce.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private OrderService orderService;

	@Override
	public UserModel addUser(UserModel user) {
		// TODO Auto-generated method stub
		user.setOrder_id(new ArrayList<Integer>());
		return repository.save(user);
	}

	@Override
	public UserModel readUser(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
	}

	@Override
	public UserModel updateUser(UserModel user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel deleteUser(int id) {
		// TODO Auto-generated method stub
		//Fetch user to check if any active order
		UserModel user = this.readUser(id);
		
		for(int order_id : user.getOrder_id()) {
			if(orderService.getOrderStatusById(order_id)) {
				throw new RuntimeException("Active order found with id "+order_id);
			}
		}
		
		repository.deleteById(id);
		
		return user;
	}

	@Override
	public List<UserModel> addAllUser(List<UserModel> list) {
		// TODO Auto-generated method stub
		return repository.saveAll(list);
	}

	@Override
	public List<UserModel> readAllUser() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public UserModel readUserByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email);
	}

}
