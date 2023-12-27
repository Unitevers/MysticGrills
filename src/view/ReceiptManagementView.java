package view;

import java.util.ArrayList;

import controller.ReceiptController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.OrderItem;
import model.Receipt;

public class ReceiptManagementView {
	private TableView<Receipt> receiptTable = new TableView<>();
	private TableView<OrderItem> oiTable = new TableView<>();
	
	private void loadOrder(TableView<Receipt> table) {
		ArrayList<Receipt> receipts = ReceiptController.getAllReceipts();;
		table.getItems().setAll(receipts);
	}

	private TableView<Receipt> createReceiptTable(TableView<Receipt> table){
		TableColumn<Receipt, Number> idColumn = new TableColumn<>("Receipt ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ReceiptID"));

        TableColumn<Receipt, Number> orderColumn = new TableColumn<>("Order ID");
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));

        TableColumn<Receipt, Number> amountColumn = new TableColumn<>("Receipt Payment Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("ReceiptPaymentAmount"));

        TableColumn<Receipt, String> dateColumn = new TableColumn<>("Receipt Payment Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("ReceiptPaymentDate"));

        TableColumn<Receipt, String> typeColumn = new TableColumn<>("Receipt Payment Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("ReceiptPaymentType"));

		table.getColumns().add(idColumn);
		table.getColumns().add(orderColumn);
		table.getColumns().add(amountColumn);
		table.getColumns().add(dateColumn);
		table.getColumns().add(typeColumn);
		loadOrder(table);

		return table;
	}
	
	private TableView<OrderItem> createOrderItemTable(TableView<OrderItem> table) {
		TableColumn<OrderItem, Number> orderIdColumn = new TableColumn<>("Order Id");
		orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
		
		TableColumn<OrderItem, String> menuIdColumn = new TableColumn<>("Menu Id");
		menuIdColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemID"));
		
		TableColumn<OrderItem, Number> orderQuantityColumn = new TableColumn<>("Order Quantity");
		orderQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("OrderQuantity"));
		
		table.getColumns().add(orderIdColumn);
		table.getColumns().add(menuIdColumn);
		table.getColumns().add(orderQuantityColumn);
		
		return table;
	}

	private GridPane createReceiptForm(TableView<Receipt> table) {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);

		form.add(new Label("Receipt Detail:"), 1, 0);
		return form;
	}

	public ReceiptManagementView(Stage stage) {
		VBox root = new VBox();
		NavView nav = new NavView();
		VBox.setMargin(createReceiptForm(receiptTable), new Insets(20));
        root.getChildren().addAll(nav.loadNavBar(stage), createReceiptTable(receiptTable), createReceiptForm(receiptTable), createOrderItemTable(oiTable));

        Scene addOrderScene = new Scene(root, 500, 500);

        stage.setTitle("Mystic Grills - Receipt Management");
        stage.setScene(addOrderScene);
        stage.show();	
	}

	
	public TableView<Receipt> getReceiptTable() {
		return receiptTable;
	}

	public void setReceiptTable(TableView<Receipt> receiptTable) {
		this.receiptTable = receiptTable;
	}

	public TableView<OrderItem> getOiTable() {
		return oiTable;
	}

	public void setOiTable(TableView<OrderItem> oiTable) {
		this.oiTable = oiTable;
	}

}
