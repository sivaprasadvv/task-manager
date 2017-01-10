package com.task.manager.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtility {
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		
		if (null != connection) {
			return connection;
		} else {
			try {
				Properties props = new Properties();
				InputStream is = DBUtility.class.getClassLoader().getResourceAsStream("/config.properties");
				props.load(is);
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				connection = DriverManager.getConnection(url, username, password);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return connection;
		
					
					
		
	}
	

}
