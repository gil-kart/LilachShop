package org.lilachshop.panels;

import org.lilachshop.entities.Employee;
import org.lilachshop.requests.EmployeeEditRequest;

import java.util.List;

public class SysAdminPanel extends Panel {
    public SysAdminPanel(String host, int port, Object controller) {
        super(host, port, controller);
    }

    public void getAllEmployees() {
        sendToServer(new EmployeeEditRequest(EmployeeEditRequest.Messages.GET_ALL_EMPLOYEES));
    }

    public void setAllEmployees(List<Employee> employees) {
        sendToServer(new EmployeeEditRequest(EmployeeEditRequest.Messages.SET_ALL_EMPLOYEES));
    }

    public void getAllStores(){
        // todo: im here
    }
}
