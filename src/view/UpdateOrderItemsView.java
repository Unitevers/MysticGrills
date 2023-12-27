package view;

import java.util.ArrayList;

import controller.LoginViewController;
import controller.OrderController;
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
import model.Order;
import model.OrderItem;

public class UpdateOrderItemsView {
	private TableView<Order> orderTable = new TableView<>();
	private TableView<OrderItem> oiTable = new TableView<>();
	private TextField OrderIdInput = new TextField();
	private TextField quantityInput = new TextField();
	private Button updButton = new Button("Update Order");
	
	private void loadOrder(TableView<Order> table) {
		ArrayList<Order> orders = OrderController.getOrdersByCustomerId(LoginViewController.curruser.getUserID());
		table.getItems().setAll(orders);
	}
	
	private TableView<Order> createOrderTable(TableView<Order> table) {
		TableColumn<Order, Number> orderIdColumn = new TableColumn<>("Order Id");
		orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
		
		TableColumn<Order, Number> userIdColumn = new TableColumn<>("User ID");
		userIdColumn.setCellValueFactory(new PropertyValueFactory<>("UserID"));
		
		TableColumn<Order, String> orderStatusColumn = new TableColumn<>("Order Status");
		orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("OrderStatus"));
		
		TableColumn<Order, String> orderDateColumn = new TableColumn<>("Order Date");
		orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
		
		TableColumn<Order, Number> orderTotalColumn = new TableColumn<>("Order Total");
		orderTotalColumn.setCellValueFactory(new PropertyValueFactory<>("OrderTotal"));
		
		table.getColumns().add(orderIdColumn);
		table.getColumns().add(userIdColumn);
		table.getColumns().add(orderStatusColumn);
		table.getColumns().add(orderDateColumn);
		table.getColumns().add(orderTotalColumn);
		loadOrder(table);
		
		return table;
	}
	
	private TableView<OrderItem> createOrderItemTable(TableView<OrderItem> table) {
		TableColumn<OrderItem, Number> orderIdColumn = new TableColumn<>("Order Id");
		orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
		
		TableColumn<OrderItem, Number> menuIdColumn = new TableColumn<>("Menu Id");
		menuIdColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemID"));
		
		TableColumn<OrderItem, Number> orderQuantityColumn = new TableColumn<>("Order Quantity");
		orderQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("OrderQuantity"));
		
		table.getColumns().add(orderIdColumn);
		table.getColumns().add(menuIdColumn);
		table.getColumns().add(orderQuantityColumn);
		
		return table;
	}
	
	private GridPane createOrderForm(TableView<Order> table) {
		GridPane form = new GridPane();
		
		form.add(new Label("Order ID  : "), 0, 1);
		form.add(OrderIdInput, 1, 1);
		form.add(new Label("Quantity: "), 0, 2);
		form.add(quantityInput, 1, 2);
		form.add(updButton, 0, 4);
		
		return form;
	}
	
	public UpdateOrderItemsView(Stage stage) {
		VBox root = new VBox();
		NavView nav = new NavView();
		VBox.setMargin(createOrderForm(orderTable), new Insets(20));
        root.getChildren().addAll(nav.loadNavBar(stage), createOrderTable(orderTable), createOrderForm(orderTable), createOrderItemTable(oiTable));

        Scene addOrderScene = new Scene(root, 500, 500);

        stage.setTitle("Mystic Grills - Update Order");
        stage.setScene(addOrderScene);
        stage.show();	
	}

	public TableView<Order> getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(TableView<Order> orderTable) {
		this.orderTable = orderTable;
	}
	
	public TableView<OrderItem> getOiTable() {
		return oiTable;
	}

	public void setOiTable(TableView<OrderItem> oiTable) {
		this.oiTable = oiTable;
	}

	public TextField getOrderIdInput() {
		return OrderIdInput;
	}

	public void setOrderIdInput(TextField orderIdInput) {
		OrderIdInput = orderIdInput;
	}

	public TextField getQuantityInput() {
		return quantityInput;
	}

	public void setQuantityInput(TextField quantityInput) {
		this.quantityInput = quantityInput;
	}

	public Button getUpdButton() {
		return updButton;
	}

	public void setUpdButton(Button updButton) {
		this.updButton = updButton;
	}
}
