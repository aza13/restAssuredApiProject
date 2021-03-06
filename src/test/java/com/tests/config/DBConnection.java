package com.tests.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.tests.utils.Utils;


public class DBConnection {
	
	Properties dbConnection;
	Connection conn=null;
	String url;
	String user;
	String password;
	
	public DBConnection() {
		dbConnection= Utils.loadProperties("DBConnection.properties");
	}
	
	public void setQA() {
		
		 url = dbConnection.getProperty("url");
		 user=dbConnection.getProperty("user");
		 password=dbConnection.getProperty("password");
	}
	

	
	public ArrayList<String> query(String query) {
		ArrayList<String> arreglo=new ArrayList<String>();
		try {
			conn = DriverManager.getConnection(url, user,password);
			Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
        	
            if (rs.next()) {
            	for(int i=0;i<20;i++) {
            		arreglo.add(rs.getString(i+1));
            	}
            }
            conn.close();
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
		}
		return arreglo;
	}

}
