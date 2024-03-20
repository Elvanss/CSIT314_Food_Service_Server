package com.management.csit314_project.Mapper;

import com.management.csit314_project.Model.ItemFeedBack;
import com.management.csit314_project.DTO.ItemFeedBackDTO;
import com.management.csit314_project.DTO.FeedBackDTO;
import com.management.csit314_project.DTO.ItemDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemFeedBackMapper implements Converter<ItemFeedBack, ItemFeedBackDTO> {

    private final FeedbackMapper feedbackMapper;
    private final ItemMapper itemMapper;

    public ItemFeedBackMapper(FeedbackMapper feedbackMapper, ItemMapper itemMapper) {
        this.feedbackMapper = feedbackMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemFeedBackDTO convert(ItemFeedBack itemFeedBack) {

        return new ItemFeedBackDTO(
                itemFeedBack.getId(),
                itemFeedBack.getFeedId() != null
                        ? this.feedbackMapper.convert(itemFeedBack.getFeedId())
                        : null,
                itemFeedBack.getItemId() != null
                        ? this.itemMapper.convert(itemFeedBack.getItemId())
                        : null
        );
    }
}
