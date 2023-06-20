package com.program.youtubeclone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService{
    @Override
    public String uploadFile(MultipartFile file){

    }
}
