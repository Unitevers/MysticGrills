package controller;

import javafx.stage.Stage;
import model.User;
import view.LoginView;
import view.RegisterView;

public class RegisterViewController {
	RegisterView register;
    // button untuk kembali ke register view
    public RegisterViewController(RegisterView register, Stage primaryStage) {
        this.register = register;

        initializeButtonHandlerLogin(primaryStage);
    }
    //button untuk bisa register akun
    private void initializeButtonHandlerLogin(Stage primaryStage) {
        register.getButton().setOnAction(e -> {
            MainController mc = new MainController();
            String usernameString = register.getUsernameField().getText();
            String emailString = register.getEmailField().getText();
            String passwordString = register.getPasswordField().getText();
            String confirmString = register.getConfirmField().getText();
            boolean tof = mc.usernamevalidation(usernameString);

            if(tof) {
                 boolean tof2 = mc.emailvalidation(emailString);
                 if(tof2) {
                     boolean tof3 = mc.passwordvalidation(passwordString, confirmString);
                     if(tof3) {
                         User.createUser("Customer", usernameString, emailString, passwordString);
                         LoginView login = new LoginView(primaryStage);
                         LoginViewController loginController = new LoginViewController(login, primaryStage);
                     }
                 }
            }
        });

        register.getLoginButton().setOnAction(e -> {
             LoginView login = new LoginView(primaryStage);
             LoginViewController buttonLogin = new LoginViewController(login, primaryStage);

        });
    }
}
