package org.lilachshop.panels;


import org.lilachshop.entities.Complaint;
import org.lilachshop.entities.Order;
import org.lilachshop.requests.CatalogRequest;
import org.lilachshop.requests.OrderRequest;
import org.lilachshop.requests.StoreRequest;
import org.lilachshop.requests.UserComplaintRequest;

public class StoreCustomerPanel extends Panel {
    public StoreCustomerPanel(String host, int port, Object controller) {
        super(host, port, controller);
    }
    public void sendComplaintToServer(Complaint complaint) {
        sendToServer(new UserComplaintRequest("post new complaint", complaint));
    }
    public void sendGetCatalogRequestToServer(long id_store) {
        sendToServer(new CatalogRequest("get catalog", id_store));
    }
    public void sendGetAllOrdersToServer(long customerId)
    {
        sendToServer(new OrderRequest("get all clients orders", customerId));
    }
    public void sendNewOrderCreationToServer(Order myOrder)
    {
        sendToServer(new OrderRequest("create new order", myOrder));
    }
    public void getAllStores() {
        sendToServer((new StoreRequest("get all stores")));
    }
}
