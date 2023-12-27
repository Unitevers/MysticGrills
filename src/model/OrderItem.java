package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItem {
	private int OrderID;
	private MenuItem menuitem;
	private int MenuItemID;
	private int OrderQuantity;
	
	public OrderItem(int orderID, MenuItem menuitem, int quantity) {
		super();
		this.OrderID = orderID;
		this.MenuItemID = menuitem.getMenuItemID();
		this.menuitem = menuitem;
		this.OrderQuantity = quantity;
	}
	
	public static void createOrderItem(int orderId, MenuItem menuitem, int quantity) {
		String query = String.format("INSERT INTO orderitems(OrderID, MenuItemID, OrderQuantity) VALUES ('%d', '%d', '%d')", orderId, menuitem.getMenuItemID(), quantity); 
		Connect con = Connect.getInstance();
		con.execUpdate(query);
	}
	
	public static void updateOrderItem(int orderId, MenuItem menuitem, int quantity) {
	String query = String.format("UPDATE orderitems SET `MenuItemID` = '%d' , `OrderQuantity`= '%d' WHERE `OrderID` = '%d' ", menuitem.getMenuItemID(), quantity, orderId);
        Connect con = Connect.getInstance();
        con.execUpdate(query);
	}
	
	public static void deleteOrderItem(int orderId, MenuItem menuitem) {
		String query = String.format("DELETE FROM `orderitems` WHERE `OrderID` = '%d' AND `MenuItemID` = '%d'", orderId, menuitem.getMenuItemID());
        Connect con = Connect.getInstance();
        con.execUpdate(query);
	}
	
	public static ArrayList<OrderItem> getAllOrderItemsByOrderId(int orderId) {
		ArrayList<OrderItem> orderitems = new ArrayList<>();
		Connect con = Connect.getInstance();
		
		String query = String.format("SELECT * FROM orderitems WHERE `OrderID` = '%d'", orderId);
		
		con.rs = con.execQuery(query);
		
		try {
			while(con.rs.next()) {
				System.out.println("Masuk");
				int OrderID = con.rs.getInt("OrderID");
				int OrderQuantity = con.rs.getInt("OrderQuantity");
				int MenuItemID = con.rs.getInt("MenuItemID");
				MenuItem menuitem = MenuItem.getMenuItemById(MenuItemID);
				orderitems.add(new OrderItem(OrderID, menuitem, OrderQuantity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderitems;
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public MenuItem getMenuitem() {
		return menuitem;
	}

	public void setMenuitem(MenuItem menuitem) {
		this.menuitem = menuitem;
	}
	
	public int getMenuItemID() {
		return MenuItemID;
	}

	public void setMenuItemID(int menuItemID) {
		MenuItemID = menuItemID;
	}

	public int getOrderQuantity() {
		return OrderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		OrderQuantity = orderQuantity;
	}
}
