package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class AdminView {
	private TableView<User> table;
	private TextField idInput = new TextField();
	private TextField nameInput = new TextField();
	private TextField emailInput = new TextField();
	private TextField passwordInput = new TextField();
	private TextField roleInput = new TextField();
//	private Button addBut;
	private Button updBut, delBut;

	private TableView<User> createUserTable() {
		TableView<User> table = new TableView<>();
		TableColumn<User, Number> idColumn = new TableColumn<>("User ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

		TableColumn<User, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("UserName"));

		TableColumn<User, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("UserEmail"));

		TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("UserPassword"));
		
		TableColumn<User, String> roleColumn = new TableColumn<>("Role");
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("UserRole"));

		table.getColumns().add(idColumn);
		table.getColumns().add(nameColumn);
		table.getColumns().add(emailColumn);
		table.getColumns().add(passwordColumn);
		table.getColumns().add(roleColumn);

		return table;
	}
	
	private GridPane AdminForm(TableView<User> table) {
        GridPane form = new GridPane();
        form.setVgap(20);
        form.setHgap(10);
        
//        addBut = new Button("Add");
        delBut = new Button("Delete");
        updBut = new Button("Update");
        
        form.add(new Label("User ID:"), 0, 0);
//        idInput.setDisable(true);
        form.add(idInput, 1, 0);
      	form.add(new Label("Name:"), 0, 1);
      	form.add(nameInput, 1, 1);
      	form.add(new Label("Email:"), 0, 2);
      	form.add(emailInput, 1, 2);
      	form.add(new Label("Password:"), 0, 3);
      	form.add(passwordInput, 1, 3);
      	form.add(new Label("Role:"), 0, 4);
      	form.add(roleInput, 1, 4);
//        form.add(addBut, 0, 5);
        form.add(delBut, 0, 5);
        form.add(updBut, 1, 5);
        
        return form;
    }
	
	public AdminView(Stage stage) {
		VBox root = new VBox(); 
		NavView nav = new NavView();
        table = createUserTable();
        GridPane form = AdminForm(table);
        VBox.setMargin(form, new Insets(20));
        root.getChildren().addAll(nav.loadNavBar(stage), table, form);  
        Scene scene = new Scene(root, 800, 600); 
        stage.setScene(scene);
        stage.setTitle("User Management");
        stage.show();
	}

	public TableView<User> getTable() {
		return table;
	}   

	public void setTable(TableView<User> table) {
		this.table = table;
	}

	public TextField getIdInput() {
		return idInput;
	}

	public void setIdInput(TextField idInput) {
		this.idInput = idInput;
	}

	public TextField getNameInput() {
		return nameInput;
	}

	public void setNameInput(TextField nameInput) {
		this.nameInput = nameInput;
	}

	public TextField getEmailInput() {
		return emailInput;
	}

	public void setEmailInput(TextField emailInput) {
		this.emailInput = emailInput;
	}

	public TextField getPasswordInput() {
		return passwordInput;
	}

	public void setPasswordInput(TextField passwordInput) {
		this.passwordInput = passwordInput;
	}

	public TextField getRoleInput() {
		return roleInput;
	}

	public void setRoleInput(TextField roleInput) {
		this.roleInput = roleInput;
	}

//	public Button getAddBut() {
//		return addBut;
//	}
//
//	public void setAddBut(Button addBut) {
//		this.addBut = addBut;
//	}

	public Button getUpdBut() {
		return updBut;
	}

	public void setUpdBut(Button updBut) {
		this.updBut = updBut;
	}

	public Button getDelBut() {
		return delBut;
	}

	public void setDelBut(Button delBut) {
		this.delBut = delBut;
	}

}
