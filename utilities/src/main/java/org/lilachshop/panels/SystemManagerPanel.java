package org.lilachshop.panels;

import org.lilachshop.entities.Employee;
import org.lilachshop.requests.EmployeeEditRequest;
import org.lilachshop.requests.StoreRequest;

import java.util.List;

public class SystemManagerPanel extends Panel {
    public SystemManagerPanel(String host, int port, Object controller) {
        super(host, port, controller);
    }

    public void getAllEmployees() {
        sendToServer(new EmployeeEditRequest(EmployeeEditRequest.Messages.GET_ALL_EMPLOYEES));
    }

    public void setAllEmployees(List<Employee> employees) {
        sendToServer(new EmployeeEditRequest(EmployeeEditRequest.Messages.SET_ALL_EMPLOYEES, employees));
    }

    public void getAllStores() {
        sendToServer(new StoreRequest("get all stores event"));
    }
}
