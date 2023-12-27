package controller;

import java.util.ArrayList;

import model.Order;
import model.OrderItem;
import view.ServeView;

public class ServeViewController {
	ServeView sv;
    private ArrayList<OrderItem> orderItems = new ArrayList<>();

    private void initializeButtonHandler() {
        sv.getServeBtn().setOnAction(e ->{
            int orderId = Integer.parseInt(sv.getOrderIdInput().getText());
            OrderController.serveOrder(orderId);
            loadOrderData();
        });
        
        sv.getDelBut().setOnAction(e -> {
            int orderId = Integer.parseInt(sv.getOrderIdInput().getText());
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
        sv.getTable().getItems().setAll(orders);
    }
    
    public ServeViewController(ServeView sv) {
        this.sv = sv;
        
        initializeButtonHandler();
        loadOrderData();
    }
}
