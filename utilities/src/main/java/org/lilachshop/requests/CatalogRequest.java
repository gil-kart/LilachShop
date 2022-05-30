package org.lilachshop.requests;

public class CatalogRequest extends Request{
    int catalogId;
    public CatalogRequest(String request, int catalogId) {
        super(request);
        this.catalogId = catalogId;
    }

    public int getCatalogId() {
        return catalogId;
    }
}
