package com.management.csit314_project.Model;


import com.management.csit314_project.Model.User.User;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CustomerAddress implements Serializable {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user-id", referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "address-id", referencedColumnName = "id")
    private Address addressId;
}
