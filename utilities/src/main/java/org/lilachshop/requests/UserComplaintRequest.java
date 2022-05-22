package org.lilachshop.requests;

import org.lilachshop.entities.Complaint;

public class UserComplaintRequest extends Request {
    Complaint  complaint;

    public Complaint getComplaint() {
        return complaint;
    }

    public UserComplaintRequest(String request, Complaint complaint) {
        super(request);
        this.complaint = complaint;
    }

    public UserComplaintRequest(String request) {
        super(request);
    }
}
