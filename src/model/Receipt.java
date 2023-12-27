package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Receipt {
	private int ReceiptID;
	private int OrderID;
	private int receiptPaymentAmount;
	private LocalDate receiptPaymentDate;
	private String receiptPaymentType;

	public Receipt(int ReceiptID, int OrderID, int receiptPaymentAmount, LocalDate receiptPaymentDate, String receiptPaymentType) {
		this.ReceiptID = ReceiptID;
		this.OrderID = OrderID;
		this.receiptPaymentAmount = receiptPaymentAmount;
		this.receiptPaymentDate = receiptPaymentDate;
		this.receiptPaymentType = receiptPaymentType;
	}
	
	public static void createReceipt(int orderId, int receiptPaymentAmount, LocalDate receiptPaymentDate, String receiptPaymentType) {
		String query = String.format("INSERT INTO receipts(OrderID, ReceiptPaymentAmount, ReceiptPaymentDate, ReceiptPaymentType) VALUES (\"%d\", \"%d\", \"%s\", \"%s\")", orderId, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType); 
		Connect con = Connect.getInstance();
		con.execUpdate(query);
	}
	
	public static void updateReceipt(int orderId, int receiptPaymentAmount, LocalDate receiptPaymentDate, String receiptPaymentType) {
		String query = String.format("UPDATE receipts SET `ReceiptPaymentAmount`=\'%d\', `ReceiptPaymentDate`=\'%s\', `ReceiptPaymentType`=\'%s\' WHERE `OrderID` = \'%d\' ", receiptPaymentAmount, receiptPaymentDate, receiptPaymentType , orderId);
		Connect con = Connect.getInstance();
		con.execUpdate(query);
	}
	
	public static void deleteReceipt(int orderId) {
		String query = String.format("DELETE FROM `receipts` WHERE `OrderID` = '%d'", orderId);
        Connect con = Connect.getInstance();
        con.execUpdate(query);
	}
	
	public static Receipt getReceiptById(int receiptId) {
		String query = String.format("SELECT * FROM receipts WHERE `ReceiptID` =  '%d'", receiptId);
        Connect con = Connect.getInstance();
        con.rs = con.execQuery(query);

        try {
            while (con.rs.next()) {
                int orderId = con.rs.getInt("OrderID");
                int receiptPaymentAmount = con.rs.getInt("ReceiptPaymentAmount");
                LocalDate receiptPaymentDate = con.rs.getDate("ReceiptPaymentDate").toLocalDate();
                String receiptPaymentType = con.rs.getString("ReceiptPaymentType");
                return new Receipt(receiptId, orderId, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
	}
	
	public static ArrayList<Receipt> getAllReceipts() {
	        ArrayList<Receipt> receipts = new ArrayList<>();
	        Connect con = Connect.getInstance();

	        String query = "SELECT * FROM receipts";

	        con.rs = con.execQuery(query);

	        try {
	            while (con.rs.next()) {
	                int receiptId = con.rs.getInt("ReceiptID");
	                int orderId = con.rs.getInt("OrderID");
	                int receiptPaymentAmount = con.rs.getInt("ReceiptPaymentAmount");
	                LocalDate receiptPaymentDate = con.rs.getDate("ReceiptPaymentDate").toLocalDate();
	                String receiptPaymentType = con.rs.getString("ReceiptPaymentType");
	                receipts.add(new Receipt(receiptId, orderId, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return receipts;
	}

	public int getReceiptId() {
		return ReceiptID;
	}

	public void setReceiptId(int receiptId) {
		this.ReceiptID = receiptId;
	}
	
	public int getReceiptID() {
		return ReceiptID;
	}

	public void setReceiptID(int receiptID) {
		ReceiptID = receiptID;
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public String getReceiptPaymentType() {
		return receiptPaymentType;
	}

	public void setReceiptPaymentType(String receiptPaymentType) {
		this.receiptPaymentType = receiptPaymentType;
	}

	public int getReceiptPaymentAmount() {
		return receiptPaymentAmount;
	}

	public void setReceiptPaymentAmount(int receiptPaymentAmount) {
		this.receiptPaymentAmount = receiptPaymentAmount;
	}

	public LocalDate getReceiptPaymentDate() {
		return receiptPaymentDate;
	}

	public void setReceiptPaymentDate(LocalDate receiptPaymentDate) {
		this.receiptPaymentDate = receiptPaymentDate;
	}

	public String getReceiptType() {
		return receiptPaymentType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptPaymentType = receiptType;
	}
}
