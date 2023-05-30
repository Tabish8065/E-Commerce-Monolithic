package com.ecommerce.app.service;

import java.util.List;

import com.ecommerce.app.model.UserModel;

public interface UserService {

	public UserModel addUser(UserModel user);
	public UserModel readUser(int id);
	public UserModel updateUser(UserModel user);
	public UserModel deleteUser(int id);
	public List<UserModel> addAllUser(List<UserModel> list);
	public List<UserModel> readAllUser();
	public UserModel readUserByEmail(String email);
}
