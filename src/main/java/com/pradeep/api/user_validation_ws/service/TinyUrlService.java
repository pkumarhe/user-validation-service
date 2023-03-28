package com.pradeep.api.user_validation_ws.service;

import com.pradeep.api.user_validation_ws.dto.TinyUrlRequestDto;
import com.pradeep.api.user_validation_ws.dto.TinyUrlRequestDto;
import com.pradeep.api.user_validation_ws.dto.TinyUrlResponseDto;
import com.pradeep.api.user_validation_ws.entities.TinyURLEntity;
import com.pradeep.api.user_validation_ws.exceptions.BadRequestException;
import com.pradeep.api.user_validation_ws.exceptions.ResourceExistsException;
import com.pradeep.api.user_validation_ws.exceptions.UnableToProcessRequestException;

public interface TinyUrlService {
    TinyUrlResponseDto generateShortLinkForCompanyInvite(TinyUrlRequestDto tinyUrlDto) throws UnableToProcessRequestException, ResourceExistsException;

    TinyURLEntity getURLDetailsByShortString(String shorString) throws BadRequestException;

    void deleteShortLink(TinyURLEntity urlToRet);

    TinyUrlResponseDto generateShortLinkForProjectInvite(TinyUrlRequestDto tinyUrlDto);
}
