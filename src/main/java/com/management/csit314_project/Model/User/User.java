package com.management.csit314_project.Model.User;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
// import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first-name")
    private String firstName;

    @Column(name = "last-name")
    private String lastName;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    @NotEmpty(message = "This area can not be null!")
    private String email;

    @Column(name = "phone")
    private Long phoneNumber;

//    @OneToMany // One customer can have multiple address
//    @JoinColumn(name = "address-id", referencedColumnName = "id")
//    private List<CustomerAddress> addressList;
//
//    public void addAddress(CustomerAddress address) {
//        address.setUserOwner(this);
//        this.addressList.add(address);
//    }
//
//    // Return the number of address
//    public Integer numberOfAddress() {
//        return this.addressList.size();
//    }
//
//    public void removeAllAddresses() {
//        this.addressList.stream().forEach(
//                artifact -> artifact.setUserOwner(null));
//        this.addressList = null; // Remove address in for loop
//    }
//
//    public void removeAddress(CustomerAddress addressToBeAssigned) {
//        // Remove address of owner (Only 1).
//        addressToBeAssigned.setUserOwner(null);
//        this.addressList.remove(addressToBeAssigned);
//    }

}
