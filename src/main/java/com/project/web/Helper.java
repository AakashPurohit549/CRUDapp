package com.project.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

// created only to learn about JDBC connection
public class Helper {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_new_db","root","root");
			
			Statement statement;
			statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("SELECT * FROM users");
			int id;
			String name;
			String email;
			String country;
			while(resultSet.next()) {
				id = resultSet.getInt("id");
				name = resultSet.getString("name").trim();
				email = resultSet.getString("email").trim();
				country = resultSet.getString("country").trim();
				System.out.println("Id: " + id + "\nName: " + name + "\nEmail: " +
				email + "\nCountry: " + country);
			}
			
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
