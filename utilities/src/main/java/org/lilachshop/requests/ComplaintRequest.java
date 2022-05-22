package org.lilachshop.requests;

import org.lilachshop.entities.Complaint;

import java.io.Serializable;
import java.util.List;

public class ComplaintRequest extends Request {
    Complaint  complaint;

    public Complaint getComplaint() {
        return complaint;
    }

    public ComplaintRequest(String request, Complaint complaint) {
        super(request);
        this.complaint = complaint;
    }

}
