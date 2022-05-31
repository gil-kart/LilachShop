package org.lilachshop.requests;

public class CatalogRequest extends Request {
    long catalogId;

    public CatalogRequest(String request, long catalogId) {
        super(request);
        this.catalogId = catalogId;
    }

    public long getCatalogId() {
        return catalogId;
    }
}
