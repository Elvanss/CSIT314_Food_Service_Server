package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.OrderDTO;
import com.management.csit314_project.Model.Order;
import org.springframework.core.convert.converter.Converter;

public class OrderMapper implements Converter<Order, OrderDTO> {

    @Override
    public OrderDTO convert(Order order) {
        OrderDTO res = new OrderDTO();
        res.setId(order.getId());
        res.setAddress(order.getAddress());
        res.setDeliveredTime(order.getDeliveredTime());
        res.setStatus(order.getStatus());
        res.setCreatedAt(order.getCreatedAt());
        res.setTotalPrice(order.getTotalPrice());
        res.setUserId(order.getUserId());

        return res;
    }
}
