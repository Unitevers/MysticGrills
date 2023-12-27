package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.OrderItem;
import model.Receipt;
import view.ReceiptManagementView;

public class ReceiptManagementController {
    ReceiptManagementView rmView;
    private Receipt selectedReceipt;
	
	//untuk memunculkan data menu item
	private void loadReceiptData() {
		ArrayList<Receipt> receipts = ReceiptController.getAllReceipts();
		rmView.getReceiptTable().getItems().setAll(receipts);
	}
	
	//untuk memunculkan data menu item
		private void loadOrderItemData() {
			ArrayList<OrderItem> orderitems = OrderItemController.getAllOrderItemsByOrderId(selectedReceipt.getOrderID());
			rmView.getOiTable().getItems().setAll(orderitems);
		}
	
	//agar tabel bisa diklik dan dapat mengambil value dalam kolom
	private void setupTableSelectionListener() {
		rmView.getReceiptTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			if(newSelection != null) {
//        		rmView.getOrderIdInput().setText(String.valueOf(newSelection.getOrderID()));

				Integer receiptId = newSelection.getReceiptId();
				Integer orderId = newSelection.getOrderID();
				Integer receiptPaymentAmount = newSelection.getReceiptPaymentAmount();
				LocalDate receiptPaymentDate = newSelection.getReceiptPaymentDate();
				String receiptPaymentType = newSelection.getReceiptPaymentType();
				
				selectedReceipt = new Receipt(receiptId, orderId, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType);
				
				loadOrderItemData();
				
			}
		});
	}
	
	//untuk menyambungkan controller dan view
	public ReceiptManagementController(ReceiptManagementView rmv) {
		this.rmView = rmv;
		
		setupTableSelectionListener();
		loadReceiptData();
	}
}
