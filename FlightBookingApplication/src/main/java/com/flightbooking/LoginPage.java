package com.flightbooking;

import java.sql.*;
import java.util.Scanner;

public class LoginPage {
	public static void main(String[] args) {
		try {
			LoginPage obj = new LoginPage();
			int option;
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter number 1 to signup,number 2 to login:");
			option = scanner.nextInt();

			if (option == 1) {
				insertDetails();
			} else if (option == 2) {
				validateDetails();
			} else {
				System.out.println("Enter a valid number please.");
			}

			flightDetails flightDetails = new flightDetails();
			flightDetails.displayFlightInfo();

			System.out.println("Enter number 3 to book and no 4 to exit:");
			option = scanner.nextInt();

			if (option == 3) {
				Booking booking = new Booking();
				if (booking.isAvailable()) {
					BookingDetails bookingDetails = new BookingDetails();
					BookingDetails.addBooking(booking);
					System.out.println("your booking is confirmed.");
				}
			} else {
				System.out.println("Sorry the seats are full.try another flight or date");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//        Sign up page
	public static void insertDetails() throws ClassNotFoundException{

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Username:");
		String username = scanner.nextLine();

		Scanner scanner2 = new Scanner(System.in);
		System.out.println("Enter the Password:");
		String password = scanner.nextLine();
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//	} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/Login_Page";
		String userName = "root";
		String passWord = "";

		String query = "INSERT INTO UserLoginDetails VALUES (?,?);";
		try {

			Connection con = DriverManager.getConnection(url, userName, passWord);

			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			int row = pst.executeUpdate();
			System.out.println("thanks for signing up with our application");
			con.close();

		} catch (SQLException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}

	public static void validateDetails() throws ClassNotFoundException{

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Username:");
		String usernametovalidate = scanner.nextLine();

		Scanner scanner2 = new Scanner(System.in);
		System.out.println("Enter the Password:");
		String passwordtovalidate = scanner.nextLine();
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/Login_Page";
		String userName = "root";
		String passWord = "";

		String query = "SELECT username,password FROM UserLoginDetails WHERE username='" + usernametovalidate
				+ "' AND password='" + passwordtovalidate + "'";

		try {

			Connection con = DriverManager.getConnection(url, userName, passWord);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				rs.getString(1);
				rs.getString(2);
				System.out.println("thamks for signing up with our application");
			} else {
				System.out.println("Sorry,Login details you provided is invalid.");
			}
			con.close();

		} catch (SQLException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}
}
