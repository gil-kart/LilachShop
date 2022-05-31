package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
@Entity
@Table(name = "Employees")
public class Employee extends User implements Serializable {
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    Store store;
    Role role;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public Employee(Store store, Role role,String userName, String userPassword) {
        super();
        this.store = store;
        this.role = role;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    protected Employee() {}

    public Store getStore() {
        return store;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
