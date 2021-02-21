package com.example.bojta_mk.web;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FetchFilesController {

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
        Path path = Paths.get("C:\\Users\\38978\\IdeaProjects\\bojta_mk\\" + fileName); // tuka treba da se smeni patekata
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf")) // tuka treba da se smeni za kakov file type e
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
