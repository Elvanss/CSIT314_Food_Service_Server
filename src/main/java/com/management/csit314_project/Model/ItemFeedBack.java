package com.management.csit314_project.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "ItemFeedBack")
public class ItemFeedBack {

    @Id
    @OneToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "feedback-id", referencedColumnName = "id")
    private FeedBack id;

    @OneToOne
    @JoinColumn(name = "item-id", referencedColumnName = "id")
    private Item itemId;


}
