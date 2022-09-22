package com.example.apachepoiexample.controller;

import com.example.apachepoiexample.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/media")
public class MediaController {
    private final FileService fileService;

    @PostMapping("/read-file")
    public void readFile(@RequestParam("file") MultipartFile file){
        fileService.readFile(file);
    }
}
