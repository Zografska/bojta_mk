package com.example.bojta_mk.service.impl;

import com.example.bojta_mk.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadServiceImpl implements UploadService  {
    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";
    @Override
    public String upload(MultipartFile file, RedirectAttributes attributes) throws IOException {

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String p=UPLOAD_DIR + fileName;
        // save the file on the local file system
        Path path = Paths.get(p);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return p;
    }
}
