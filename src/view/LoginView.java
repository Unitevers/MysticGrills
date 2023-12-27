package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
	private TextField emailField;
	private PasswordField passwordField;
	private Button button;
	private Button registerButton;
	
	    
	public LoginView(Stage primaryStage) {
	        // TODO Auto-generated constructor stub
	        BorderPane borderPane= new BorderPane();
	        Scene scene = new Scene(borderPane,600,400);
	        
	        emailField = new TextField();
	        passwordField = new PasswordField();
	        button = new Button("Login");
	        registerButton = new Button("Register");
	        
	        passwordField.setPromptText("Password");
	        emailField.setPromptText("Email");
	        
	        emailField.setMinWidth(200);
	        passwordField.setMinWidth(200);
	        button.setMinWidth(100);
	        registerButton.setMinWidth(100);
	        
	        emailField.setMinHeight(35);
	        passwordField.setMinHeight(35);
	        button.setMinHeight(35);
	        registerButton.setMinHeight(35);
	        
	        VBox vBox = new VBox(20);
	        
	        vBox.getChildren().addAll(emailField,passwordField,button);
	        
	        borderPane.setCenter(vBox);
	        borderPane.setTop(registerButton);
	        vBox.setPadding(new Insets(50, 60, 60, 60));
	        
			primaryStage.setTitle("MysticGrills - Login");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	    }


	    public TextField getEmailField() {
	        return emailField;
	    }


	    public void setEmailField(TextField emailField) {
	        this.emailField = emailField;
	    }


	    public PasswordField getPasswordField() {
	        return passwordField;
	    }


	    public void setPasswordField(PasswordField passwordField) {
	        this.passwordField = passwordField;
	    }


	    public Button getButton() {
	        return button;
	    }


	    public void setButton(Button button) {
	        this.button = button;
	    }


	    public Button getRegisterButton() {
	        return registerButton;
	    }


	    public void setRegisterButton(Button registerButton) {
	        this.registerButton = registerButton;
	    }
}
