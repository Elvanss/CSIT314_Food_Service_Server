package com.management.csit314_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemFeedBackDTO implements Serializable {
    private FeedBackDTO id; // Assuming you have a FeedBackDTO class
    private ItemDTO item; // Assuming you have an ItemDTO class

    public ItemFeedBackDTO(Integer itemFeedBackId, FeedBackDTO id, ItemDTO item) {
        this.id = id;
        this.item = item;
    }

    // Constructor, getters, and setters
}

