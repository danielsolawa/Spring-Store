package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.Order;
import com.danielsolawa.storeauth.dtos.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderDtoToOrder(OrderDto orderDto);
    OrderDto orderToOrderDto(Order order);
}
