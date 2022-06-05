package org.lilachshop.panels;

import org.lilachshop.commonUtils.Socket;
import org.lilachshop.requests.Request;

import java.io.IOException;

public abstract class Panel {
    private final LilachClient lilachClient;

    public Panel(Socket socket, Object controller) {
        lilachClient = LilachClient.getClient(socket);
        lilachClient.getBus().register(controller);
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

