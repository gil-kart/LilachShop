package org.lilachshop.panels;

import org.lilachshop.entities.Complaint;
import org.lilachshop.requests.SupportComplaintRequest;

public class CustomerServicePanel extends Panel{
    public CustomerServicePanel(String host, int port, Object controller) {
        super(host, port, controller);
    }
    public void GetAllClientsComplaintsRequestToServer() {
        sendToServer(new SupportComplaintRequest("get all complaints"));
    }
    public void ReplyToComplaintRequestToServer(Complaint complaint, String reply){
        sendToServer(new SupportComplaintRequest("reply to customer complaint", reply, complaint));
    }
}
