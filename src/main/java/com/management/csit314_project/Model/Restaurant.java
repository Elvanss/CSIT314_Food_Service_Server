package com.management.csit314_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Restaurant")
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "restaurant-name")
    @NotEmpty(message = "The restaurant should be declared!")
    private String restaurantName;

    @Column(name = "email") // Represent the restaurant email
    @NotEmpty(message = "This field can not be null")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "cuisine") // Represent the cuisine type {VNese, Chinese, Aussie}
    private String cuisine;

    @Column(name = "open-time")
    private Time openTime;

    @Column(name = "close-time")
    private Time closeTime;

    @Column(name = "isOpened")
    private boolean isOpened;

    @Column(name = "description")
    private String description;

    @OneToOne // Only one address can be assigned to the restaurant
    @JoinColumn(name = "address-id", referencedColumnName = "id")
    private Address address;

    @OneToMany
//            (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "person")
    @JoinColumn(name = "item-id", referencedColumnName = "id")
    // Show the list of items belong to restaurant
    private List<Item> itemList;

    public int getNumberOfItems() {
        return itemList.size();
    }

    public void addItemToList(Item item) {
        this.itemList.add(item);
        item.setRestaurantId(this);
    }

    public void removeAllItems() {
        this.itemList.clear();
    }

    public void removeItem(Item item) {
        this.itemList.remove(item);
    }

}
