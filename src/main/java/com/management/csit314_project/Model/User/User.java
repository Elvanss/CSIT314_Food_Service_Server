package com.management.csit314_project.Model.User;


import com.management.csit314_project.Model.User.Category.MembershipUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
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

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    @NotEmpty(message = "This area can not be null!")
    private String email;

    @Column(name = "phone")
    private Long phoneNumber;

    @Column(name = "is_member")
    private boolean isMember; //To identify this user is non/membership

    @OneToMany(mappedBy = "user")
    @Column(name = "role")
//    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRoles> roleUsers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "membership_id", referencedColumnName = "memId", unique = true,
            foreignKey = @ForeignKey(name = "fk_membership_user"))
    private MembershipUser membership;


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
