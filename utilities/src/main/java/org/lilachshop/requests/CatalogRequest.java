package org.lilachshop.requests;

import org.lilachshop.entities.Item;

public class CatalogRequest extends Request {
    long Id;
    Item item;

    public CatalogRequest(String request, long catalogId, Item item) {
        super(request);
        Id = catalogId;
        this.item = item;
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
