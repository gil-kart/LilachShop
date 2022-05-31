package org.lilachshop.panels;

import org.lilachshop.requests.ReportsRequest;

public class StoreManagerPanel extends GeneralEmployeePanel {
    public StoreManagerPanel(String host, int port, Object controller) {
        super(host, port, controller);
    }
    public void getStoreComplaint(long storeID){
        sendToServer(new ReportsRequest("get store complaints", storeID));
    }
    public void getStoreOrders(long storeID){
        sendToServer(new ReportsRequest("get store orders", storeID));
    }
    public void getStoreCatalog(long storeID){
        sendToServer(new ReportsRequest("get store catalog", storeID));
    }
}
