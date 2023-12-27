package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MenuItem;
import model.OrderItem;
import view.AddOrderView;

public class AddOrderViewController {
	AddOrderView adov;
	private MenuItem selectedMenuItem;
	private ArrayList<OrderItem> orderitems = new ArrayList<>();
	
	//untuk fungsi button
	private void initializeButtonHandler() {
		adov.getAddButton().setOnAction(e -> {
			Integer quantity = Integer.parseInt(adov.getQuantityInput().getText());
			OrderController.createOrder(LoginViewController.curruser, LocalDate.now());
			
			if(quantity < 1) {
				Alert alert = new Alert(AlertType.INFORMATION, "Quantity must not be smaller than 1!");
				alert.show();
			}else {
				OrderItemController.createOrderItem(OrderController.getLastOrder().getOrderID(), selectedMenuItem, quantity);
				orderitems.add(new OrderItem(OrderController.getLastOrder().getOrderID(), selectedMenuItem, quantity));
				OrderController.calculateOrderTotal(orderitems);
			}	
			loadMenuItemData();
		});
	}
	
	//untuk memunculkan data menu item
	private void loadMenuItemData() {
		ArrayList<MenuItem> menus = MenuItem.getAllMenuItems();
		adov.getMenuTable().getItems().setAll(menus);
	}
	
	//agar tabel bisa diklik dan dapat mengambil value dalam kolom
	private void setupTableSelectionListener() {
		adov.getMenuTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			if(newSelection != null) {
				adov.getNameInput().setText(newSelection.getMenuItemName());

				Integer id = newSelection.getMenuItemID();
				String name = newSelection.getMenuItemName();
				String description = newSelection.getMenuItemDescription();
				Integer price = newSelection.getMenuItemPrice();
				
				selectedMenuItem = new MenuItem(id, name, description, price);
			}
		});
	}
	
	//untuk menyambungkan controller dan view
	public AddOrderViewController(AddOrderView adov) {
		this.adov = adov;
		
		initializeButtonHandler();
		setupTableSelectionListener();
		loadMenuItemData();
	}
}
