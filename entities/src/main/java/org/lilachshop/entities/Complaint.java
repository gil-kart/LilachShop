package org.lilachshop.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Complaint")
public class Complaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String endOfHandleDate;
    String status;
    String complaintNumber;
    String content;

    public Complaint() {}

    public String getComplaintNumber() {
        return complaintNumber;
    }

    public Complaint(String endOfHandleDate, String status, String complaintNumber, String content) {
        this.endOfHandleDate = endOfHandleDate;
        this.status = status;
        this.complaintNumber = complaintNumber;
        this.content = content;
    }

    public String getCreationDate() {
        return endOfHandleDate;
    }

    public void setCreationDate(String creationDate) {
        this.endOfHandleDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setComplaintNumber(String complaintNumber) {
        this.complaintNumber = complaintNumber;
    }
}
