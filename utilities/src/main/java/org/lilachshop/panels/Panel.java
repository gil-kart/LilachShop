package org.lilachshop.panels;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.controller.Controller;
import org.lilachshop.panels.ocsf.AbstractClient;
import org.lilachshop.requests.Request;

import java.io.IOException;

public abstract class Panel {
    private final LilachClient lilachClient;

    public Panel(String host, int port, Controller controller) {
        EventBus bus = EventBus.builder().build();
        bus.register(controller);
        lilachClient = new LilachClient(host, port, bus);
        try {
            lilachClient.openConnection();
        } catch (IOException e) {
            System.out.println("Client failed to open connection to server.");
            e.printStackTrace();
        }
    }

    protected void sendToServer(Request request) {
        try {
            lilachClient.sendToServer(request);
        } catch (IOException e) {
            System.out.println("Unable to send request to server.");
            e.printStackTrace();
        }
    }
}

