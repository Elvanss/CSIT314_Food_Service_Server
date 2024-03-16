package com.management.csit314_project.Model.User.Category;

import com.management.csit314_project.Model.Type.MembershipType;
import com.management.csit314_project.Model.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Membership")
public class MembershipUser implements Serializable {

    @Id
    @OneToOne // One customer can use this function while paying the membership
    // Cannot assigned automatically
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User id;

    @Column(name = "expiry-time")
    @NotEmpty(message = "This column can not be null!")
    //This Column presents the exipryTime which is assigned to each package
    private Timestamp expiryDateTime;

    @Column(name = "type")
    private MembershipType membershipType;


















}
