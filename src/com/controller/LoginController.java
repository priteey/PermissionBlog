package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;
import com.service.UserService;
import com.service.UserServiceImpl;

/**
 * Servlet implementation class Signin
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    UserService userService = new UserServiceImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String buttonClick= request.getParameter("signin");
		if(buttonClick.equals("Sign In")){
			response.sendRedirect("login.jsp");
		}
		else if(buttonClick.equals("Create a new account")){
			response.sendRedirect("registration.jsp");
		}
		else if(buttonClick.equals("Logout")){
	        request.getSession().invalidate();
	        response.sendRedirect("index.jsp");
		}
		else{
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId= request.getParameter("userId");
		String password= request.getParameter("password");
		
		boolean users = userService.authenticateUsers(userId,password);
		if(userId.isEmpty() || password.isEmpty()){
			if(userId.isEmpty()){
				request.setAttribute("err", "Please enter the Username");
			}
			else if(password.isEmpty()){
				request.setAttribute("err", "Please enter the Password");
			}
			
			RequestDispatcher reqdispatcher = request.getRequestDispatcher("login.jsp");
			reqdispatcher.forward(request, response);
		}
		else if(users){
			List<User> user = userService.getUserNameById(userId);
			HttpSession session = request.getSession();
			session.setAttribute("userName",user.get(0).getFname());
			session.setAttribute("userId",userId );
			session.setAttribute("role",user.get(0).getRole() );


			if(user.get(0).getRole().equals("Admin")){
				
			 RequestDispatcher reqdispatcher = request.getRequestDispatcher("AdminController?action=active");
				reqdispatcher.forward(request, response);
			}
			else {
				RequestDispatcher reqdispatcher = request.getRequestDispatcher("EmployeeController?action=profile");
				reqdispatcher.forward(request, response);
			}
			
		}
		else{
			request.setAttribute("err", "Enter valid Username and Password");
			RequestDispatcher reqdispatcher = request.getRequestDispatcher("login.jsp");
			reqdispatcher.forward(request, response);
		}
	}
}
