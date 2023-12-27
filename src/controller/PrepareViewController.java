package controller;

import java.util.ArrayList;

import model.Order;
import model.OrderItem;
import view.PrepareView;

public class PrepareViewController {
	PrepareView pv;
    private ArrayList<OrderItem> orderItems = new ArrayList<>();

    private void initializeButtonHandler() {
        pv.getPrepareBtn().setOnAction(e ->{
            int orderId = Integer.parseInt(pv.getOrderIdInput().getText());
            OrderController.prepareOrder(orderId);
            loadOrderData();
        });
        
        pv.getDelBut().setOnAction(e -> {
            int orderId = Integer.parseInt(pv.getOrderIdInput().getText());
            orderItems = OrderItemController.getAllOrderItemsByOrderId(orderId);
            
            for(OrderItem item : orderItems) {
                OrderItemController.deleteOrderItem(orderId, item.getMenuitem().getMenuItemID());
            }
            
            OrderController.deleteOrder(orderId);
            loadOrderData();
        });
    }
    
    private void loadOrderData() {
        ArrayList<Order> orders = OrderController.getAllOrders();
        pv.getTable().getItems().setAll(orders);
    }
    
    public PrepareViewController(PrepareView pv) {
        this.pv = pv;
        
        initializeButtonHandler();
        loadOrderData();
    }
}
