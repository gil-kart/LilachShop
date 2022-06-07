package org.lilachshop.requests;

import org.lilachshop.entities.Color;
import org.lilachshop.entities.Item;
import org.lilachshop.entities.ItemType;

public class CatalogRequest extends Request {
    private int price;
    private Color color = null;
    private ItemType type = null;
    long Id;
    Item item;

    public CatalogRequest(String request, long catalogId, int price , Color color, ItemType type) {
        super(request);
        Id = catalogId;
        this.price = price;
        this.color = color;
        this.type = type;

    }

    public CatalogRequest(String request, long catalogId, Item item) {
        super(request);
        Id = catalogId;
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public Color getColor() {
        return color;
    }

    public ItemType getType() {
        return type;
    }

    public CatalogRequest(String request, long catalogId) {
        super(request);
        this.Id = catalogId;
    }

    public CatalogRequest(String get_all_catalogs) {
        super(get_all_catalogs);
    }

    public long getId() {
        return Id;
    }

    public Item getItem() {
        return item;
    }
}
