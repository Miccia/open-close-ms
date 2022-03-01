package com.tech.challenge.frontend.service.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorDTO {

    private String error;
    private String message;

}
