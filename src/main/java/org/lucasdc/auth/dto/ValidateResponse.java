package org.lucasdc.auth.dto;

import lombok.Data;

@Data
public class ValidateResponse {
    private Boolean valid = true;
    private String message;
}
