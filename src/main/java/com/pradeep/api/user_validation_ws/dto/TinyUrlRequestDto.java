package com.pradeep.api.user_validation_ws.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class TinyUrlRequestDto {
    @NotNull @NotEmpty
    private String originalURL;
    private Long userId;
    private Long companyId;
    private Long projectId;
    private String expirationDate;
    private Boolean isRestricted;
    private Map<String, Object> urlDetails = new HashMap<>();
}
