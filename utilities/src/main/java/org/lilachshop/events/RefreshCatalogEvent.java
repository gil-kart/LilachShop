package org.lilachshop.events;

import org.lilachshop.entities.Store;

public class RefreshCatalogEvent {
    int id;

    public RefreshCatalogEvent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
