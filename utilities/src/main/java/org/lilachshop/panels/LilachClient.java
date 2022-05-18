package org.lilachshop.panels;

import org.greenrobot.eventbus.EventBus;
import org.lilachshop.panels.ocsf.AbstractClient;

public class LilachClient extends AbstractClient {
    private final EventBus bus;

    public LilachClient(String host, int port, EventBus bus) { //todo change object to actual controller type, maybe some generic type
        super(host, port);
        this.bus = bus; // this posts stuff
    }

    @Override
    protected void connectionClosed() {
        System.out.println("Client connection closed.");
    }

    @Override
    protected void connectionEstablished() {
        System.out.println("Client connection established.");
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        bus.post(msg);
    }
}
