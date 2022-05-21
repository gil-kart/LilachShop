package org.lilachshop.requests;

import org.lilachshop.entities.Complaint;

public class ComplaintRequest extends Request{
    Complaint complaint;
    public ComplaintRequest(String request) {
        super(request);
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public ComplaintRequest(String request, Complaint complaint) {
        super(request);
        this.complaint = complaint;
    }
}
