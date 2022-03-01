package com.tech.challenge.frontend.service.service;

import com.tech.challenge.frontend.service.model.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantEntityService {

    void updateRestaurantStatus(String restaurantId, Boolean status);
    List<RestaurantDTO> findAll();
    List<RestaurantDTO> findByStatus(boolean status);
}
