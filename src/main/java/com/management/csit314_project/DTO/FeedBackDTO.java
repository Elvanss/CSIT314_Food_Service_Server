package com.management.csit314_project.DTO;

import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Model.Type.Rating;
import com.management.csit314_project.Model.Restaurant; // Import Restaurant (not DTO)
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class FeedBackDTO {

    private Integer id;
    private UserDTO user;
    private Restaurant restaurant;
    private String content;
    private Rating rating;
    private LocalDateTime postDateTime;


}

