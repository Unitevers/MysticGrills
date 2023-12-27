package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {
	private TextField usernameField;
	private TextField emailField;
	private PasswordField passwordField;
	private PasswordField confirmField;
	private Button button;
	private Button loginButton;
	
	public RegisterView(Stage primaryStage) {
		BorderPane borderPane= new BorderPane();
		Scene scene = new Scene(borderPane,600,400);
		usernameField = new TextField();
		passwordField = new PasswordField();
		emailField = new TextField();
		confirmField = new PasswordField();
		button = new Button("Register");
		loginButton = new Button("<Login");
		
		usernameField.setPromptText("Username");
		passwordField.setPromptText("Password");
		emailField.setPromptText("Email");
		confirmField.setPromptText("Confirmation");
		
		usernameField.setMinWidth(200);
		emailField.setMinWidth(200);
		passwordField.setMinWidth(200);
		confirmField.setMinWidth(200);
		button.setMinWidth(100);
		loginButton.setMinWidth(100);
		
		usernameField.setMinHeight(35);
		emailField.setMinHeight(35);
		passwordField.setMinHeight(35);
		confirmField.setMinHeight(35);
		button.setMinHeight(35);
		loginButton.setMinHeight(35);
		
		button.setAlignment(Pos.CENTER);
		
		VBox vBox = new VBox(20);
		
		vBox.getChildren().addAll(usernameField,emailField,passwordField,confirmField,button);
		vBox.setPadding(new Insets(50, 60, 60, 60));
		borderPane.setCenter(vBox);
		borderPane.setTop(loginButton);
		
		primaryStage.setTitle("MysticGrills - Register");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public TextField getUsernameField() {
		return usernameField;
	}

	public void setUsernameField(TextField usernameField) {
		this.usernameField = usernameField;
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

	public PasswordField getConfirmField() {
		return confirmField;
	}

	public void setConfirmField(PasswordField confirmField) {
		this.confirmField = confirmField;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public Button getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(Button loginButton) {
		this.loginButton = loginButton;
	}
}
