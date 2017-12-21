package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Directories;
import com.model.DirectoryFiles;
import com.model.User;
import com.service.ManagerService;
import com.service.ManagerServiceImpl;

/**
 * Servlet implementation class ManagerController
 */
@WebServlet("/ManagerController")
public class ManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ManagerService managerService = new ManagerServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = request.getParameter("userId");
		String button= request.getParameter("action");
		String userId=(String) session.getAttribute("userId");
		if(button.equals("profile")){	
			}
		else if(button.equals("leaves")){
			  User leaves = managerService.getLeavesInfo(userId);
	            request.setAttribute("leaves", leaves);
			       RequestDispatcher req = request.getRequestDispatcher("requestLeaves.jsp");
		            req.forward(request, response);
		}
		else if(button.equals("bonus")){	
			List<User> empList= managerService.getEmployees(userId);
			request.setAttribute("empList", empList);
			request.setAttribute("bonus", "assigned");

		      RequestDispatcher req = request.getRequestDispatcher("assignBonus.jsp");
	            req.forward(request, response);
		}
		else if(button.equals("assignBonus")){	
			User u = new User();
			u.setUserId(userID);
			List<User> empList= managerService.getEmployees(userId);
			request.setAttribute("empList", empList);
			request.setAttribute("bonus", "bonusAssigned");
			session.setAttribute("userID", userID);

		      RequestDispatcher req = request.getRequestDispatcher("assignBonus.jsp");
	            req.forward(request, response);
		}
		else if(button.equals("manageLeaves")){	
			List<User> leavesList= managerService.getLeavesRequest(userId);
			request.setAttribute("leavesList", leavesList);
			RequestDispatcher req = request.getRequestDispatcher("manageRequestLeaves.jsp");
            req.forward(request, response);
		}
		else if(button.equals("acceptLeaves")){	
			managerService.acceptLeave(userID);
			request.setAttribute("leavesList", "Leaves accepted successfully");
			List<User> leavesList= managerService.getLeavesRequest(userId);
			request.setAttribute("leavesList", leavesList);
			 RequestDispatcher req = request.getRequestDispatcher("manageRequestLeaves.jsp");
	            req.forward(request, response);
		}
		else if(button.equals("declineLeaves")){
			managerService.declineLeave(userID);
			request.setAttribute("success", "Leaves declined successfully");
			List<User> leavesList= managerService.getLeavesRequest(userId);
			request.setAttribute("leavesList", leavesList);
			 RequestDispatcher req = request.getRequestDispatcher("manageRequestLeaves.jsp");
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
            User leaves = managerService.getLeavesInfo(userId);
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
            User leave = managerService.getLeavesInfo(userId);
            if(leave.getLeaveStatus().equals("Pending")){
                request.setAttribute("error", "Cannot apply for leave until previous leave is approved or disapproved");
            }
            else{
            	managerService.addLeaves(user);
                request.setAttribute("success", "Leave requested successfully");
            }
            User leaves = managerService.getLeavesInfo(userId);
            request.setAttribute("leaves", leaves);
            RequestDispatcher req = request.getRequestDispatcher("requestLeaves.jsp");
            req.forward(request, response);
		}
		else if(button.equals("Assign")){
			String bonus = request.getParameter("bonus");
			String userID = (String) session.getAttribute("userID");
			User u = new User();
			u.setBonus(bonus);
			u.setUserId(userID);
			managerService.addBonus(u);
			List<User> empList= managerService.getEmployees(userId);
			request.setAttribute("empList", empList);
			request.setAttribute("bonus", "assigned");
		      RequestDispatcher req = request.getRequestDispatcher("assignBonus.jsp");
	            req.forward(request, response);
		}

	}
}
