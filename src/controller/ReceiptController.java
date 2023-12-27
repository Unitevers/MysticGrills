package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Connect;
import model.Receipt;

public class ReceiptController {
	// untuk memvalidasi
	public static void createReceipt(int OrderID, String ReceiptPaymentType, int ReceiptPaymentAmount, LocalDate ReceiptPaymentDate) {
		if(OrderID == 0 || ReceiptPaymentType.isBlank() || ReceiptPaymentAmount == 0 || ReceiptPaymentDate == null) {
			Alert alert = new Alert(AlertType.INFORMATION, "Order Id, Payment Type, Payment amount, and Payment Date must not be empty!");
	    	alert.show();
		}
		else if(!ReceiptPaymentType.equals("Cash") && !ReceiptPaymentType.equals("Debit") && !ReceiptPaymentType.equals("Credit")) {
			Alert alert = new Alert(AlertType.INFORMATION, "Payment Type must be \"Cash\" , \"Debit\", or \"Credit\"!");
	    	alert.show();
		}
		else {
			Receipt.createReceipt(OrderID, ReceiptPaymentAmount, LocalDate.now(), ReceiptPaymentType);
		}
	}
	
	// untuk memvalidasi
	public static void updateReceipt(int OrderID, String ReceiptPaymentType, int ReceiptPaymentAmount, LocalDate ReceiptPaymentDate) {
		if(OrderID == 0 || ReceiptPaymentType.isBlank() || ReceiptPaymentAmount == 0 || ReceiptPaymentDate == null) {
			Alert alert = new Alert(AlertType.INFORMATION, "Order Id, Payment Type, Payment amount, and Payment Date must not be empty!");
	    	alert.show();
		}
		else {
			Receipt.updateReceipt(OrderID, ReceiptPaymentAmount, ReceiptPaymentDate, ReceiptPaymentType);
		}
	}
	
	// untuk memvalidasi 
	public static void deleteReceipt(int OrderID) {
		String query = String.format("SELECT * FROM receipts WHERE `OrderID` = '%d'", OrderID);
        Connect con = Connect.getInstance();
        con.execQuery(query);
		try {
			if(con.rs.next()) {
				Receipt.deleteReceipt(OrderID);
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION, "User ID does not exits!");
				alert.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    // untuk memvalidasi 
	public static void getReceiptById(int ReceiptID) {
		String query = String.format("SELECT * FROM receipts WHERE `ReceiptID` = '%d'", ReceiptID);
        Connect con = Connect.getInstance();
        con.execQuery(query);
		try {
			if(con.rs.next()) {
				Receipt.getReceiptById(ReceiptID);
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION, "User ID does not exits!");
				alert.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// untuk menyambungkan controller dengan view
	public static ArrayList<Receipt> getAllReceipts() {
		return Receipt.getAllReceipts();
	}
}
