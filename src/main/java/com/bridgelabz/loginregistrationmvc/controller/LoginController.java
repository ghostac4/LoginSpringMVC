package com.bridgelabz.loginregistrationmvc.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.loginregistrationmvc.model.Login;
import com.bridgelabz.loginregistrationmvc.model.UserModel;
import com.bridgelabz.loginregistrationmvc.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value="/Login",method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("login") Login login,HttpServletResponse response,HttpSession httpSession) throws IOException{
		UserModel userModel;
		if((userModel=loginService.verifyUser(login)) == null) {
			return new ModelAndView("redirect:/").addObject("loginError", "Please check if username and password is correct");
		}else {
			Cookie cookie = new Cookie("appid", loginService.getUuuid(login.getUsername()));
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			response.setHeader("pragma", "no-cache");              
            response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
            response.setHeader("Expires", "0");
			httpSession.setAttribute("user",userModel);
			return new ModelAndView("home");
		}	
	}
	
	@RequestMapping(value="/Logout")
	public ModelAndView logout(HttpServletResponse httpServletResponse,HttpSession httpSession) {
		Cookie cookie = new Cookie("appid", null);
		cookie.setMaxAge(0);
		httpServletResponse.addCookie(cookie);
		httpSession.invalidate();
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/RegistrationForm")
	public ModelAndView registrationForm() {
		return new ModelAndView("register");
	}
	
	@RequestMapping(value="/Register",method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("user") UserModel userModel,HttpServletResponse response) {
		boolean flag = loginService.registerUser(userModel);
		if(flag) {
			return new ModelAndView("index");
		}else {
			ModelAndView modelAndView = new ModelAndView("register");
			modelAndView.addObject("errorEmail", "Email Already Exits.");
			return modelAndView;
		}
	}
}
