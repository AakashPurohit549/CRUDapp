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
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.dao.UserDao;
import com.project.model.User;


@WebServlet("/")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

private static final Logger logger = Logger.getLogger(DataServlet.class.getName()); 
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
					
					case "/new":
						showNewForm(request,response);
						break;
					case "/insert":
						insertUser(request,response);
						break;
					case "/delete":
						deleteUser(request,response);
						break;
					case "/edit":
						showEditForm(request, response);
	                case "/update":
	                    updateUser(request, response);
	                    break;
	                default:
	                    listUser(request, response);
	                    break;
					}
						
				} catch (SQLException ex) {
		            throw new ServletException(ex);
				}
	}
	
	private void listUser(HttpServletRequest req , HttpServletResponse res) throws SQLException, IOException, ServletException{
		logger.log(Level.INFO, "list user method called");
		List<User> listUser = userDao.selectAllUsers();
		req.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = req.getRequestDispatcher(page);
		dispatcher.forward(req, res);
	}
	
	private void insertUser(HttpServletRequest req, HttpServletResponse res) throws SQLException ,IOException , ServletException {
		logger.log(Level.INFO, "Insertuser method called");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String country = req.getParameter("country");
		String number = req.getParameter("number");
		User newUser = new User(name,email,country,number);
		userDao.insertUser(newUser);
		res.sendRedirect("list");
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		logger.log(Level.INFO, "Dlete user method called");
		int id = Integer.parseInt(request.getParameter("id"));
		userDao.deleteUser(id);
		response.sendRedirect("list");
	}
	
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		logger.log(Level.INFO, "Update user method called");
		int id = Integer.parseInt(request.getParameter("id"));
		 User alreadyUser = userDao.selectUser(id);
		 request.setAttribute("user", alreadyUser);

		 
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String number = request.getParameter("number");
		
		User updatedUser = new User(id,name,email,country,number);
		userDao.updateUser(updatedUser);
		response.sendRedirect("list");

	}
	//not used as i am using modal-pop
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
		
	}
	
	//not used as i am using modal-pop
	 private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, ServletException, IOException {
			        int id = Integer.parseInt(request.getParameter("id"));
			        User existingUser = userDao.selectUser(id);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("list");
			        request.setAttribute("user", existingUser);
			        dispatcher.forward(request, response);

			    }
	

}
