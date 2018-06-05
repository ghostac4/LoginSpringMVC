package com.bridgelabz.loginregistrationmvc.interceptor;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ValidationInterceptor extends HandlerInterceptorAdapter {

	private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	private static Pattern pattern;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView("forward:/RegistrationForm");
		pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		String username = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String dateOfBirth = request.getParameter("dateOfBirth");
		
		modelAndView.addObject("username", username);
		modelAndView.addObject("email", email);
		modelAndView.addObject("dateOfBirth", dateOfBirth);
		
		long mobile;

		if (username.equals("")) {
			modelAndView.addObject("errorUsername", "Username cannot be Empty");
			throw new ModelAndViewDefiningException(modelAndView);
		} else if (password.length() < 5 && password.length() > 10) {
			modelAndView.addObject("errorPassword", "Password length should be between 5-10");
			throw new ModelAndViewDefiningException(modelAndView);
		} else if (!pattern.matcher(email).matches()) {
			modelAndView.addObject("errorEmail", "Invalid Email");
			throw new ModelAndViewDefiningException(modelAndView);
		} else if (request.getParameter("mobile").equals("")) {
			modelAndView.addObject("errorMobile", "Mobile Cannot be blank");
			throw new ModelAndViewDefiningException(modelAndView);
		} else {
			mobile = Long.parseLong(request.getParameter("mobile"));
			if (mobile < 1111111111 || mobile > (1111111111 * 9)) {
				modelAndView.addObject("errorMobile", "Invalid Mobile Number");
				throw new ModelAndViewDefiningException(modelAndView);
			} else {
				return true;
			}
		}
	}
}
