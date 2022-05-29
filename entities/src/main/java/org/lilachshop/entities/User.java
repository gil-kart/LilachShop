package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
@Entity
@Table(name = "Users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }


    protected User() {}



    String userName;
    String userPassword;

    public Long getId() {
        return id;
    }
}