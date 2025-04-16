package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.model.User;

public class UserDao {

	public UserDao() {}
	
	private static final Logger logger = LogManager.getLogger();
	
    private static final String INSERT_USERS_SQL = "insert into users" + "  (name, email, country, number) VALUES " +
            " (?, ?, ?, ?);";
  

	
	protected Connection getConnection(){
		Connection connection = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_new_db?useSSL=false","root","root");
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.error("exception occured in database connection",e);
		}
		return connection;
	}
	
	
	//List of all users  selectAllUsers == getAllUsers
	public List <User> getAllUsers(){
		
		List<User> users = new ArrayList<>();
		
		try(Connection connection = getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement("Select * from users")){
			System.out.println(preparedStatement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String country = resultSet.getString("country");
				String number = resultSet.getString("number");
				users.add(new User(id,name,email,country,number));
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("exception in the userDao getAllUser methods ");
			
		}
		return users;
			
	}
	
	//get user by id         
	public User getUser(int id) {
		User user = null;
		
		try(Connection connection = getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,email,country,number FROM users WHERE id=?");
				){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String country = resultSet.getString("country");
				String number = resultSet.getString("number");
				user = new User(id,name,email,country,number);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("exception occured in the getUser method of userDao");
		}
		
		return user;
	}
	
	//to create a new user
	public void createUser(User user) {
		System.out.println(INSERT_USERS_SQL);
		
		try(Connection connection = getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
				){
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setString(4, user.getNumber());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception occured in the createUser method");
			
		}
	}
	
	
	//to delete a user
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try(Connection connection = getConnection(); 
				PreparedStatement statement = connection.prepareStatement("delete from users where id=?");
				){
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
			System.out.println("User Deleted successfully" + id);
		} 
		return rowDeleted;
	}
	
	
	//update user
	public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("update users set name = ?,email= ?, country =?, number =? where id = ?;");) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
			statement.setString(4, user.getNumber());
            statement.setInt(6, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
	
}
