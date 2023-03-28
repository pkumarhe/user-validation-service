package com.pradeep.api.user_validation_ws.dto;


import com.pradeep.api.user_validation_ws.enums.VerificationContext;
import lombok.Data;

@Data
public class ValidateOtpRequestDto {
    private Long userid;
    private String otp;
    private VerificationContext verificationContext;
}
