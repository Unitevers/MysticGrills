package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Connect;
import model.Order;
import model.OrderItem;
import model.User;

public class OrderController {
	//untuk menyambungkan controller dengan model
	public static void createOrder(User curruser, LocalDate orderDate) {
		Order.createOrder(curruser, orderDate);
	}
	
	public static Order getLastOrder() {
		return Order.getLastOrder();
	}
	
	//untuk memvalidasi
	public static void calculateOrderTotal(ArrayList<OrderItem> orderitems) {
		if(orderitems.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION, "Order Items must not be empty!");
	    	alert.show();
		}
		else {
			Order.calculateOrderTotal(orderitems);
		}
	}
	
	//untuk memvalidasi
	public static void updateOrder(int OrderID, String OrderStatus) {
		if(OrderID == 0 || OrderStatus.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION, "Id, Order Items, Status must not be empty!");
	    	alert.show();
		}
		else {
			Order.updateOrder(OrderID, OrderStatus);
		}
	}
	
	//untuk memvalidasi
	public static void deleteOrder(int OrderID) {
		String query = String.format("SELECT * FROM menuitems WHERE `OrderID` = '%d'", OrderID);
        Connect con = Connect.getInstance();
        con.execQuery(query);
		try {
			if(con.rs.next()) {
				Order.deleteOrder(OrderID);
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION, "Order ID does not exits!");
				alert.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//untuk memvalidasi
	public static ArrayList<Order> getOrdersByCustomerId(int CustomerID) {
		String query = String.format("SELECT * FROM orders WHERE `UserID` = '%d'", CustomerID);
        Connect con = Connect.getInstance();
        con.execQuery(query);
		try {
			if(con.rs.next()) {
				return Order.getOrdersByCustomerId(CustomerID);
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION, "Customer ID does not exits!");
				alert.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//untuk menyambungkan controller dengan model
	public static ArrayList<Order> getAllOrders() {
		return Order.getAllOrders();
	}
	
	//untuk memvalidasi
	public static Order getOrderByOrderId(int OrderID) {
		String query = String.format("SELECT * FROM orders WHERE `OrderID` = '%d'", OrderID);
        Connect con = Connect.getInstance();
        con.execQuery(query);
		try {
			if(con.rs.next()) {
				return Order.getOrderByOrderId(OrderID);
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION, "Order ID does not exits!");
				alert.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//update order status ke prepare
    public static void prepareOrder(int OrderID) {
    	//Validasi input id dan status
    	if(OrderID == 0) {
    		Alert alert = new Alert(AlertType.INFORMATION, "Id, Order Items, and status must not be empty!");
    		alert.show();
    	}
    	else {
    		Order.updateOrder(OrderID, "Prepared");
    	}
    }
  
    //update order status ke serve
    public static void serveOrder(int OrderID) {
    	//Validasi input id dan status
    	if(OrderID == 0) {
    		Alert alert = new Alert(AlertType.INFORMATION, "Id, Order Items, and status must not be empty!");
    		alert.show();
    	}
    	else {
    		Order.updateOrder(OrderID, "Served");
    	}
    }
}
