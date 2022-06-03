package org.lilachshop.events;

import org.lilachshop.entities.Item;
import org.lilachshop.entities.Order;

import java.io.Serializable;
import java.util.List;

public class OrderEvent implements Serializable {
    List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public OrderEvent(List<Order> orders) {
        this.orders = orders;
    }
}