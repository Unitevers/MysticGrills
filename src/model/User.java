package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class User {

	private int UserID;
	private String UserName;
	private String UserEmail;
	private String UserPassword;
	private String UserRole;
	
	//constructor user
	public User(int userID, String userName, String userEmail, String userPassword, String userRole) {
		super();
		this.UserID = userID;
		this.UserName = userName;
		this.UserEmail = userEmail;
		this.UserPassword = userPassword;
		this.UserRole = userRole;
	}
	
	//metode untuk delete user
	public static void deleteUser(int UserID) {
		String query = String.format("DELETE FROM `users` WHERE `UserID` = '%d'", UserID);
        Connect con = Connect.getInstance();
        con.execUpdate(query);
	}
	
	//metode untuk mengambil user dari user id
	public static User getUserById(int UserID){
		String query = String.format("SELECT * FROM users WHERE `UserID`= '%d'", UserID);
		Connect con = Connect.getInstance();
		con.rs = con.execQuery(query);
		
		try {
			if(con.rs.next()) {
				System.out.println("Masuk");
				String UserName = con.rs.getString("UserName");
				String UserEmail = con.rs.getString("UserEmail");
				String UserPassword = con.rs.getString("UserPassword");
				String UserRole = con.rs.getString("UserRole");
				return new User(UserID, UserName, UserEmail, UserPassword, UserRole);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//metode untuk membuat user
	public static void createUser(String UserRole, String UserName, String UserEmail, String UserPassword) {
		String query = String.format("INSERT INTO users(UserName, UserEmail, UserPassword, UserRole) VALUES (\"%s\", \"%s\", \"%s\", \"%s\")", UserName, UserEmail, UserPassword, UserRole); 
		Connect con = Connect.getInstance();
		con.execUpdate(query);
	}
	
	//metode untuk memperbarui data user
	public static void updateUser(int UserID, String UserRole, String UserName, String UserEmail, String UserPassword) {
		String query = String.format("UPDATE users SET `UserRole`=\'%s\',`UserName`=\'%s\',`UserEmail`=\'%s\',`UserPassword`=\'%s\' WHERE `UserID` = \'%d\' ", UserRole, UserName, UserEmail, UserPassword, UserID);
        Connect con = Connect.getInstance();
        con.execUpdate(query);
	}
	
	//metode untuk mengambil data user
	public static ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		Connect con = Connect.getInstance();
		
		String query = "SELECT * FROM users";
		
		con.rs = con.execQuery(query);
		
		try {
			while(con.rs.next()) {
				System.out.println("Masuk");
				int UserID = con.rs.getInt("UserID");
				String UserName = con.rs.getString("UserName");
				String UserEmail = con.rs.getString("UserEmail");
				String UserPassword = con.rs.getString("UserPassword");
				String UserRole = con.rs.getString("UserRole");
				users.add(new User(UserID, UserName, UserEmail, UserPassword, UserRole));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	//
	public static User authenticateUser(String UserEmail, String UserPassword) {
		String query = String.format("SELECT * FROM users WHERE `UserEmail`= '%s' AND `UserPassword` = '%s'", UserEmail, UserPassword);
        Connect con = Connect.getInstance();
        con.execQuery(query);
        
        try {
			if(con.rs.next()) {
				System.out.println("Masuk");
				int UserID = con.rs.getInt("UserID");
				String UserName = con.rs.getString("UserName");
				String UserRole = con.rs.getString("UserRole");
				return new User(UserID, UserName, UserEmail, UserPassword, UserRole);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return null;
	}
	
	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		this.UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		this.UserName = userName;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		this.UserEmail = userEmail;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		this.UserPassword = userPassword;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		this.UserRole = userRole;
	}
}
