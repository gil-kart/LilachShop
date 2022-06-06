package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Transactional
@Entity
@Table(name = "PickUpDetails")
public class PickUpDetails implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    Order order;

    LocalDateTime PickUptime;

    public PickUpDetails(LocalDateTime pickUptime) {
        PickUptime = pickUptime;
    }

    protected PickUpDetails() {

    }

    public LocalDateTime getPickUptime() {
        return PickUptime;
    }

    public void setPickUptime(LocalDateTime pickUptime) {
        PickUptime = pickUptime;
    }

    public Long getId() {
        return id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
