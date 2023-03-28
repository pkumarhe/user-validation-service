package com.pradeep.api.user_validation_ws.dto;


import com.pradeep.api.user_validation_ws.enums.VerificationContext;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OtpRequestDto {
    @NotNull
    private Long userid;
    private VerificationContext verificationContext;
}
