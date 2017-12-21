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
import com.service.UserService;
import com.service.UserServiceImpl;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 UserService userService= new UserServiceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String button= request.getParameter("action");
		if(button.equals("Password")){
			response.sendRedirect("updatePassword.jsp");
		}
		else{
			HttpSession session = request.getSession();
			String userId=(String) session.getAttribute("userId");
			User u=userService.getUserDetails(userId);
			session.setAttribute("user", u);
			session.setAttribute("password", u.getPassword());
			RequestDispatcher reqdispatcher = request.getRequestDispatcher("updateProfile.jsp");
			reqdispatcher.forward(request, response);
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId=(String) session.getAttribute("userId");
		String button= request.getParameter("action");
		 if(button==null){
			String currentPassword= request.getParameter("currentpassword");
			String password= request.getParameter("password");
			String cnfirmPassword=request.getParameter("confirmPassword");
			User u=userService.getUserDetails(userId);
			String userPassword=u.getPassword();
			if(!currentPassword.equals(userPassword)||password.equals(userPassword)||!cnfirmPassword.equals(password)||currentPassword.isEmpty()||password.isEmpty()||cnfirmPassword.isEmpty()||!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
				if(currentPassword.isEmpty()||password.isEmpty()||cnfirmPassword.isEmpty()){
					request.setAttribute("error", "Enter all details");

				}
			else if(!currentPassword.equals(userPassword)){
				request.setAttribute("error", "Current Password is invalid");
			}
			else if(password.equals(userPassword)){
				request.setAttribute("error", "Enter New Password");

			}
			else if(!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
				request.setAttribute("error","Minimum 8 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character");
			}
			else if(!cnfirmPassword.equals(password)){
				request.setAttribute("error", "Confirm Password incorrect");

			}
				RequestDispatcher reqdispatcher = request.getRequestDispatcher("updatePassword.jsp");
				reqdispatcher.forward(request, response);
				}
				else {
					u.setPassword(password);
					u.setUserId(userId);
					userService.updateUser(u);

					request.setAttribute("success", "Password Updated Successfully ");
					RequestDispatcher reqdispatcher = request.getRequestDispatcher("updatePassword.jsp");
					reqdispatcher.forward(request, response);
			}
			
		}
		 else{
		String firstName= request.getParameter("fname");
		String lastName= request.getParameter("lname");
		String emailId= request.getParameter("emailId");
		String password=(String) session.getAttribute("password");
		String address= request.getParameter("address");
		String phoneNo= request.getParameter("phoneNo");
		String gender= request.getParameter("gender");

		User user = new User();
		user.setEmailId(emailId);
		user.setFname(firstName);
		user.setLname(lastName);
		user.setUserId(userId);
		user.setAddress(address);
		user.setPhoneNo(phoneNo);
		user.setGender(gender);

//	if(!user.getEmailId().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
		/*if(!userName.equals(userId)){
			if(!userName.isEmpty()){
				 userExist= userService.checkUserIdExist(userName);
			}
			if(userExist){
				request.setAttribute("error", "UserName already exists");
				
			}
		}*/
		if(user.getFname().isEmpty() || user.getLname().isEmpty() || !user.getEmailId().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") ){
			if(user.getFname().isEmpty()){
				request.setAttribute("error", "Enter First Name");
			}
			else if(user.getLname().isEmpty()){
				request.setAttribute("error", "Enter Last Name");
			}
			else if(!user.getEmailId().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
				request.setAttribute("error", "Enter valid emailId");
			}
			
		User u=userService.getUserDetails(userId);
		session.setAttribute("user", u);
		session.setAttribute("userName", user.getFname());
		RequestDispatcher reqdispatcher = request.getRequestDispatcher("updateProfile.jsp");
		reqdispatcher.forward(request, response);
	}
	else{
		user.setPassword(password);
		userService.updateUser(user);
		request.setAttribute("success", "Profile Updated Successfully");
		User u=userService.getUserDetails(userId);
		session.setAttribute("user", u);
		session.setAttribute("userName", user.getFname());
		RequestDispatcher reqdispatcher = request.getRequestDispatcher("updateProfile.jsp");
		reqdispatcher.forward(request, response);
		}
		}
		}
}
