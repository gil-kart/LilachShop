package org.lilachshop.panels;

import org.lilachshop.commonUtils.Socket;
import org.lilachshop.entities.Complaint;
import org.lilachshop.requests.UserComplaintRequest;

public class RegisteredCustomerPanel extends CustomerAnonymousPanel {
    public RegisteredCustomerPanel(Socket socket, Object controller) {
        super(socket, controller);
    }

    public void sendComplaintToServer(Complaint complaint) {
        sendToServer(new UserComplaintRequest("post new complaint", complaint));
    }
}
