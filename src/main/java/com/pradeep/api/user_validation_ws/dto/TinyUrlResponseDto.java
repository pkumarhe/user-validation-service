package com.pradeep.api.user_validation_ws.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class TinyUrlResponseDto {
    private String originalURL;
    private String shortenString;
    private UUID uuid;
    private LocalDateTime expirationDate;
    private Boolean isRestricted;
    private Map<String, Object> urlDetails = new HashMap<>();

}
