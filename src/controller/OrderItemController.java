package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Connect;
import model.MenuItem;
import model.OrderItem;

public class OrderItemController {
    //untuk memvalidasi
	public static void createOrderItem(int orderId, MenuItem menuitem, int quantity) {
		if(orderId == 0 || menuitem.getMenuItemID() == 0 || quantity == 0) {
			Alert alert = new Alert(AlertType.INFORMATION, "Order ID, Menu Item ID, and Quantity must not be empty!");
	    	alert.show();
		}
		else {
			OrderItem.createOrderItem(orderId, menuitem, quantity);
		}
	}
	
	//untuk memvalidasi
	public static void updateOrderItem(int orderId, MenuItem menuitem, int quantity) {
		if(orderId == 0 || menuitem.getMenuItemID() == 0 || quantity == 0) {
			Alert alert = new Alert(AlertType.INFORMATION, "Order ID, Menu Item ID, and Quantity must not be empty!");
	    	alert.show();
		}
		else {
			OrderItem.updateOrderItem(orderId, menuitem, quantity);
		}
	}
	
	//untuk memvalidasi
	public static void deleteOrderItem(int orderId, int menuItemId) {
		if(orderId == 0 || menuItemId == 0) {
			Alert alert = new Alert(AlertType.INFORMATION, "Order ID, Menu Item ID, and Quantity must not be empty!");
	    	alert.show();
		}
		else {
			OrderItem.deleteOrderItem(orderId, MenuItem.getMenuItemById(menuItemId));
		}
	}
	
	//untuk memvalidasi
	public static ArrayList<OrderItem> getAllOrderItemsByOrderId(int orderId) {
		String query = String.format("SELECT * FROM orderitems WHERE OrderID = '%d'", orderId);
        Connect con = Connect.getInstance();
        con.execQuery(query);
		try {
			if(con.rs.next()) {
				return OrderItem.getAllOrderItemsByOrderId(orderId);
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
}
