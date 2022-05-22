package org.lilachshop.requests;

import org.lilachshop.entities.Complaint;

public class SupportComplaintRequest extends Request {
    String reply;
    Complaint complaint;
    public Complaint getComplaint() {
        return complaint;
    }
    public String getReply(){
        return reply;
    }
    public SupportComplaintRequest(String request) {
        super(request);
    }

    public SupportComplaintRequest(String request, String reply, Complaint complaint) {
        super(request);
        this.reply = reply;
        this.complaint = complaint;
    }

}
