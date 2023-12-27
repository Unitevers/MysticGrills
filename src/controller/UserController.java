package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Connect;
import model.User;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
	
	//untuk memvalidasi parameter
	public static void createUser(String UserRole, String UserName, String UserEmail, String UserPassword) {
		if(UserName.isBlank() || UserEmail.isBlank() || UserPassword.isBlank()) {
			Alert alert = new Alert(AlertType.INFORMATION, "Name, Email, Password, and Role must not be empty!");
	    	alert.show();
		}
		else {
			User.createUser(UserRole, UserName, UserEmail, UserPassword);
		}
	}
	
	//untuk memvalidasi parameter
	public static void updateUser(int UserID, String UserRole, String UserName, String UserEmail, String UserPassword) {
		if(UserRole.isBlank() || UserName.isBlank() || UserEmail.isBlank() || UserPassword.isBlank()) {
			Alert alert = new Alert(AlertType.INFORMATION, "Name, Email, Password, and Role must not be empty!");
	    	alert.show();
		}
		else {
			User.updateUser(UserID, UserRole, UserName, UserEmail, UserPassword);
		}
	}
	
	//untuk memvalidasi parameter
	public static void deleteUser(int UserID) {
		String query = String.format("SELECT * FROM users WHERE `UserID` = '%d'", UserID);
        Connect con = Connect.getInstance();
        con.execQuery(query);
		try {
			if(con.rs.next()) {
				User.deleteUser(UserID);
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION, "User ID does not exits!");
				alert.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//untuk memvalidasi parameter
	public static User authenticateUser(String UserEmail, String UserPassword) {
		String query = String.format("SELECT * FROM users WHERE `UserEmail` = '%s' AND `UserPassword` = '%s'", UserEmail, UserPassword);
        Connect con = Connect.getInstance();
        con.execQuery(query);
		try {
			if(con.rs.next()) {
				 return User.authenticateUser(UserEmail, UserPassword);
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION, "User ID does not exits!");
				alert.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//untuk menyambungkan controller dengan model
	public static ArrayList<User> getAllUsers() {
		return User.getAllUsers();
	}
	
	//untuk memvalidasi parameter
	public User getUserById(int UserID) {
		String query = String.format("SELECT * FROM users WHERE `UserID` = '%d'", UserID);
        Connect con = Connect.getInstance();
        con.execQuery(query);
		try {
			if(con.rs.next()) {
				return User.getUserById(UserID);
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION, "User ID does not exits!");
				alert.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//untuk mengechek email dan password yang dimasukin udh benar atau belum
	public boolean UserLogin(String UserEmail, String UserPassword) {
		  boolean check = false;
		  String query = String.format("SELECT * FROM users WHERE UserEmail = '%s' AND UserPassword = '%s'", UserEmail, UserPassword);
		        Connect con = Connect.getInstance();
		        con.execQuery(query);
		  try {
		   if(con.rs.next()) {
		    check = true;
		   }
		   else {
		    Alert alert = new Alert(AlertType.INFORMATION, "Email or password are wrong, please try again");
		       alert.show();
		    check = false;
		   }
		  } catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  return check;
	}
}
