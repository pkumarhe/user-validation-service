package com.pradeep.api.user_validation_ws.entities;


import com.pradeep.api.user_validation_ws.enums.VerificationContext;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
///@Table(uniqueConstraints = {@UniqueConstraint(name = "UniqueOTP",  columnNames = {"userid", "verificationContext"})})
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid;
    private String otp;
    private VerificationContext verificationContext;
    private LocalDateTime expiration;
}
