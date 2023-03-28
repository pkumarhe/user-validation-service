package com.pradeep.api.user_validation_ws.service;

import com.pradeep.api.user_validation_ws.dto.*;
import com.pradeep.api.user_validation_ws.exceptions.BadRequestException;
import com.pradeep.api.user_validation_ws.exceptions.ResourceNotFoundException;


public interface OtpService {
    OtpResponseDto generateOTP(OtpRequestDto otpRequestDto);

    OtpResponseDto reGenerateOTP(OtpRequestDto otpRequestDto) throws ResourceNotFoundException;

    Response1 validateOTP(ValidateOtpRequestDto validateOtpRequestDto) throws ResourceNotFoundException, BadRequestException;
}
