package com.gonzalodev.saiyajinstore.backend.infrastructure.mapper;

import com.gonzalodev.saiyajinstore.backend.domain.model.Order;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.OrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {OrderProductMapper.class})
public interface OrderMapper {
    @Mappings(
    {
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "dateCreated", target = "dateCreated"),
        @Mapping(source = "orderProducts", target = "orderProducts"),
        @Mapping(source = "state", target = "state"),
            @Mapping(source = "userEntity.id", target = "userId")
    })
    Order toOrder(OrderEntity orderEntity);
    Iterable<Order> toOrderList(Iterable<OrderEntity> orderEntities);

    @InheritInverseConfiguration
    OrderEntity toOrderEntity(Order order);

}
