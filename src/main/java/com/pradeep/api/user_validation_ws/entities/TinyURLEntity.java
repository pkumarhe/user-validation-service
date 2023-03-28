package com.pradeep.api.user_validation_ws.entities;

import com.pradeep.tinyurlservice.util.HashMapConverterToJSON;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class TinyURLEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long userId;
    private Long companyId;
    private Long projectId;
    @Lob
    private String originalURL;  //c

    private String shortenString;
    private Boolean isRestricted; //c
    @CreatedDate
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate; //c
    @Convert(converter = HashMapConverterToJSON.class)
    @Column( columnDefinition = "json" )
    private Map<String, Object> urlDetails = new HashMap<>(); //c
}