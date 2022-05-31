package org.lilachshop.employeeclient;

import org.lilachshop.entities.Employee;

public class EmployeeEvent {
    private final Employee employeeToDisplay;

    public EmployeeEvent(Employee employeeToDisplay) {
        this.employeeToDisplay = employeeToDisplay;
    }

    public Employee getEmployeeToDisplay() {
        return employeeToDisplay;
    }
}
