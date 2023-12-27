package controller;

import javafx.stage.Stage;
import model.User;
import view.LoginView;
import view.MenuItemView;
import view.RegisterView;

public class LoginViewController {
	 LoginView login;
	 public static User curruser;
	 
	 public LoginViewController(LoginView login, Stage primaryStage) {
	  this.login = login;
	  
	  initializeButtonHandlerLogin(primaryStage);
	 }
	 
	 private void initializeButtonHandlerLogin(Stage primaryStage) {
	        //untuk button kembali ke register view
	        login.getRegisterButton().setOnAction(e -> {
	        	RegisterView register = new RegisterView(primaryStage);
	        	RegisterViewController br = new RegisterViewController(register, primaryStage);
	        });
	        
	        //untuk memencet button login kedalam system
	        login.getButton().setOnAction(e->{
	        	MainController mc = new MainController();
	        	UserController uc = new UserController();
	        	String emailString = login.getEmailField().getText();
	        	String passwordString= login.getPasswordField().getText();
	        	if(mc.LoginEmailValidation(emailString)) {
	        		if (mc.LoginPasswordValidation(passwordString)) {
	        			if(uc.UserLogin(emailString, passwordString)) {
	        				curruser = UserController.authenticateUser(emailString, passwordString);	        				
	        				MenuItemView miView = new MenuItemView(primaryStage);
	        				MenuItemManagementViewController menuItemController = new MenuItemManagementViewController(miView);
//	        				System.out.println(userRole);
	      
	        			}
	        		}
	        	};
	   
	        });
	 }
}
