package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.dao.UserDaoImpl;
import com.model.Directories;
import com.model.DirectoryFiles;
import com.model.User;
import com.service.EmployeeService;
import com.service.EmployeeServiceImpl;
import com.service.ManagerService;
import com.service.ManagerServiceImpl;
import com.service.UserService;
import com.service.UserServiceImpl;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
@MultipartConfig
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      EmployeeService empService= new EmployeeServiceImpl(); 
      ManagerService managerService= new ManagerServiceImpl(); 
      UserService userService= new UserServiceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String button= request.getParameter("action");
		if(button.equals("profile")){	
			User userDetails = userService.getUserDetails(userId);
	            request.setAttribute("userDetails", userDetails);
			       RequestDispatcher req = request.getRequestDispatcher("employeeHomePage.jsp");
		            req.forward(request, response);
			}
		else if(button.equals("leaves")){
			  User leaves = empService.getLeavesInfo(userId);
	            request.setAttribute("leaves", leaves);
			       RequestDispatcher req = request.getRequestDispatcher("requestLeaves.jsp");
		            req.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String button= request.getParameter("action");
		if(button.equals("Calculate")){	
			 //Dates to compare
            String CurrentDate=  request.getParameter("fromDate");
            String FinalDate=  request.getParameter("toDate");
            //validation for dates
            Date date1;
            Date date2;

            SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");

            //Setting dates
            try {
				date1 = dates.parse(CurrentDate);
				date2 = dates.parse(FinalDate);
			
            //Comparing dates
            long difference = (date2.getTime() - date1.getTime());
	           System.out.println(date1.getTime());
	           System.out.println(date2.getTime());

            long differenceDates = (difference / (24 * 60 * 60 * 1000))+1;
            System.out.println(differenceDates);
            if(differenceDates==0){
                session.setAttribute("error", "End date should be greater than start date");
                RequestDispatcher req = request.getRequestDispatcher("requestLeaves.jsp");
                req.forward(request, response);
            }
            else{
            //Convert long to String
            String dayDifference = Long.toString(differenceDates);
            request.setAttribute("fromDate", CurrentDate);
            request.setAttribute("toDate", FinalDate);
            request.setAttribute("noOfLeaves", dayDifference);
            User leaves = empService.getLeavesInfo(userId);
            request.setAttribute("leaves", leaves);
            RequestDispatcher req = request.getRequestDispatcher("requestLeaves.jsp");
            req.forward(request, response);
            }
            } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if(button.equals("Submit")){
		    String fromDate =  request.getParameter("fromDate");
            String toDate =  request.getParameter("toDate");
            String noOfLeaves = request.getParameter("noOfLeaves");
            String leaveType = request.getParameter("leaveType");
            User user= new User();
            user.setLeaves(noOfLeaves);
            user.setLeaveType(leaveType);
            user.setFromDate(fromDate);
            user.setToDate(toDate);
            user.setUserId(userId);
            user.setLeaveStatus("Pending");
            User leave = empService.getLeavesInfo(userId);
            if(leave.getLeaveStatus().equals("Pending")){
                request.setAttribute("error", "Cannot apply for leave until previous leave is approved or disapproved");
            }
            else{
            empService.addLeaves(user);
            request.setAttribute("success", "Leave requested successfully");
            }
            User leaves = empService.getLeavesInfo(userId);
            request.setAttribute("leaves", leaves);
            RequestDispatcher req = request.getRequestDispatcher("requestLeaves.jsp");
            req.forward(request, response);
		}
		else if(button.equals("profile")){	
			User userDetails = userService.getUserDetails(userId);
	            request.setAttribute("userDetails", userDetails);
			       RequestDispatcher req = request.getRequestDispatcher("employeeHomePage.jsp");
		            req.forward(request, response);
			}
		

	}

}
