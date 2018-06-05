package com.bridgelabz.loginregistrationmvc.dao;

import com.bridgelabz.loginregistrationmvc.model.UserModel;

public interface IUserDAO {

	boolean registerUser(UserModel userModel);
	UserModel getUser(String email);
	void insertUuid(String username,String uuid);
	UserModel getUserByUuid(String uuid);
	boolean checkUser(String email);
}
