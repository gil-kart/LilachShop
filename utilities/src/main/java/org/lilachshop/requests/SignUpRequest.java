package org.lilachshop.requests;

import org.lilachshop.entities.Customer;

public class SignUpRequest extends Request {
    Customer customer;

    public SignUpRequest(Customer customer) {
        super("create new customer");
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
