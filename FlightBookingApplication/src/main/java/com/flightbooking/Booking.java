package com.flightbooking;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Booking {
	String PassengerName;
	int FlightNo;
    java.util.Date date;
	
	Booking(){
	Scanner scanner = new Scanner(System.in);
	System.out.println("Enter the passengers name:");
	PassengerName = scanner.next();
	System.out.println("Enter the FlightNo:");
	FlightNo = scanner.nextInt();
	System.out.println("Enter the date:YYYY-MM-DD:");
	String dateInput = scanner.next();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	try {
		date = dateFormat.parse(dateInput);
	}catch(ParseException e){
		e.printStackTrace();
	}
	
	}

	public boolean isAvailable() throws SQLException, ClassNotFoundException {
		flightDetails flightDetails = new flightDetails();
		BookingDetails bookingDetails = new BookingDetails();
		int capacity = flightDetails.getCapacity(FlightNo);
		
		int booked = bookingDetails.getBookedCount(FlightNo, date);
		
		return booked<capacity?true:false;
	}
}
