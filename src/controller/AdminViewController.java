package controller;

import java.util.ArrayList;

import model.User;
import view.AdminView;

public class AdminViewController {
AdminView adminView;
	
	// buat fungsi button delete dan update
	private void initializeButtonHandler() {
        
        adminView.getDelBut().setOnAction(e -> {
        	Integer id = Integer.parseInt(adminView.getIdInput().getText());
        	
            UserController.deleteUser(id);
            loadUserData();
        });
       
        adminView.getUpdBut().setOnAction(e -> {
        	Integer id = Integer.parseInt(adminView.getIdInput().getText());
            String name = adminView.getNameInput().getText();
            String email = adminView.getEmailInput().getText();
            String password = adminView.getPasswordInput().getText();
            String role = adminView.getRoleInput().getText();
            
            UserController.updateUser(id, role, name, email, password);
            loadUserData();
        });
	}
	
	//untuk memunculkan data user
	private void loadUserData() {
		ArrayList<User> users = UserController.getAllUsers();
		adminView.getTable().getItems().setAll(users);
	}
	
	//agar bisa klik tabel dan ngambil value dari kolom
	private void setupTableSelectionListener() {
        adminView.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
        	if(newSelection != null) {
        		adminView.getIdInput().setText(String.valueOf(newSelection.getUserID()));
                adminView.getNameInput().setText(newSelection.getUserName());
                adminView.getEmailInput().setText(newSelection.getUserEmail());
                adminView.getPasswordInput().setText(newSelection.getUserPassword());
                adminView.getRoleInput().setText(newSelection.getUserRole());
        	}
        });
    }
	
	//untuk menyambungkan controller dan view
	public AdminViewController(AdminView adminView) {
		this.adminView = adminView;
			
		initializeButtonHandler();
		setupTableSelectionListener();
		loadUserData();
	}
}
