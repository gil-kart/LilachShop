package org.lilachshop.panels;

import org.lilachshop.entities.Employee;
import org.lilachshop.requests.EmployeeEditRequest;
import org.lilachshop.requests.StoreRequest;

import java.util.Collections;
import java.util.HashSet;
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

    public void createEmployee(Employee employee) {
        updateEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        sendToServer(new EmployeeEditRequest(EmployeeEditRequest.Messages.CREATE_UPDATE_EMPLOYEE, employee));
    }

    public void deleteEmployeeByID(Long id) {
        sendToServer(new EmployeeEditRequest(EmployeeEditRequest.Messages.DELETE_EMPLOYEES_BY_ID, new HashSet<>(Collections.singleton(id))));
    }

    public void getAllStores() {
        sendToServer(new StoreRequest("get all stores event"));
    }
}
