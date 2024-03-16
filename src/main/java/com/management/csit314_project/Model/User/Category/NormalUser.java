package com.management.csit314_project.Model.User.Category;

import com.management.csit314_project.Model.Type.MembershipType;
import com.management.csit314_project.Model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Normal")
public class NormalUser implements Serializable {

    @Id
    @OneToOne // One customer can use this function while paying the membership
    // Cannot assigned automatically
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User id;

    @Column(name = "membershipType")
    // Return the unstable String none
    private MembershipType membershipType;

}
