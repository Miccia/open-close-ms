package com.tech.challenge.frontend.service.service.impl;

import com.tech.challenge.frontend.service.model.dto.RestaurantDTO;
import com.tech.challenge.frontend.service.model.mapper.RestaurantMapper;
import com.tech.challenge.frontend.service.model.repo.RestaurantRepository;
import com.tech.challenge.frontend.service.service.RestaurantEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<RestaurantDTO> findAll(){
        List<RestaurantDTO> res = new ArrayList<>();
        repo.findAll().forEach(restEntity->{
            res.add(mapper.toDto(restEntity));
        });
        return res;
    }

}
