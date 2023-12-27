package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Connect;
import model.LoginInfo;

public class MainController {
	
	public boolean emailvalidation(String emailString) {
		  if(emailString.length()<1) {
		   Alert alert = new Alert(AlertType.INFORMATION, "Email is empty, please input email");
		      alert.show();
		   return false;
		  }
		  else {
		   int at = emailString.indexOf("@");
		   int dot = emailString.indexOf(".");
		   boolean temp;
		   boolean temp2 = SeeEmail(emailString);
		   if(at != -1 &&at != 0 &&dot != -1 &&dot != 0 &&dot > at+ 1 &&emailString.length() > dot + 1) {
		    temp = true;
		   }
		   else {
		    temp = false;
		   }
		   if(temp==false) {
		    Alert alert = new Alert(AlertType.INFORMATION, "Email is wrong, please input a correct email");
		    alert.show();
		    return false;
		   }
		   else if (temp2==false) {
		    Alert alert = new Alert(AlertType.INFORMATION, "Email is already used, please input new email");
		    alert.show();
		    return false;
		   }
		   else return true;
		  }
		  
		}
		 
		 public static ArrayList<String> getAllMails(){
		  
		  ArrayList<String> emails = new ArrayList<>();
		  Connect con = Connect.getInstance();
		  
		  String query = "SELECT * FROM users";
		  
		  con.rs = con.execQuery(query);
		  
		  try {
		   while(con.rs.next()) {
		    
		    String emailString = con.rs.getString("UserEmail");
		    emails.add(emailString);
		   }
		  } catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  
		  return emails;
		  
		 }
		 
		 public boolean usernamevalidation(String username) {
		  if(username.length()<1) {
		   Alert alert = new Alert(AlertType.INFORMATION, "Username, Email, and Password are wrong, please try again");
		      alert.show();
		   return false;
		  }
		  else {
		   return true;
		  }
		 }
		 
		 public boolean passwordvalidation(String passString, String confirmString) {
		  if(passString.length()<6) {
		   Alert alert = new Alert(AlertType.INFORMATION, "Password are empty, please input password");
		      alert.show();
		   return false;
		  }
		  else if (confirmString.length()<6) {
		   Alert alert = new Alert(AlertType.INFORMATION, "Password Confirmation are empty, please input password confirmation");
		      alert.show();
		   return false;
		  }
		  else {
		   if(passString.equals(confirmString)) {
		    return true;
		   }
		   else {
		    Alert alert = new Alert(AlertType.INFORMATION, "Password are diffrent, please try again");
		       alert.show();
		    return false;
		   }
		  }
		 }
		 
		 public boolean SeeEmail(String emailString) {
		  boolean check = true;
		  ArrayList<String> emails = getAllMails();
		  for (String email : emails) {
		      if(email.equals(emailString)) {
		       
		       check = false;
		      }    
		  }
		  return check;
		 }
		 
		 public static ArrayList<LoginInfo> getAllInfos(){
		  ArrayList<LoginInfo> infos = new ArrayList<>();
		  Connect con = Connect.getInstance();
		  String query = "SELECT * FROM users";
		  con.rs = con.execQuery(query);
		  
		  try {
		   while(con.rs.next()) {
		    
		    String emailString = con.rs.getString("UserEmail");
		    String passwordString = con.rs.getString("UserPassword");
		    infos.add(new LoginInfo(emailString, passwordString));
		   }
		  } catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  
		  return infos;
		  
		 }
		 
		 public boolean SeeLoginInfo(String emailString, String passwordString) {
		  boolean check = false;
		  ArrayList<LoginInfo> infos = getAllInfos();
		  for (LoginInfo info : infos ) {
		      if(info.getEmailString().equals(emailString)) {
		       if(info.getPasswordString().equals(passwordString)) {
		        check = true;
		       }
		       else {
		        Alert alert = new Alert(AlertType.INFORMATION, "Password is wrong, please try again");
		        alert.show();
		    }
		      }
		  }
		  return check;
		 }
		 
		 public boolean LoginEmailValidation(String emailString) {
		  if(emailString.length()<1) {
		   Alert alert = new Alert(AlertType.INFORMATION, "Email is empty, please input email");
		      alert.show();
		   return false;
		  }
		  else {
		   int at = emailString.indexOf("@");
		   int dot = emailString.indexOf(".");
		   boolean temp;
		   if(at != -1 &&at != 0 &&dot != -1 &&dot != 0 &&dot > at+ 1 &&emailString.length() > dot + 1) {
		    temp = true;
		   }
		   else {
		    temp = false;
		   }
		   if(temp==false) {
		    Alert alert = new Alert(AlertType.INFORMATION, "Email is wrong, please input a correct email");
		    alert.show();
		    return false;
		   }
		   else return true;
		  }
		  
		 }
		 
		 public boolean LoginPasswordValidation(String passString) {
		  if(passString.length()<6) {
		   Alert alert = new Alert(AlertType.INFORMATION, "Password is under 6 digit, please input another password");
		      alert.show();
		   return false;
		  }
		  else return true;
		 }
}
