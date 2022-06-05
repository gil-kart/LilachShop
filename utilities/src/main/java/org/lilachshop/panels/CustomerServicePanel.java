package org.lilachshop.panels;

import org.lilachshop.commonUtils.Socket;
import org.lilachshop.entities.Complaint;
import org.lilachshop.requests.SupportComplaintRequest;

public class CustomerServicePanel extends Panel {
    public CustomerServicePanel(Socket socket, Object controller) {
        super(socket, controller);
    }

    public void GetAllClientsComplaintsRequestToServer() {
        sendToServer(new SupportComplaintRequest("get all complaints"));
    }

    public void ReplyToComplaintRequestToServer(Complaint complaint, String reply) {
        sendToServer(new SupportComplaintRequest("reply to customer complaint", reply, complaint));
    }
}
