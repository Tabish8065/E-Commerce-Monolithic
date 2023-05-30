package com.ecommerce.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.model.UserModel;
import com.ecommerce.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	public static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@GetMapping("/getById/{id}")
	public UserModel readUserById(@PathVariable int id) {
		UserModel user = service.readUser(id);
		logger.info("User Controller -> readUserById : " + id + " : " + user.toString());
		return user;
	}

	@PostMapping("/register")
	public UserModel saveUser(@RequestBody UserModel user) {
		UserModel userRetrived = service.addUser(user);
		logger.info("User Controller -> saveUser : " + user.getUser_id() + " : " + userRetrived.toString());
		return userRetrived;
	}

	@GetMapping("/getAll")
	public List<UserModel> readAllUser() {
		List<UserModel> readAllUser = service.readAllUser();
		logger.info("UserController -> readAllUser Retrived list size : " + readAllUser.size());
		return readAllUser;
	}

	@PutMapping("/update")
	public UserModel updateUser(@RequestBody UserModel user) {
		UserModel updateUser = service.updateUser(user);
		logger.info("UserController -> updateUser : " + user.getUser_id() + " : " + updateUser);
		return updateUser;
	}

	@DeleteMapping("/delete/{id}")
	public int deleteUser(@PathVariable int id) {
		UserModel deleteUser = service.deleteUser(id);
		logger.info("UserController -> deleteUser : " + id + " : " + deleteUser);
		return deleteUser.getUser_id();
	}

	@PostMapping("/jsonImport")
	public List<UserModel> importJson(@RequestBody List<UserModel> list) {
		logger.info("UserController -> importJson : "+list.size());
		return service.addAllUser(list);
	}
}
