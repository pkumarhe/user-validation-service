package com.pradeep.api.user_validation_ws.repository;


import com.pradeep.api.user_validation_ws.entities.TinyURLEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TinyURLRepository extends JpaRepository<TinyURLEntity, Long> {
    Optional<TinyURLEntity> findByShortenString(String shortenString);

    Optional<TinyURLEntity> findByUserIdAndCompanyId(Long userId, Long companyId);




}