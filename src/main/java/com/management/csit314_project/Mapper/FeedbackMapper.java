package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.FeedBackDTO;
import com.management.csit314_project.Mapper.UserMapper.UserMapper;
import com.management.csit314_project.Model.FeedBack;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FeedbackMapper implements Converter<FeedBack, FeedBackDTO> {

    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;

    @Override
    public FeedBackDTO convert(FeedBack feedBack) {
        return new FeedBackDTO(feedBack.getId(),
                                feedBack.getUser() != null
                                        ? this.userMapper.convert(feedBack.getUser())
                                        : null,
                                feedBack.getRestaurant() != null
                                        ? feedBack.getRestaurant()
                                        : null,
                                feedBack.getContent(),
                                feedBack.getRating(),
                                feedBack.getPostDateTime());
    }
}
