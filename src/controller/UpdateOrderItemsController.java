 package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;
import view.UpdateOrderItemsView;

public class UpdateOrderItemsController {
	UpdateOrderItemsView udpoiv;
	public Order selectedOrder;
	public OrderItem selectedOrderItem;
	private ArrayList<OrderItem> orderitems = new ArrayList<>();
	
	//untuk fungsi button
	private void initializeButtonHandler() {
		udpoiv.getUpdButton().setOnAction(e -> {
			Integer quantity = Integer.parseInt(udpoiv.getQuantityInput().getText());
			MenuItem menuitem = selectedOrderItem.getMenuitem();
			
			orderitems = OrderItemController.getAllOrderItemsByOrderId(selectedOrder.getOrderID());
			OrderItemController.updateOrderItem(selectedOrder.getOrderID(), menuitem, quantity);
			
			for(OrderItem item: orderitems) {
				if(item.getOrderQuantity() == 0) {
					orderitems.remove(item);
				}
			}

			OrderController.calculateOrderTotal(orderitems);
			loadOrderData();
		});
	}
	
	//untuk memunculkan data order
	private void loadOrderData() {
		System.out.println(LoginViewController.curruser.getUserID());
		ArrayList<Order> orders = OrderController.getOrdersByCustomerId(LoginViewController.curruser.getUserID());
		udpoiv.getOrderTable().getItems().setAll(orders);
	}
	
	//untuk memunculkan data menu item
	private void loadOrderItemData() {
		if(selectedOrder == null) {
			ArrayList<OrderItem> orderitems = new ArrayList<>();
			udpoiv.getOiTable().getItems().setAll(orderitems);
		}
		else {
			ArrayList<OrderItem> orderitems = OrderItemController.getAllOrderItemsByOrderId(selectedOrder.getOrderID());
			udpoiv.getOiTable().getItems().setAll(orderitems);
		}
	}
	
	//agar tabel bisa diklik dan dapat mengambil value dalam kolom
	private void setupTableSelectionListener() {
		udpoiv.getOrderTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			if(newSelection != null) {
        		udpoiv.getOrderIdInput().setText(String.valueOf(newSelection.getOrderID()));

				Integer orderId = newSelection.getOrderID();
				Integer userId = newSelection.getUserID();
				String orderStatus = newSelection.getOrderStatus();
				LocalDate orderDate = newSelection.getOrderDate();
				Integer orderTotal = newSelection.getOrderTotal();
				
				selectedOrder = new Order(orderId, User.getUserById(userId), orderStatus, orderDate, orderTotal);
				loadOrderItemData();
			}
		});
		
		udpoiv.getOiTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			if(newSelection != null) {
        		udpoiv.getQuantityInput().setText(String.valueOf(newSelection.getOrderQuantity()));

				Integer orderId = newSelection.getOrderID();
				MenuItem menuItem = newSelection.getMenuitem();
				Integer quantity = newSelection.getOrderQuantity();
				
				selectedOrderItem = new OrderItem(orderId, menuItem, quantity);
			}
		});
	}
	
	//untuk menyambungkan controller dan view
	public UpdateOrderItemsController(UpdateOrderItemsView udpoiv) {
		this.udpoiv = udpoiv;
		
		initializeButtonHandler();
		setupTableSelectionListener();
		loadOrderItemData();
	}
}
