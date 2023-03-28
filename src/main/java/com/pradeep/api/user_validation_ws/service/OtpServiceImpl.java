package com.pradeep.api.user_validation_ws.service;


import com.pradeep.api.user_validation_ws.dto.OtpRequestDto;
import com.pradeep.api.user_validation_ws.dto.OtpResponseDto;
import com.pradeep.api.user_validation_ws.dto.Response1;
import com.pradeep.api.user_validation_ws.dto.ValidateOtpRequestDto;
import com.pradeep.api.user_validation_ws.entities.Otp;
import com.pradeep.api.user_validation_ws.exceptions.BadRequestException;
import com.pradeep.api.user_validation_ws.exceptions.ResourceNotFoundException;
import com.pradeep.api.user_validation_ws.repository.OtpRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService{
    @Autowired
    OtpRepository otpRepository;
    @Override
    public OtpResponseDto generateOTP(OtpRequestDto otpRequestDto) {
        Otp otp=new Otp();
        BeanUtils.copyProperties(otpRequestDto, otp);
        otp.setOtp(getRandomNumberString());
        otp.setExpiration(LocalDateTime.now().plusMinutes(5));
        Otp savedOtp= otpRepository.save(otp);
        OtpResponseDto otpResponseDto=new OtpResponseDto();
        BeanUtils.copyProperties(savedOtp, otpResponseDto);
        return otpResponseDto;
    }

    @Override
    public OtpResponseDto reGenerateOTP(OtpRequestDto otpRequestDto) throws ResourceNotFoundException {
        Otp savedOtp=otpRepository.findByUseridAndVerificationContext(otpRequestDto.getUserid(),otpRequestDto.getVerificationContext()).orElseThrow(() -> new ResourceNotFoundException("No not found"));;
        savedOtp.setOtp(getRandomNumberString());
        OtpResponseDto otpResponseDto=new OtpResponseDto();
        Otp updatedOtp= otpRepository.save(savedOtp);
        BeanUtils.copyProperties(updatedOtp, otpResponseDto);
        return otpResponseDto;
    }

    @Override
    public Response1 validateOTP(ValidateOtpRequestDto validateOtpRequestDto) throws ResourceNotFoundException, BadRequestException {
        Otp savedOtp=otpRepository.findByUseridAndVerificationContext(validateOtpRequestDto.getUserid(),validateOtpRequestDto.getVerificationContext()).orElseThrow(() -> new ResourceNotFoundException("Invalid request or the otp has expired."));;
        if (savedOtp.getExpiration().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Invalid request or the otp has expired.");
        }
        return new Response1(200,"Sucessfully validated");
    }

    private static String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
}