package view;

import controller.AddOrderViewController;
import controller.AdminViewController;
import controller.LoginViewController;
import controller.MenuItemManagementViewController;
import controller.OrderViewController;
import controller.PrepareViewController;
import controller.ReceiptManagementController;
import controller.ServeViewController;
import controller.UpdateOrderItemsController;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NavView {
	private Button adminBtn, menuItemBtn, addorderBtn, orderViewBtn, updOrderbtn, receiptBtn, prepareBtn, serveBtn;
	    
	    public NavView() {
	        // TODO Auto-generated constructor stub
	       
	    }
	    
	    public GridPane loadNavBar(Stage primaryStage) {
	        GridPane navbar = new GridPane();
	    
	        adminBtn = new Button("User Management");
	        menuItemBtn = new Button("Menu Item");
	        addorderBtn = new Button("Add Order");
	        updOrderbtn = new Button("Update Order");
	        orderViewBtn = new Button("Order View");
	        receiptBtn = new Button("Receipt");
	        prepareBtn = new Button("Prepare");
	        serveBtn = new Button("Serve");
	        
//	      Add button yang ada di navigation bar sesuai role
	        
//	      Ini logika untuk hilangin button sesuai role
	        if(LoginViewController.curruser.getUserRole().equals("Admin") ) {            	
	        	navbar.add(adminBtn, 1, 0);
	        }
        	navbar.add(menuItemBtn, 2, 0);
	        if(LoginViewController.curruser.getUserRole().equals("Customer") ) {   
	        	navbar.add(addorderBtn, 3, 0);
	        	navbar.add(updOrderbtn, 4, 0);
	        }
	        if(LoginViewController.curruser.getUserRole().equals("Cashier")) {
		        navbar.add(orderViewBtn, 3, 0);
		        navbar.add(receiptBtn, 4, 0);
	        }
	        if(LoginViewController.curruser.getUserRole().equals("Chef")) {
	        	navbar.add(prepareBtn, 3, 0);
	        }
	        if(LoginViewController.curruser.getUserRole().equals("Waiter")) {
	        	navbar.add(serveBtn, 3, 0);
	        }
	    
	        eventHandler(primaryStage);
	        
	        return navbar;
	    }
	    
	    private void eventHandler(Stage primaryStage) {
//	        Routing semua button ke halaman yang tepat
//	    	Ini logika untuk if userrole nya
	    	if(LoginViewController.curruser.getUserRole().equals("Admin")) {        	
//	    		Ini Contoh routing nya
	    		adminBtn.setOnAction(e -> {
	    			AdminView AV = new AdminView(primaryStage);
	    			AdminViewController UC = new AdminViewController(AV);
	    		});
	    	}
	    	menuItemBtn.setOnAction(e -> {
    			MenuItemView MIV = new MenuItemView(primaryStage);
    			MenuItemManagementViewController MC = new MenuItemManagementViewController(MIV);
    		});
	    	if(LoginViewController.curruser.getUserRole().equals("Customer")) {        	
	    		addorderBtn.setOnAction(e -> {
	    			AddOrderView AOV = new AddOrderView(primaryStage);
	    			AddOrderViewController AOVC = new AddOrderViewController(AOV);
	    		});
	    		updOrderbtn.setOnAction(e ->{
	    			UpdateOrderItemsView UOIV = new UpdateOrderItemsView(primaryStage);
	    			UpdateOrderItemsController UOIC = new UpdateOrderItemsController(UOIV);
	    		});
	    	}
	    	if(LoginViewController.curruser.getUserRole().equals("Cashier")) {
	    		orderViewBtn.setOnAction(e -> {
		            OrderView OV = new OrderView(primaryStage);
		            OrderViewController OVC = new OrderViewController(OV);
		        });
	    		receiptBtn.setOnAction(e -> {
		            ReceiptManagementView RV = new ReceiptManagementView(primaryStage);
		            ReceiptManagementController RC = new ReceiptManagementController(RV);
		        });
	    	}
	    	if(LoginViewController.curruser.getUserRole().equals("Chef")) {
	    		prepareBtn.setOnAction(e ->{
	    			PrepareView PV = new PrepareView(primaryStage);
	    			PrepareViewController PVC = new PrepareViewController(PV);
	    		});
	    	}
	    	if(LoginViewController.curruser.getUserRole().equals("Waiter")) {
	    		serveBtn.setOnAction(e ->{
	    			ServeView SV = new ServeView(primaryStage);
	    			ServeViewController SVC = new ServeViewController(SV);
	    		});
	    	}
	    }
}
