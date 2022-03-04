package com.tech.challenge.openclose.service.service;

import com.tech.challenge.openclose.service.model.dto.RestaurantDTO;

public interface RestaurantEntityService {
    void updateRestaurantStatus(String restaurantId, Boolean status);
    RestaurantDTO getRestaurantById(String id);
}
