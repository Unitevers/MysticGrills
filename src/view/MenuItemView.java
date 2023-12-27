package view;

import java.util.ArrayList;

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
import model.MenuItem;

public class MenuItemView {
	private TableView<MenuItem> menuTable = new TableView<>();
	private TextField idInput = new TextField();
	private TextField nameInput = new TextField();
	private TextField descriptionInput = new TextField();
	private TextField priceInput = new TextField();
	private Button addButton = new Button("Add");
	private Button deleteButton = new Button("Delete");
	private Button updateButton = new Button("Update");
	
	private void loadMenu(TableView<MenuItem> table) {
		ArrayList<MenuItem> menus = MenuItem.getAllMenuItems();
		table.getItems().setAll(menus);
	}
	
	private TableView<MenuItem> createMenuTable(TableView<MenuItem> table) {
		TableColumn<MenuItem, Number> menuIdColumn = new TableColumn<>("Menu Item Id");
		menuIdColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemID"));
		
		TableColumn<MenuItem, String> menuNameColumn = new TableColumn<>("Menu Item Name");
		menuNameColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemName"));
		
		TableColumn<MenuItem, String> menuDescriptionColumn = new TableColumn<>("Menu Item Description");
		menuDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemDescription"));
		
		TableColumn<MenuItem, Number> menuPriceColumn = new TableColumn<>("Menu Item Price");
		menuPriceColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemPrice"));
		
		table.getColumns().add(menuIdColumn);
		table.getColumns().add(menuNameColumn);
		table.getColumns().add(menuDescriptionColumn);
		table.getColumns().add(menuPriceColumn);
		loadMenu(table);
		
		return table;
	}
	
	private GridPane createMenuForm(TableView<MenuItem> table) {
		GridPane form = new GridPane();
		
		form.add(new Label("Menu Id (For Delete & Update): "), 0, 0);
		form.add(idInput, 1, 0);
		form.add(new Label("Menu Name: "), 0, 1);
		form.add(nameInput, 1, 1);
		form.add(new Label("Description: "), 0, 2);
		form.add(descriptionInput, 1, 2);
		form.add(new Label("Price: "), 0, 3);
		form.add(priceInput, 1, 3);
		form.add(addButton, 0, 4);
		form.add(updateButton, 0, 5);
		form.add(deleteButton, 0, 6);
		
		return form;
	}
	
	public MenuItemView(Stage menuStage) {
		VBox root = new VBox();
		NavView nav = new NavView();
		VBox.setMargin(createMenuForm(menuTable), new Insets(20));
		
        root.getChildren().addAll(nav.loadNavBar(menuStage) ,createMenuTable(menuTable), createMenuForm(menuTable));

        Scene menuScene = new Scene(root, 500, 500);

        menuStage.setTitle("Mystic Grills Menu Page");
        menuStage.setScene(menuScene);
        menuStage.show();	
	}
	
	public TableView<MenuItem> getMenuTable() {
		return menuTable;
	}

	public void setMenuTable(TableView<MenuItem> menuTable) {
		this.menuTable = menuTable;
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

	public TextField getPriceInput() {
		return priceInput;
	}

	public void setPriceInput(TextField priceInput) {
		this.priceInput = priceInput;
	}
	
	public TextField getDescriptionInput() {
		return descriptionInput;
	}

	public void setDescriptionInput(TextField descriptionInput) {
		this.descriptionInput = descriptionInput;
	}

	public Button getAddButton() {
		return addButton;
	}

	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public Button getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(Button updateButton) {
		this.updateButton = updateButton;
	}
}
