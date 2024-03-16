package com.management.csit314_project.Model;

import com.management.csit314_project.Model.Type.Rating;
import com.management.csit314_project.Model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FeedBack")
public class FeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user-id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant-id", referencedColumnName = "id")
    private Restaurant restaurant;

    @Column(name = "content")
    private String content;

    @Column(name = "rating") // From 1 to 5 Stars
    private Rating rating;

    @Column(name = "post-date-time")
    private LocalDateTime postDateTime;



}
