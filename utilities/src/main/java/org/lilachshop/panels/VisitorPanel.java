package org.lilachshop.panels;

import java.io.IOException;
import org.greenrobot.eventbus.EventBus;
import org.lilachshop.requests.Request;

public class VisitorPanel extends Panel{

    public VisitorPanel(String host, int port, Object controller) {
        super(host, port, controller);
    }
    public void sendCatalogRequestToServer(String msg) {
        sendToServer(new Request(msg));
    }
}
