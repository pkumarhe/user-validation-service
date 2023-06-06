package com.pradeep.api.user_validation_ws.controller;

import com.pradeep.api.user_validation_ws.dto.*;
import com.pradeep.api.user_validation_ws.service.OtpService;
import com.pradeep.api.user_validation_ws.exceptions.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/otp")
@Tag(name = "otp Api's", description = "otp related services")
public class OTPController {

    @Autowired
    private OtpService otpService;
    @PostMapping("/generate")
    @Operation(summary = "Creates tiny-url", description = "Creates tiny-url", tags = {"tiny-url"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(responseCode = "409", description = "Organization exists"),
            @ApiResponse(responseCode = "500", description = "Server is down, please try few minutes later")
    })
    public ResponseEntity<OtpResponseDto> generateOTP(@RequestBody @Valid OtpRequestDto otpRequestDto) throws UnableToProcessRequestException, ResourceExistsException {
        OtpResponseDto otpResponseDto = otpService.generateOTP(otpRequestDto);
        return new ResponseEntity<OtpResponseDto>(otpResponseDto, HttpStatus.OK);
    }

    @PostMapping("/re-generate")
    @Operation(summary = "Creates tiny-url", description = "Creates tiny-url", tags = {"tiny-url"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(responseCode = "409", description = "Organization exists"),
            @ApiResponse(responseCode = "500", description = "Server is down, please try few minutes later")
    })
    public ResponseEntity<OtpResponseDto> reGenerateOTP(@RequestBody @Valid OtpRequestDto otpRequestDto) throws UnableToProcessRequestException, ResourceExistsException, ResourceNotFoundException {
        OtpResponseDto otpResponseDto = otpService.reGenerateOTP(otpRequestDto);
        return new ResponseEntity<OtpResponseDto>(otpResponseDto, HttpStatus.OK);
    }

    @PostMapping("/validate")
    @Operation(summary = "Creates tiny-url", description = "Creates tiny-url", tags = {"tiny-url"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(responseCode = "409", description = "Organization exists"),
            @ApiResponse(responseCode = "500", description = "Server is down, please try few minutes later")
    })
    public ResponseEntity<Response1> validateOTP(@RequestBody @Valid ValidateOtpRequestDto validateOtpRequestDto) throws UnableToProcessRequestException, ResourceExistsException, ResourceNotFoundException, BadRequestException {
        Response1 otpResponseDto = otpService.validateOTP(validateOtpRequestDto);
        return new ResponseEntity<Response1>(otpResponseDto, HttpStatus.OK);
    }
}
