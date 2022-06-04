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

    public void saveNewItem(Item item, Catalog catalog,boolean saveMode) {
        if (saveMode) { //save-mode
            System.out.println("General panel sending request to server:add new item to Catalog" +catalog.getId());
            sendToServer(new CatalogRequest("add new Item to Catalog",catalog.getId(),item));
        }else{ //edit - mode
            System.out.println("General panel sending request to server:edit item" + item.getId()+" to Catalog" +catalog.getId());
            sendToServer(new CatalogRequest("edit Item to Catalog",catalog.getId(),item));
        }
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

    public void deleteItem(Item item,long id) {
        sendToServer(new CatalogRequest("delete item",id,item));
    }
}
