package com.management.csit314_project.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ItemFeedBackDTO implements Serializable {
    private FeedBackDTO id; // Assuming you have a FeedBackDTO class
    private ItemDTO item; // Assuming you have an ItemDTO class

    // Constructor, getters, and setters
}

