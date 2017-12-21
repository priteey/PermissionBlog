package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;
import com.service.AdminService;
import com.service.AdminServiceImpl;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       AdminService adminService = new AdminServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String button= request.getParameter("action");
		if(button.equals("inactive")){
			List<User> inactiveEmpList = adminService.getInactiveEmp();
			request.setAttribute("inactiveEmpList", inactiveEmpList);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("inactiveEmployees.jsp");
			reqDispatcher.forward(request, response);
		}
		else if(button.equals("active")){
			List<User> activeEmpList = adminService.getActiveEmp();
			request.setAttribute("activeEmpList", activeEmpList);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("adminHomePage.jsp");
			reqDispatcher.forward(request, response);
		}
		else if(button.equals("update")){
			List<User> allEmpList = adminService.getAllEmp();
			request.setAttribute("allEmpList", allEmpList);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("organizationProfile.jsp");
			reqDispatcher.forward(request, response);
		}
		else if(button.equals("updateProfile")){
			String role=request.getParameter("role");
			System.out.println(role);
			String supervision=request.getParameter("supervision");
			System.out.println(supervision);

			User organizationProfile = adminService.getOrganizationProfile(userId);
			session.setAttribute("user", organizationProfile);
			User supervisor = adminService.getSupervisorNameById(organizationProfile.getSupervision());
			request.setAttribute("supervisor", supervisor);

			List<User> supervisionList = adminService.getsupervisionList();
			request.setAttribute("supervisionList", supervisionList);
			request.setAttribute("userId", userId);
			session.setAttribute("designation", role);
			session.setAttribute("supervision", supervision);

			RequestDispatcher reqDispatcher = request.getRequestDispatcher("updateOrganizationProfile.jsp");
			reqDispatcher.forward(request, response);
		}
		else if(button.equals("deleteActive")){
			adminService.deleteEmp(userId);
			List<User> activeEmpList = adminService.getActiveEmp();
			request.setAttribute("activeEmpList", activeEmpList);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("adminHomePage.jsp");
			reqDispatcher.forward(request, response);
		}
		else if(button.equals("deleteInactive")){
			adminService.deleteEmp(userId);
			List<User> inactiveEmpList = adminService.getInactiveEmp();
			request.setAttribute("inactiveEmpList", inactiveEmpList);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("inactiveEmployees.jsp");
			reqDispatcher.forward(request, response);
		}
		else if(button.equals("payroll")){
			List<User> allEmpList = adminService.getAllEmp();
			request.setAttribute("allEmpList", allEmpList);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("payroll.jsp");
			reqDispatcher.forward(request, response);
		}
		else{
			List<User> activeEmpList = adminService.getActiveEmp();
			request.setAttribute("activeEmpList", activeEmpList);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("adminHomePage.jsp");
			reqDispatcher.forward(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String button= request.getParameter("action");
		if(button.equals("inactive")){
		List<User> inactiveEmpList = adminService.getInactiveEmp();
		request.setAttribute("inactiveEmpList", inactiveEmpList);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("inactiveEmployees.jsp");
		reqDispatcher.forward(request, response);

		}
		else if(button.equals("active")){
			List<User> activeEmpList = adminService.getActiveEmp();
			request.setAttribute("activeEmpList", activeEmpList);	
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("adminHomePage.jsp");
			reqDispatcher.forward(request, response);

		}
		
		else if(button.equals("Update Profile")){
			HttpSession session = request.getSession();

			String divisionName= request.getParameter("divisionName");
			String role= request.getParameter("role");
			String supervision= request.getParameter("supervision");
			
			String salary= request.getParameter("salary");
//			String bonus= request.getParameter("bonus");
			String userId = request.getParameter("userId");
			User user = new User();
//			user.setBonus(bonus);
			user.setDivisionName(divisionName);
			user.setSalary(salary);
			user.setSupervision(supervision);
			user.setUserId(userId);
			String sessionSupervisor=(String) session.getAttribute("supervision");
			if(supervision!=null){
				user.setSupervision(supervision);
				user.setStatus("active");
			}
			else if(sessionSupervisor!=null){
				user.setSupervision(sessionSupervisor);
				user.setStatus("active");
			}
//			else if((sessionSupervisor==null)&&(supervision==null)){
//				user.setSupervision(supervision);
//				user.setStatus("active");
//			}
			else{
				user.setSupervision(supervision);
				user.setStatus("inactive");
			}
			if(role!=null){
				user.setRole(role);
			}
			else{
				String sessionRole=(String) session.getAttribute("designation");
				user.setRole(sessionRole);
			}
			adminService.updateProfile(user);
			User organizationProfile = adminService.getOrganizationProfile(userId);
			request.setAttribute("user", organizationProfile);
			User supervisor = adminService.getSupervisorNameById(organizationProfile.getSupervision());
			request.setAttribute("supervisor", supervisor);

			List<User> supervisionList = adminService.getsupervisionList();
			request.setAttribute("supervisionList", supervisionList);
			request.setAttribute("userId", userId);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("updateOrganizationProfile.jsp");
			reqDispatcher.forward(request, response);
		}
		else if(button.equals("Run Payroll")){
			List<User> allEmployeeList = adminService.getAllEmp();
			adminService.runPayroll(allEmployeeList);
			List<User> allEmpList = adminService.getAllEmp();
			request.setAttribute("allEmpList", allEmpList);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("payroll.jsp");
			reqDispatcher.forward(request, response);
		}
		else{
			List<User> empList = adminService.getActiveEmp();
			request.setAttribute("activeEmpList", empList);	
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("adminHomePage.jsp");
			reqDispatcher.forward(request, response);
		}
	}
}
