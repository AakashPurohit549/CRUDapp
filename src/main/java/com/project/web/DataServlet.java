package com.project.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.project.dao.UserDao;
import com.project.model.User;


@WebServlet("/")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

private static final Logger logger = LogManager.getLogger();

	private UserDao userDao;
	
	public void init() {
		userDao = new UserDao();
	}
       
   
    public DataServlet() {
        super();
    }

    String page = "index.jsp";
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	doGet(request,response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String actions = request.getServletPath();
				try {
					switch(actions) {
					
					case "/insert":
						createUser(request,response);
						break;
					case "/delete":
						deleteUser(request,response);
						break;
	                case "/update":
	                    updateUser(request, response);
	                    break;
	                default:
	                    listUser(request, response);
	                    break;
					}
						
				} catch (SQLException ex) {
					logger.error("exception occured in the servlet calling",ex);
		            throw new ServletException(ex);
				}
	}
	
	private void listUser(HttpServletRequest req , HttpServletResponse res) throws SQLException, IOException, ServletException{
		logger.info("hello you used log4j for calling the list user servlet ");
		List<User> listUser = userDao.getAllUsers();
		req.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = req.getRequestDispatcher(page);
		dispatcher.forward(req, res);
	}
	
	private void createUser(HttpServletRequest req, HttpServletResponse res) throws SQLException ,IOException , ServletException {
		logger.info("create user method called ");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String country = req.getParameter("country");
		String number = req.getParameter("number");
		User newUser = new User(name,email,country,number);
		userDao.createUser(newUser);
		res.sendRedirect("list");
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		logger.info("Delete user serlvet called " );
		int id = Integer.parseInt(request.getParameter("id"));
		userDao.deleteUser(id);
		response.sendRedirect("list");
	}
	
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
	
		int id = Integer.parseInt(request.getParameter("id"));
		 User alreadyUser = userDao.getUser(id);
		 request.setAttribute("user", alreadyUser);

		 
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String number = request.getParameter("number");
		
		User updatedUser = new User(id,name,email,country,number);
		userDao.updateUser(updatedUser);
		logger.info("Update user servlet called");
		response.sendRedirect("list");

	}
}
