package com.socialmediaplatform.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service class for saving files to Amazon s3.
 */
@Service
@Transactional
public class AwsS3Service {

    public AwsS3Service() {}

    public String saveMultipartFiletoS3(MultipartFile file) {
        return "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
    }
}
