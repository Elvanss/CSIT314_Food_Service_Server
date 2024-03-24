package com.management.csit314_project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RestaurantAddress implements Serializable {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "restaurant-id", referencedColumnName = "id")
    private Restaurant restaurantId ;

    @ManyToOne
    @JoinColumn(name = "address-id", referencedColumnName = "id")
    private Address addressId;
}
