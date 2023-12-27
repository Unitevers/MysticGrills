package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import model.Order;
import model.User;
import view.OrderView;

public class OrderViewController {
	OrderView ov;
	
	//untuk fungsi button
	private void initializeButtonHandler() {
		ov.getPayButton().setOnAction(e ->{
        	Integer orderId = Integer.parseInt(ov.getOrderIdInput().getText());
            String paymentType = ov.getTypeInput().getText();
            Integer paymentAmount = Integer.parseInt(ov.getAmountInput().getText());
            
            OrderController.getOrderByOrderId(orderId);
			Order.updateOrder(orderId, "Paid");
            ReceiptController.createReceipt(orderId, paymentType, paymentAmount, LocalDate.now());
        });
	}
	
	//untuk memunculkan data menu item
	private void loadOrderData() {
		ArrayList<Order> orders = OrderController.getAllOrders();
		ov.getOrderTable().getItems().setAll(orders);
	}
	
	//agar tabel bisa diklik dan dapat mengambil value dalam kolom
	private void setupTableSelectionListener() {
		ov.getOrderTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			if(newSelection != null) {
        		ov.getOrderIdInput().setText(String.valueOf(newSelection.getOrderID()));

				Integer orderId = newSelection.getOrderID();
				Integer userId = newSelection.getUserID();
				String orderStatus = newSelection.getOrderStatus();
				LocalDate orderDate = newSelection.getOrderDate();
				Integer orderTotal = newSelection.getOrderTotal();
				
				new Order(orderId, User.getUserById(userId), orderStatus, orderDate, orderTotal);
				
				ov.selectedOrder.setText("Order Detail: " + orderId + " - " + userId + " - " + orderStatus + " - " + orderDate + " - " + orderTotal);
			}
		});
	}
	
	//untuk menyambungkan controller dan view
	public OrderViewController(OrderView ov) {
		this.ov = ov;
		
		initializeButtonHandler();
		setupTableSelectionListener();
		loadOrderData();
	}
}
