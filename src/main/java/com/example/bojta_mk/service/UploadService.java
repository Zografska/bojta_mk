package com.example.bojta_mk.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

public interface UploadService {
    String upload(MultipartFile file, RedirectAttributes attributes) throws IOException;
}
