package com.bridgelabz.loginregistrationmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.loginregistrationmvc.dao.UserDAO;
import com.bridgelabz.loginregistrationmvc.model.Login;
import com.bridgelabz.loginregistrationmvc.model.UserModel;
import com.fasterxml.uuid.Generators;

@Service
public class LoginService {
	
	private UserDAO userDAO;
	
	public LoginService() {
	}
	
	@Autowired
	public LoginService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Transactional
	public boolean registerUser(UserModel userModel) {
		if(userDAO.checkUser(userModel.getEmail())) {
			userDAO.registerUser(userModel);
			return true;
		}else {
			return false;
		}
	}
	
	public UserModel verifyUser(Login login) {
		UserModel userModel = userDAO.getUser(login.getUsername());
		if(userModel == null) {
			return userModel;
		}else if(userModel.getEmail().equals(login.getUsername()) && userModel.getPassword().equals(login.getPassword())) {
			return userModel;
		}else
			return null;
	}
	
	public String getUuuid(String username) {
		String uuid = Generators.timeBasedGenerator().generate().toString();
		userDAO.insertUuid(username, uuid);
		return uuid;
	}
	
	public UserModel getUserByUuid(String uuid) {
		return userDAO.getUserByUuid(uuid);
	}
}
