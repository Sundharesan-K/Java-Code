package com.project.clone_project.service;

import com.project.clone_project.exception.SpringRedditException;
import com.project.clone_project.model.RefreshToken;
import com.project.clone_project.repository.RefreshTokenRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {
    private final RefreshTokenRepo refreshTokenRepo;

    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepo.save(refreshToken);
    }

   public void validateRefreshToken(String token){
        refreshTokenRepo.findByToken(token)
                .orElseThrow(()->new SpringRedditException("Invalid refresh token"));
    }

   public void deleteRefreshToken(String token){
        log.info("Delete Process");
        refreshTokenRepo.deleteByToken(token);
   }
}
