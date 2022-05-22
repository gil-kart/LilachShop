package org.lilachshop.panels;

import org.lilachshop.entities.Complaint;
import org.lilachshop.requests.CatalogRequest;
import org.lilachshop.requests.ComplaintRequest;
import org.lilachshop.requests.UserComplaintRequest;

import java.util.ArrayList;
import java.util.List;

public class RegisteredCustomerPanel extends CustomerAnonymousPanel{
    public RegisteredCustomerPanel(String host, int port, Object controller) {
        super(host, port, controller);
    }

    public void sendComplaintToServer(Complaint complaint) {
        sendToServer(new UserComplaintRequest("post new complaint", complaint));
    }
}
