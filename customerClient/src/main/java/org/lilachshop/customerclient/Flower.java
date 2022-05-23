package org.lilachshop.customerclient;

public class Flower {
    public Flower(){}

    public Flower(String name, int price, String imgSrc,int percent) {
        this.name = name;
        this.price = price;
        this.imgSrc = imgSrc;
        this.percent = percent;
    }
    private String name;
    private int price;
    private String imgSrc;
    private String color;

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    private int id;
    private int percent;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
