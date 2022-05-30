package org.lilachshop.customerclient;

import org.lilachshop.entities.Item;

public class myOrderItem {
    private Item flower;
    private int count;

    public myOrderItem() {

    }
    public myOrderItem(Item flower,int count) {
        this.flower = flower;
        this.count = count;
    }

    public Item getFlower() {
        return flower;
    }

    public void setFlower(Item flower) {
        this.flower = flower;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
