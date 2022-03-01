package com.tech.challenge.openclose.service.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private String id;
    private String name;
    private String description;
    private String address;
    private Boolean isOpen;
}
