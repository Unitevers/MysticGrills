package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Connect;
import model.MenuItem;

public class MenuItemController {
	//untuk memvalidasi parameter
	public static void createMenuItem(String menuItemName, String menuItemDescription, int menuItemPrice) {
		if(menuItemName.isBlank() || menuItemDescription.isBlank() || menuItemPrice == 0) {
			Alert alert = new Alert(AlertType.INFORMATION, "Name, Description, and Price must not be empty!");
	    	alert.show();
		}
		else {
			MenuItem.createMenuItem(menuItemName, menuItemDescription, menuItemPrice);;
		}
	}
	
	//untuk memvalidasi parameter
	public static void updateMenuItem(int menuItemId, String menuItemName, String menuItemDescription, int menuItemPrice) {
		if(menuItemId == 0 || menuItemName.isBlank() || menuItemDescription.isBlank() || menuItemPrice == 0) {
			Alert alert = new Alert(AlertType.INFORMATION, "Id, Name, Description, and Price must not be empty!");
	    	alert.show();
		}
		else {
			MenuItem.updateMenuItem(menuItemId, menuItemName, menuItemDescription, menuItemPrice);;
		}
	}
	
	//untuk memvalidasi parameter
	public static void deleteMenuItem(int menuItemId) {
		String query = String.format("SELECT * FROM menuitems WHERE MenuItemID = '%d'", menuItemId);
        Connect con = Connect.getInstance();
        con.execQuery(query);
		try {
			if(con.rs.next()) {
				MenuItem.deleteMenuItem(menuItemId);
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION, "Menu Item ID does not exits!");
				alert.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//untuk memvalidasi parameter
	public static void getMenuItemById(int menuItemId) {
		String query = String.format("SELECT * FROM menuitems WHERE MenuItemID = '%d'", menuItemId);
        Connect con = Connect.getInstance();
        con.execQuery(query);
		try {
			if(con.rs.next()) {
				MenuItem.getMenuItemById(menuItemId);
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION, "Menu Item ID does not exits!");
				alert.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//untuk menyambungkan controller dengan model
	public static ArrayList<MenuItem> getAllMenuItems() {
		return MenuItem.getAllMenuItems();
	}
}
