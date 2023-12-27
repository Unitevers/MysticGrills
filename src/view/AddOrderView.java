package view;

import java.util.ArrayList;

import controller.MenuItemController;
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

public class AddOrderView {
	private TableView<MenuItem> menuTable = new TableView<>();
	private TextField nameInput = new TextField();
	private TextField quantityInput = new TextField();
	private Button addButton = new Button("Add Order");
	
	private void loadMenu(TableView<MenuItem> table) {
		ArrayList<MenuItem> menus = MenuItemController.getAllMenuItems();
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
		
		form.add(new Label("Menu Name: "), 0, 1);
		nameInput.setDisable(true);
		form.add(nameInput, 1, 1);
		form.add(new Label("Quantity: "), 0, 2);
		form.add(quantityInput, 1, 2);
		form.add(addButton, 0, 4);
		
		return form;
	}
	
	public AddOrderView(Stage stage) {
		VBox root = new VBox();
		NavView nav = new NavView();
		VBox.setMargin(createMenuForm(menuTable), new Insets(20));
        root.getChildren().addAll(nav.loadNavBar(stage) ,createMenuTable(menuTable), createMenuForm(menuTable));

        Scene addOrderScene = new Scene(root, 500, 500);

        stage.setTitle("Mystic Grills - Add Order");
        stage.setScene(addOrderScene);
        stage.show();	
	}
	
	public TableView<MenuItem> getMenuTable() {
		return menuTable;
	}

	public void setMenuTable(TableView<MenuItem> menuTable) {
		this.menuTable = menuTable;
	}

	public TextField getNameInput() {
		return nameInput;
	}

	public void setNameInput(TextField nameInput) {
		this.nameInput = nameInput;
	}

	public TextField getQuantityInput() {
		return quantityInput;
	}

	public void setQuantityInput(TextField quantityInput) {
		this.quantityInput = quantityInput;
	}

	public Button getAddButton() {
		return addButton;
	}

	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}
}
