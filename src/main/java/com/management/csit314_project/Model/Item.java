package com.management.csit314_project.Model;

import com.management.csit314_project.Model.Type.ItemCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "item-name")
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "isAvailable")
    private boolean isAvailable;

    @Column(name = "price")
    private Long price;

    @Column(name = "itemCategory")
    private ItemCategory itemCategory;

    @ManyToOne
    @JoinColumn(name = "restaurant-id", referencedColumnName = "id")
    // The item which is belong to restaurant
    private Restaurant restaurantId;


    public Item(Integer id,
                String itemName,
                String description,
                boolean available,
                Long price,
                ItemCategory itemCategory) {

        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.isAvailable = available;
        this.price = price;
        this.itemCategory = itemCategory;
    }
}
