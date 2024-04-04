package com.management.csit314_project.Model;


import com.management.csit314_project.Model.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "address-id", referencedColumnName = "id")
    @NotEmpty(message = "Address is required")
    private Address address;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "delivered_time")
    private Date deliveredTime;

    @Column(name = "total-price")
    @NotEmpty(message = "Total price is required")
    private Long totalPrice;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "User-id", referencedColumnName = "id")
    private User userId;





}
