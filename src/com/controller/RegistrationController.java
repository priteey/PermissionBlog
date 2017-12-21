package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;
import com.service.RegisterService;
import com.service.RegisterServiceImpl;
import com.service.UserService;
import com.service.UserServiceImpl;

/**
 * Servlet implementation class RegistrationPage
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 UserService userService= new UserServiceImpl();
	 RegisterService regService= new RegisterServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	
		boolean userExist = false;
		String firstName= request.getParameter("fname");
		String lastName= request.getParameter("lname");
		String gender= request.getParameter("gender");
		String emailId= request.getParameter("emailId");
		String userId= request.getParameter("userId");
		String password= request.getParameter("password");
		String confirmPassword= request.getParameter("confirmPassword");
		String role= request.getParameter("role");
		String address= request.getParameter("address");
		String phoneNo= request.getParameter("phoneNo");
		String status= request.getParameter("status");

		User user = new User();
		user.setEmailId(emailId);
		user.setFname(firstName);
		user.setLname(lastName);
		user.setPassword(password);
		user.setUserId(userId);
		user.setRole(role);
		user.setAddress(address);
		user.setPhoneNo(phoneNo);
		user.setStatus(status);
		user.setGender(gender);
		if(!userId.isEmpty()){
			 userExist= regService.checkUserIdExist(userId);
		}
		if(user.getFname().isEmpty()||user.getLname().isEmpty()||user.getUserId().isEmpty()||!user.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$") || userExist==true ){
		if(user.getFname().isEmpty()){
			request.setAttribute("firstNameErr", "Please enter the First Name");
		}
		if(user.getLname().isEmpty()){
			request.setAttribute("lastNameErr", "Please enter the Last Name");
		}
		if(userId.isEmpty()){
			request.setAttribute("userIdErr", "Please enter the User Name");
		}
		if(!user.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
			request.setAttribute("passErr", "Minimum 8 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character");
		}
		if(confirmPassword.isEmpty()){
			request.setAttribute("cnpassErr", "Re-Enter password");
		}
		if(userExist){
			request.setAttribute("userIdErr", "UserName already exists");
		}
		RequestDispatcher reqdispatcher = request.getRequestDispatcher("registration.jsp");
		reqdispatcher.forward(request, response);
		}
		else{
			userService.addUser(user);
			session.setAttribute("userName", user.getFname());
//			session.setAttribute("userId", user.getUserId());
			request.setAttribute("UserRegisteredMsg", "User Registered Successfully");
			RequestDispatcher reqdispatcher = request.getRequestDispatcher("login.jsp");
			reqdispatcher.forward(request, response);
		}
	}
}
