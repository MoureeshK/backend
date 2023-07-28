package com.flightbooking;

import java.sql.*;


public class BookingDetails {

	public int getBookedCount(int FlightNo,java.util.Date date) throws SQLException{
//		Class.forName("com.mysql.cj.jdbc.Driver");
		String query = "select COUNT(passenger_name) from booking where FlightNo=? and travel_date=? ";
		Connection con = Dbconnection.getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		java.sql.Date sqldate = new java.sql.Date(date.getTime());
		pst.setInt(1, FlightNo);
		pst.setDate(2, sqldate);
		ResultSet rs = pst.executeQuery();
//		int passengerCount = 0;
		return rs.getInt("passenger_name");	
	}

	public static void addBooking(Booking booking) throws SQLException{
//		Class.forName("com.mysql.cj.jdbc.Driver");
		String query = "insert into booking values(?,?,?)";
		Connection con = Dbconnection.getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		java.sql.Date sqldate = new java.sql.Date(booking.date.getTime());
		pst.setString(1, booking.PassengerName);
		pst.setInt(2, booking.FlightNo);
		pst.setDate(3, sqldate);
		pst.executeUpdate();
	}
}
