package org.lilachshop.panels;

import org.lilachshop.requests.CatalogRequest;
import org.lilachshop.requests.DebugRequest;


public class CustomerAnonymousPanel extends Panel{

    public CustomerAnonymousPanel(String host, int port, Object controller) {
        super(host, port, controller);
    }
    public void sendCatalogRequestToServer() {
//        sendToServer(new CatalogRequest("get catalog"));
        sendToServer(new DebugRequest("write catalog"));
    }
}
