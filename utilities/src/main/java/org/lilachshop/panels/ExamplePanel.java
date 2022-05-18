package org.lilachshop.panels;

import org.lilachshop.controller.Controller;
import org.lilachshop.requests.DebugRequest;

public class ExamplePanel extends Panel {
    private DebugRequest debugRequest;

    public ExamplePanel(String host, int port, Controller controller) {
        super(host, port, controller);
    }

    public void sendMessageToServer(String msg) {
        sendToServer(new DebugRequest(msg));
    }

    // has access to **certain** requests
}
