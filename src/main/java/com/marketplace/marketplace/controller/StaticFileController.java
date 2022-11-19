package com.marketplace.marketplace.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("static")
public class StaticFileController {
    private final String imageFilePath ;

    public StaticFileController(@Value("${image.file.path}") String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    @GetMapping(
            value = "/image/{imageName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public byte[] getImageWithMediaType(@PathVariable("imageName") String imageName) throws IOException {
        String path = imageFilePath + File.separator + imageName;
        File img = new File(imageFilePath + File.separator + imageName);
        InputStream in = new FileInputStream(img);

        return IOUtils.toByteArray(in);
    }
}
