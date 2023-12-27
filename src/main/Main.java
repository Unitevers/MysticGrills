package main;

import controller.RegisterViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.RegisterView;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {

		RegisterView regView = new RegisterView(primaryStage);
    	RegisterViewController regController = new RegisterViewController(regView, primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
