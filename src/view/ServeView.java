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
//import model.MenuItem;
import model.Order;

public class ServeView {
	private TableView<Order> table = new TableView<>();
	private TextField orderIdInput = new TextField();
	private TextField userIdInput = new TextField();
	private TextField statusInput = new TextField();
	private TextField totalInput = new TextField();
	private Button delBut, serveBtn;

	private TableView<Order> createOrderTable(){
		TableColumn<Order, Number> orderIdColumn = new TableColumn<>("Order ID");
		orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));

		TableColumn<Order, Number> userIdColumn = new TableColumn<>("User ID");
		userIdColumn.setCellValueFactory(new PropertyValueFactory<>("UserID"));

		TableColumn<Order, String> statusColumn = new TableColumn<>("Order Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("OrderStatus"));

		TableColumn<Order, String> totalColumn = new TableColumn<>("Order Total");
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("OrderTotal"));

		table.getColumns().add(orderIdColumn);
		table.getColumns().add(userIdColumn);
		table.getColumns().add(statusColumn);
		table.getColumns().add(totalColumn);

		return table;
	}

	private GridPane OrderForm(TableView<Order> table) {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);
    
		delBut = new Button("Delete");
		serveBtn = new Button("Serve");
    
		form.add(new Label("Order ID:"), 0, 0);
		form.add(orderIdInput, 1, 0);
		form.add(delBut, 0, 1);
		
//		Add Button prepare untuk role waiter
		form.add(serveBtn, 1, 1);
    
		return form;
	}

	public ServeView(Stage stage) {
		VBox root = new VBox();
		NavView nav = new NavView();
		table = createOrderTable();
		GridPane form = OrderForm(table);
		VBox.setMargin(form, new Insets(20));
		root.getChildren().addAll(nav.loadNavBar(stage), table, form);  
		Scene scene = new Scene(root, 800, 600); 
		stage.setScene(scene);
		stage.setTitle("Serve");
		stage.show();
	}

	public TableView<Order> getTable() {
		return table;
	}

	public void setTable(TableView<Order> table) {
		this.table = table;
	}

	public TextField getOrderIdInput() {
		return orderIdInput;
	}

	public void setOrderIdInput(TextField orderIdInput) {
		this.orderIdInput = orderIdInput;
	}

	public TextField getUserIdInput() {
		return userIdInput;
	}

	public void setUserIdInput(TextField userIdInput) {
		this.userIdInput = userIdInput;
	}

	public TextField getStatusInput() {
		return statusInput;
	}

	public void setStatusInput(TextField statusInput) {
		this.statusInput = statusInput;
	}

	public TextField getTotalInput() {
		return totalInput;
	}

	public void setTotalInput(TextField totalInput) {
		this.totalInput = totalInput;
	}

	public Button getDelBut() {
		return delBut;
	}

	public void setDelBut(Button delBut) {
		this.delBut = delBut;
	}

	public Button getServeBtn() {
		return serveBtn;
	}

	public void setServeBtn(Button serveBtn) {
		this.serveBtn = serveBtn;
	}
}
