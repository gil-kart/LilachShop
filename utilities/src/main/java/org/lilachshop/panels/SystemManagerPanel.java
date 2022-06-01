package org.lilachshop.panels;

import org.lilachshop.entities.Employee;
import org.lilachshop.requests.EmployeeEditRequest;
import org.lilachshop.requests.StoreRequest;

import java.util.List;
import java.util.Set;

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

    public void deleteEmployeesByID(Set<Long> ids) {
        sendToServer(new EmployeeEditRequest(EmployeeEditRequest.Messages.DELETE_EMPLOYEES_BY_ID, ids));
    }

    public void getAllStores() {
        sendToServer(new StoreRequest("get all stores event"));
    }
}
