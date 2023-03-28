package com.pradeep.api.user_validation_ws.service;

import com.pradeep.api.user_validation_ws.dto.TinyUrlRequestDto;
import com.pradeep.api.user_validation_ws.dto.TinyUrlResponseDto;
import com.pradeep.api.user_validation_ws.entities.TinyURLEntity;

import com.pradeep.api.user_validation_ws.exceptions.BadRequestException;
import com.pradeep.api.user_validation_ws.exceptions.ResourceExistsException;
import com.pradeep.api.user_validation_ws.exceptions.UnableToProcessRequestException;
import com.pradeep.api.user_validation_ws.repository.TinyURLRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static com.pradeep.api.user_validation_ws.service.Temp.UUID_USER_INVITE;

@Service
public class TinyUrlServiceImpl implements TinyUrlService{

    @Value("${configExpirationMinutes}")
    private Long configExpirationMinutes;

    TinyURLRepository tinyURLRepository;
    @Autowired
    public TinyUrlServiceImpl(TinyURLRepository tinyURLRepository){
        this.tinyURLRepository=tinyURLRepository;
    }

    @Override
    public TinyUrlResponseDto generateShortLinkForCompanyInvite(TinyUrlRequestDto tinyUrlDto) throws UnableToProcessRequestException, ResourceExistsException {
        Optional<TinyURLEntity> optionalTinyURLEntity=tinyURLRepository.findByUserIdAndCompanyId(tinyUrlDto.getUserId(),tinyUrlDto.getCompanyId());
        if(optionalTinyURLEntity.isPresent()){
            TinyURLEntity savedTinyURLEntity=optionalTinyURLEntity.get();
            savedTinyURLEntity.setShortenString(UUID.randomUUID().toString().substring(28));
            savedTinyURLEntity.setCreationDate(LocalDateTime.now());
            savedTinyURLEntity.setExpirationDate(getExpirationDate(savedTinyURLEntity.getExpirationDate().toString(),savedTinyURLEntity.getCreationDate()));
            TinyUrlResponseDto tinyUrlResponseDto=new TinyUrlResponseDto();
            BeanUtils.copyProperties(savedTinyURLEntity, tinyUrlResponseDto);
            tinyUrlResponseDto.setUuid(UUID_USER_INVITE.get(new Random().nextInt(UUID_USER_INVITE.size())));
            return tinyUrlResponseDto;
        }
        TinyUrlResponseDto tinyUrlToRet = persistTinyLink(tinyUrlDto);
        return tinyUrlToRet;
    }

    @Override
    public TinyUrlResponseDto generateShortLinkForProjectInvite(TinyUrlRequestDto tinyUrlDto) {
        return null;
    }

    @Override
    public TinyURLEntity getURLDetailsByShortString(String shorString) throws BadRequestException {
        TinyURLEntity tinyURLEntity=tinyURLRepository.findByShortenString(shorString).orElseThrow(() -> new BadRequestException("Invalid request"));
        return tinyURLEntity;
    }

    @Override
    public void deleteShortLink(TinyURLEntity urlToRet) {
        tinyURLRepository.delete(urlToRet);
    }



    private TinyUrlResponseDto persistTinyLink(TinyUrlRequestDto tinyUrlRequestDto) {
        TinyURLEntity tinyURLEntity=new TinyURLEntity();
        BeanUtils.copyProperties(tinyUrlRequestDto, tinyURLEntity);
        tinyURLEntity.setShortenString( UUID.randomUUID().toString().substring(28));
        tinyURLEntity.setCreationDate(LocalDateTime.now());
        if(tinyUrlRequestDto.getIsRestricted()){
            tinyURLEntity.setExpirationDate(getExpirationDate(tinyUrlRequestDto.getExpirationDate(),tinyURLEntity.getCreationDate()));
        }
        TinyURLEntity savedTinyURLEntity=tinyURLRepository.save(tinyURLEntity);
        TinyUrlResponseDto tinyUrlResponseDto=new TinyUrlResponseDto();
        BeanUtils.copyProperties(savedTinyURLEntity, tinyUrlResponseDto);
        tinyUrlResponseDto.setUuid(UUID_USER_INVITE.get(new Random().nextInt(UUID_USER_INVITE.size())));
        return tinyUrlResponseDto;
    }

    private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
        if (expirationDate.isEmpty()) {
            if (configExpirationMinutes != null && (configExpirationMinutes >= 1 && configExpirationMinutes <= 10080)) {
                return creationDate.plusMinutes(configExpirationMinutes);
            } else {
                return creationDate.plusMinutes(30);
            }
        }
        LocalDateTime expirationDateToRet = LocalDateTime.parse(expirationDate);
        return expirationDateToRet;
    }
}