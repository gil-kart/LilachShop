package org.lilachshop.panels;

import org.lilachshop.entities.Catalog;
import org.lilachshop.entities.Employee;
import org.lilachshop.entities.Item;
import org.lilachshop.entities.Store;
import org.lilachshop.requests.CatalogRequest;
import org.lilachshop.requests.StoreRequest;

public class GeneralEmployeePanel extends Panel {
    Employee employee;


    public GeneralEmployeePanel(String host, int port, Object controller) {
        super(host, port, controller);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void getCatalogByStoreID(long storeID) {
        sendToServer(new CatalogRequest("Get Catalog By StoreID",storeID));
    }

    public void saveNewItem(Item item, Catalog catalog) {
        sendToServer(new CatalogRequest("add new Item to Catalog",catalog.getId(),item));
    }

    public void getAllCatalog() {
        sendToServer(new CatalogRequest("get all catalogs"));
    }

    public void getAllStores() {
        sendToServer((new StoreRequest("get all stores")));
    }

    public void getCatalogByID(int id) {
        sendToServer(new CatalogRequest("get catalog by id",id));
    }
}
