package controller;

import java.util.ArrayList;

import model.MenuItem;
import view.MenuItemView;

public class MenuItemManagementViewController {
	MenuItemView menuItemView;

	//untuk fungsi button
	private void initializeButtonHandler() {
		menuItemView.getAddButton().setOnAction(e -> {
			String name = menuItemView.getNameInput().getText();
			String description = menuItemView.getDescriptionInput().getText();
			Integer price = Integer.parseInt(menuItemView.getPriceInput().getText());
		
			MenuItemController.createMenuItem(name, description, price);
			loadMenuItemData();
		});
	
		menuItemView.getUpdateButton().setOnAction(e -> {
			Integer id = Integer.parseInt(menuItemView.getIdInput().getText());
			String name = menuItemView.getNameInput().getText();
			String description = menuItemView.getDescriptionInput().getText();
			Integer price = Integer.parseInt(menuItemView.getPriceInput().getText());
		
			MenuItemController.updateMenuItem(id, name, description, price);
			loadMenuItemData();
		});
	
		menuItemView.getDeleteButton().setOnAction(e -> {
			Integer id = Integer.parseInt(menuItemView.getIdInput().getText());
			
			MenuItemController.deleteMenuItem(id);
			loadMenuItemData();
		});
	}
	
	//untuk memunculkan data menu item
	private void loadMenuItemData() {
		ArrayList<MenuItem> menus = MenuItemController.getAllMenuItems();
		menuItemView.getMenuTable().getItems().setAll(menus);
	}
	
	//agar tabel bisa diklik dan dapat mengambil value dalam kolom
	private void setupTableSelectionListener() {
		menuItemView.getMenuTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			if(newSelection != null) {
				menuItemView.getIdInput().setText(String.valueOf(newSelection.getMenuItemID()));
				menuItemView.getNameInput().setText(newSelection.getMenuItemName());
				menuItemView.getDescriptionInput().setText(newSelection.getMenuItemDescription());
				menuItemView.getPriceInput().setText(String.valueOf(newSelection.getMenuItemPrice()));
			}
		});
	}
	
	//untuk menyambungkan controller dan view
	public MenuItemManagementViewController(MenuItemView menuItemView) {
		this.menuItemView = menuItemView;
	
		initializeButtonHandler();
		setupTableSelectionListener();
		loadMenuItemData();
	}
}
