package view;

import java.util.ArrayList;

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

public class OrderView {
	private TableView<Order> orderTable = new TableView<>();
	private TextField orderIdInput = new TextField();
	private TextField typeInput = new TextField();
	private TextField amountInput = new TextField();
	private Button payButton = new Button("Pay");
	
	public Label selectedOrder = new Label("Order Detail: ");
	
	private void loadOrder(TableView<Order> table) {
		ArrayList<Order> orders = OrderController.getAllOrders();
		table.getItems().setAll(orders);
	}

	private TableView<Order> createOrderTable(TableView<Order> table){
		TableColumn<Order, Number> orderIdColumn = new TableColumn<>("Order ID");
		orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));

		TableColumn<Order, String> statusColumn = new TableColumn<>("Order Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("OrderStatus"));

		TableColumn<Order, Number> totalColumn = new TableColumn<>("Order Total");
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("OrderTotal"));

		table.getColumns().add(orderIdColumn);
		table.getColumns().add(statusColumn);
		table.getColumns().add(totalColumn);
		loadOrder(table);

		return table;
	}

	private GridPane createOrderForm(TableView<Order> table) {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);
		
		form.add(selectedOrder, 0, 0);
		form.add(new Label("Order ID:"), 0, 1);
		form.add(orderIdInput, 1, 1);
		form.add(new Label("Payment Type:"), 0, 2);
		form.add(typeInput, 1, 2);
		form.add(new Label("Amount Input:"), 0, 3);
		form.add(amountInput, 1, 3);
		form.add(payButton, 1, 4);
		
		return form;
	}

	public OrderView(Stage stage) {
		VBox root = new VBox();
		NavView nav = new NavView();
		VBox.setMargin(createOrderForm(orderTable), new Insets(20));
        root.getChildren().addAll(nav.loadNavBar(stage), createOrderTable(orderTable), createOrderForm(orderTable));

        Scene addOrderScene = new Scene(root, 500, 500);

        stage.setTitle("Mystic Grills - Process Payment");
        stage.setScene(addOrderScene);
        stage.show();	
	}

	public TableView<Order> getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(TableView<Order> orderTable) {
		this.orderTable = orderTable;
	}

	public TextField getOrderIdInput() {
		return orderIdInput;
	}

	public void setOrderIdInput(TextField orderIdInput) {
		this.orderIdInput = orderIdInput;
	}

	public TextField getTypeInput() {
		return typeInput;
	}

	public void setTypeInput(TextField typeInput) {
		this.typeInput = typeInput;
	}

	public TextField getAmountInput() {
		return amountInput;
	}

	public void setAmountInput(TextField amountInput) {
		this.amountInput = amountInput;
	}

	public Label getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(Label selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public Button getPayButton() {
		return payButton;
	}

	public void setPayButton(Button payButton) {
		this.payButton = payButton;
	}
}
