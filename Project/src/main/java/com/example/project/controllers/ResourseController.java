package com.example.project.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/res")
public class ResourseController {
    @GetMapping("/style.css")
    public ResponseEntity<byte[]> getStyle() throws IOException {
        InputStream in=getClass().getResourceAsStream("/style/style.css");
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE,"text/css")
                .body(in.readAllBytes());
    }

    @GetMapping("/image/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
        InputStream in=getClass().getResourceAsStream("/drawable/"+name);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE,"image/jpeg")
                .body(in.readAllBytes());
    }
}
