package org.lilachshop.customerclient;

import java.util.List;

public class Order {
    List<myOrderItem> myOrder;

    public Order(List<myOrderItem> myOrder) {
        this.myOrder = myOrder;
    }

    public List<myOrderItem> getMyOrder() {
        return myOrder;
    }

    public void setMyOrder(List<myOrderItem> myOrder) {
        this.myOrder = myOrder;
    }
}
