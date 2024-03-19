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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "feedback-id", referencedColumnName = "id")
    private FeedBack feedId;

    @OneToOne
    @JoinColumn(name = "item-id", referencedColumnName = "id")
    private Item itemId;


}
