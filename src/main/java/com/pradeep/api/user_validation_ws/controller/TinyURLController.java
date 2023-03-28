package com.pradeep.api.user_validation_ws.controller;

import com.pradeep.api.user_validation_ws.dto.TinyUrlRequestDto;
import com.pradeep.api.user_validation_ws.dto.TinyUrlResponseDto;
import com.pradeep.api.user_validation_ws.entities.TinyURLEntity;
import com.pradeep.api.user_validation_ws.exceptions.*;
import com.pradeep.api.user_validation_ws.service.TinyUrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/tiny-url")
@Tag(name = "tiny-url Api's", description = "tiny-url realted services")
public class TinyURLController {

    @Autowired
    private TinyUrlService tinyUrlService;

    @PostMapping("/company-invite/generate")
    @Operation(summary = "Creates tiny-url", description = "Creates tiny-url", tags = {"tiny-url"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(responseCode = "409", description = "Organization exists"),
            @ApiResponse(responseCode = "500", description = "Server is down, please try few minutes later")
    })
    public ResponseEntity<TinyUrlResponseDto> generateShortLinkForCompanyInvite(@RequestBody @Valid TinyUrlRequestDto tinyUrlDto) throws UnableToProcessRequestException,ResourceExistsException {
        TinyUrlResponseDto urlResponseDto = tinyUrlService.generateShortLinkForCompanyInvite(tinyUrlDto);
        return new ResponseEntity<TinyUrlResponseDto>(urlResponseDto, HttpStatus.OK);
    }

    @PostMapping("/project-invite/generate")
    @Operation(summary = "Creates tiny-url", description = "Creates tiny-url", tags = {"tiny-url"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(responseCode = "409", description = "Organization exists"),
            @ApiResponse(responseCode = "500", description = "Server is down, please try few minutes later")
    })
    public ResponseEntity<TinyUrlResponseDto> generateShortLinkForProjectInvite(@RequestBody @Valid TinyUrlRequestDto tinyUrlDto) throws UnableToProcessRequestException {
        TinyUrlResponseDto urlResponseDto = tinyUrlService.generateShortLinkForProjectInvite(tinyUrlDto);
        return new ResponseEntity<TinyUrlResponseDto>(urlResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{shorString}")
    @Operation(summary = "Get short url details by shorString", description = "Get short url details by shorString", tags = {"tiny-url" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Data not found"),
            @ApiResponse(responseCode = "500", description = "Server is down, please try few minutes later")
    })
    public ResponseEntity<?> getURLDetailsByShortString(@PathVariable(name = "shorString") String shorString)
            throws IOException, InvalidUrlException, UrlExpiredException,ResourceNotFoundException,BadRequestException {
        if (shorString.isEmpty()) {
            throw new InvalidUrlException("Invalid Url");
        }
        TinyURLEntity urlToRet = tinyUrlService.getURLDetailsByShortString(shorString);
       if (urlToRet.getExpirationDate().isBefore(LocalDateTime.now())) {
           tinyUrlService.deleteShortLink(urlToRet);
           throw new UrlExpiredException("Url Expired. Please try generating a fresh one.");
        }
        TinyUrlResponseDto tinyUrlResponseDto=new TinyUrlResponseDto();
        BeanUtils.copyProperties(urlToRet, tinyUrlResponseDto);
        return new ResponseEntity<TinyUrlResponseDto>(tinyUrlResponseDto, HttpStatus.OK);
    }
}