package com.tech.challenge.openclose.service.service.impl;

import com.tech.challenge.openclose.service.model.dto.RestaurantDTO;
import com.tech.challenge.openclose.service.model.mapper.RestaurantMapper;
import com.tech.challenge.openclose.service.model.repo.RestaurantRepository;
import com.tech.challenge.openclose.service.service.RestaurantEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantEntityServiceImpl implements RestaurantEntityService {

    @Autowired
    RestaurantRepository repo;
    @Autowired
    RestaurantMapper mapper;

    @Override
    public void updateRestaurantStatus(String restaurantId,Boolean status){
        repo.updateStatusById(restaurantId,status);
    }

    @Override
    public RestaurantDTO getRestaurantById(String id) {
        return mapper.toDto(repo.getById(id));
    }

}
