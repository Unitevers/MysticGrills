package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
	private int OrderID;
	private int UserID;
	private User OrderUser;
	private String OrderStatus;
	private LocalDate OrderDate;
	private int OrderTotal;
	
	
	public Order(int orderID, User orderUser,  String orderStatus, LocalDate orderDate, int orderTotal) {
		super();
		this.OrderID = orderID;
		this.OrderUser = orderUser;
		this.UserID = orderUser.getUserID();
		this.OrderStatus = orderStatus;
		this.OrderDate = orderDate;
		this.OrderTotal = orderTotal;
	}
	
	public static void createOrder(User orderUser, LocalDate orderDate) {
		final String orderStatus = "Pending";
		int orderTotal = 0;
		String query = String.format("INSERT INTO orders(UserID, OrderStatus, OrderDate, OrderTotal) VALUES (\'%d\', \"%s\", \"%s\", \'%d\')", orderUser.getUserID(), orderStatus, orderDate, orderTotal); 
		Connect con = Connect.getInstance();
		con.execUpdate(query);	
	}
	
	public static Order getLastOrder() {
		String query = "SELECT * FROM orders ORDER BY OrderID DESC LIMIT 1";
		Connect con = Connect.getInstance();
        con.rs = con.execQuery(query);
        
        try {
			if(con.rs.next()) {
				System.out.println("Masuk");
				int OrderID = con.rs.getInt("OrderID");
				String OrderStatus = con.rs.getString("OrderStatus");
				LocalDate OrderDate = con.rs.getDate("OrderDate").toLocalDate();
				int OrderTotal = con.rs.getInt("OrderTotal");
				int UserID = con.rs.getInt("UserID");
				User orderUser = User.getUserById(UserID);
				return new Order(OrderID, orderUser, OrderStatus, OrderDate, OrderTotal);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return null;
	}
	
	public static void calculateOrderTotal(ArrayList<OrderItem> orderitems) {
		int orderTotal = 0;
		int orderId = 0;
		for(OrderItem item: orderitems) {
			orderTotal += item.getMenuitem().getMenuItemPrice() * item.getOrderQuantity();
			orderId = item.getOrderID();
		}
		String query = String.format("UPDATE orders SET `OrderTotal` = \'%d\' WHERE `OrderID` = \'%d\' ", orderTotal, orderId); 
		Connect con = Connect.getInstance();
		con.execUpdate(query);
	}
	
	public static void updateOrder(int orderID, String orderStatus) {
		String query = String.format("UPDATE orders SET `OrderStatus`= \'%s\' WHERE `OrderID` = \'%d\' ", orderStatus, orderID);
        Connect con = Connect.getInstance();
        con.execUpdate(query);
	}
	
	public static void deleteOrder(int orderID) {
		String query = String.format("DELETE FROM `orders` WHERE `OrderID` = '%d'", orderID);
        Connect con = Connect.getInstance();
        con.execUpdate(query);
	}
	
	public static ArrayList<Order> getOrdersByCustomerId(int userID) {
		ArrayList<Order> orders = new ArrayList<>();
		Connect con = Connect.getInstance();
		
		String query = String.format("SELECT * FROM orders WHERE `UserID` = '%d'", userID);
		
		con.rs = con.execQuery(query);
		
		try {
			while(con.rs.next()) {
				System.out.println("Masuk");
				int OrderID = con.rs.getInt("OrderID");
				String OrderStatus = con.rs.getString("OrderStatus");
				LocalDate OrderDate = con.rs.getDate("OrderDate").toLocalDate();
				int OrderTotal = con.rs.getInt("OrderTotal");
				int UserID = con.rs.getInt("UserID");
				User OrderUser= User.getUserById(UserID);
				orders.add(new Order(OrderID, OrderUser, OrderStatus, OrderDate, OrderTotal));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public static ArrayList<Order> getAllOrders() {
		ArrayList<Order> orders = new ArrayList<>();
		Connect con = Connect.getInstance();
		
		String query = "SELECT * FROM orders";
		
		con.rs = con.execQuery(query);
		
		try {
			while(con.rs.next()) {
				System.out.println("Masuk");
				int OrderID = con.rs.getInt("OrderID");
				String OrderStatus = con.rs.getString("OrderStatus");
				LocalDate OrderDate = con.rs.getDate("OrderDate").toLocalDate();
				int OrderTotal = con.rs.getInt("OrderTotal");
				int UserID = con.rs.getInt("UserID");
				User OrderUser = User.getUserById(UserID);
				orders.add(new Order(OrderID, OrderUser, OrderStatus, OrderDate, OrderTotal));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public static Order getOrderByOrderId(int orderId) {
		Connect con = Connect.getInstance();
		String query = String.format("SELECT * FROM orders WHERE `OrderID` = %d", orderId);
		con.rs = con.execQuery(query);
		
		try {
			while(con.rs.next()) {
				System.out.println("Masuk");
				int OrderID = con.rs.getInt("OrderID");
				String OrderStatus = con.rs.getString("OrderStatus");
				LocalDate OrderDate = con.rs.getDate("OrderDate").toLocalDate();
				int OrderTotal = con.rs.getInt("OrderTotal");
				int UserID = con.rs.getInt("UserID");
				User OrderUser = User.getUserById(UserID);
				return new Order(OrderID, OrderUser, OrderStatus, OrderDate, OrderTotal);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
		
	//getter dan setter attribute model
	public int getOrderID() {
		return OrderID;
	}
	
	public void setOrderID(int orderID) {
		this.OrderID = orderID;
	}
	
	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getOrderStatus() {
		return OrderStatus;
	}
	
	public void setOrderStatus(String orderStatus) {
		this.OrderStatus = orderStatus;
	}
	
	public LocalDate getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		OrderDate = orderDate;
	}

	public int getOrderTotal() {
		return OrderTotal;
	}
	
	public void setOrderTotal(int orderTotal) {
		this.OrderTotal = orderTotal;
	}
	
}
