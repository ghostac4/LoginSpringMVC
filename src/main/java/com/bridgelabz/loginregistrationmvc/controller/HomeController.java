package com.bridgelabz.loginregistrationmvc.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.loginregistrationmvc.model.UserModel;
import com.bridgelabz.loginregistrationmvc.service.LoginService;

@Controller
public class HomeController {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(value="/")
	public ModelAndView home(HttpServletRequest httpServletRequest, HttpServletResponse response,HttpSession httpSession) throws IOException{
		Cookie[] cookies = httpServletRequest.getCookies();
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("appid")) {
					UserModel userModel = loginService.getUserByUuid(cookie.getValue());
					if(userModel!=null) {
						httpSession.setAttribute("user", userModel);
						return new ModelAndView("home");
					}
				}
			}
		}
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/Home")
	public ModelAndView getHome() {
		return new ModelAndView("home");
	}
}
