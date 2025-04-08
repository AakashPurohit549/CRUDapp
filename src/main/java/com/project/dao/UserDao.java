package com.project.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.model.User;

public class UserDao {

	public UserDao() {}
	
	//private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country, number) VALUES " +
            " (?, ?, ?, ?);";
   // private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

	
	protected Connection getConnection(){
		Connection connection = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_new_db?useSSL=false","root","root");
			System.out.println("Connect to db");
			System.out.println("Current Working Directory: " + new File(".").getAbsolutePath());
			System.out.println("Here is the login time " + DriverManager.getLoginTimeout());
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	//List of all users 
	public List <User> selectAllUsers(){
		
		List<User> users = new ArrayList<>();
		
		try(Connection connection = getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement("Select * from users")){
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("Country");
				String number = rs.getString("number");
				users.add(new User(id,name,email,country,number));
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return users;
			
	}
	
	//selecting user by id
	public User selectUser(int id) {
		User user = null;
		
		try(Connection connection = getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,email,country,number FROM users WHERE id=?");
				){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("Country");
				String number = rs.getString("number");
				user = new User(id,name,email,country,number);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	//to insert a new user
	public void insertUser(User user) {
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
            statement.setInt(5, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
	
	
	
	
	
	
	
	
}
