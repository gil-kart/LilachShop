package org.lilachshop.customerclient;

public class myOrderItem {
    private  Flower flower;
    private int count;

    public myOrderItem() {

    }
    public myOrderItem(Flower flower,int count) {
        this.flower = flower;
        this.count = count;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
