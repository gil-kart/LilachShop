package org.lilachshop.requests;

import org.lilachshop.entities.Employee;

import java.util.LinkedList;
import java.util.List;

public class EmployeeEditRequest extends Request {

    public enum Messages {
        GET_ALL_EMPLOYEES,
        SET_ALL_EMPLOYEES;
    }

    private List<Employee> employeesToEdit = null;

    public EmployeeEditRequest(Messages request) {
        super(request.name());
    }

    public EmployeeEditRequest(String request, Employee employeeToEdit) {
        super(request);
        this.employeesToEdit = new LinkedList<>();
        employeesToEdit.add(employeeToEdit);
    }

    public EmployeeEditRequest(String request, List<Employee> employeesToEdit) {
        super(request);
        this.employeesToEdit = employeesToEdit;
    }

    public List<Employee> getAllEmployeesToEdit() {
        return employeesToEdit;
    }
}
