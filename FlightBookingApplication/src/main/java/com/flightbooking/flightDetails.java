package com.flightbooking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class flightDetails {
	public void displayFlightInfo() throws SQLException {
	String query ="select * from flight";
	Connection con = Dbconnection.getConnection();
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery(query);
	
	while(rs.next()) {
		System.out.println("the flight no:"+" "+rs.getInt(1));
		System.out.println("the flight capacity:"+" "+rs.getInt(2));
	}
	}

	public int getCapacity(int id) throws SQLException {
		String query ="select capacity from flight where id=" + id;
		Connection con = Dbconnection.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		return rs.getInt(1);
	}
}
