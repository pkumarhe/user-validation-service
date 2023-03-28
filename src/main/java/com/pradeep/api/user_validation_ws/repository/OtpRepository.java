package com.pradeep.api.user_validation_ws.repository;


import com.pradeep.api.user_validation_ws.entities.Otp;
import com.pradeep.api.user_validation_ws.enums.VerificationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByUseridAndVerificationContext(Long userid, VerificationContext verificationContext);
}
