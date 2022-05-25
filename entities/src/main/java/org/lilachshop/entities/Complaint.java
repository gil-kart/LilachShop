package org.lilachshop.entities;

import lombok.ToString;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
@Transactional

@Entity
@Table(name = "Complaint")
public class Complaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String creationDate;
    String endOfHandleDate;
    String status;
    String content;
    String reply;

    @OneToOne(cascade = CascadeType.ALL)
    Order order;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Complaint() {}

    public int getId() {
        return id;
    }

    public Complaint(String endOfHandleDate, String status, String content, String creationDate, String reply) {
        this.endOfHandleDate = endOfHandleDate;
        this.status = status;
        this.content = content;
        this.creationDate = creationDate;
        this.reply = reply;
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

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
