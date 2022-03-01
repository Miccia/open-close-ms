package com.tech.challenge.frontend.service.model.mapper;

import com.tech.challenge.frontend.service.model.dto.RestaurantDTO;
import com.tech.challenge.frontend.service.model.entity.RestaurantEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface RestaurantMapper {

    RestaurantDTO toDto(RestaurantEntity entity);
    RestaurantEntity toEntity(RestaurantDTO dto);
}
