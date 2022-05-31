package org.lilachshop.panels;

import org.lilachshop.requests.*;
import org.lilachshop.entities.*;


public class CustomerAnonymousPanel extends Panel {

    public CustomerAnonymousPanel(String host, int port, Object controller) {
        super(host, port, controller);
    }

    public void sendGetGeneralCatalogRequestToServer() {
        sendToServer(new CatalogRequest("get catalog", 0));
//        sendToServer(new DebugRequest("write catalog"));
    }

    public void sendCustomerLoginRequest(String userName, String password) {
        sendToServer(new CustomerLoginRequest("customer login request", userName, password));
    }

    public void sendSignUpRequest(Customer customer) {
        sendToServer(new SignUpRequest(customer));
    }

    public void getAllStores() {
        sendToServer((new StoreRequest("get all stores")));
    }

}
