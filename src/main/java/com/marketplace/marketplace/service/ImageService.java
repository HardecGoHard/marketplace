package com.marketplace.marketplace.service;

import com.marketplace.marketplace.exception.AvatarUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {
    private final String imageFilePath ;

    public ImageService(@Value("${image.file.path}") String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

   public String saveImage(MultipartFile image){
       String fileName = "";
       try {

           File dir = new File(imageFilePath);

           if (!dir.exists())
               dir.mkdirs();

           String ext = FilenameUtils.getExtension(image.getOriginalFilename());

           fileName = String.format("%s.%s", UUID.randomUUID().toString(), ext);
           File serverFile = new File(dir.getAbsolutePath()
                   + File.separator + fileName);


           Files.write(serverFile.toPath(), image.getBytes());

       } catch (Exception e) {
           throw new AvatarUploadException();
       }

     return fileName;
   }

    public void deleteFileImage(String fileName) {
        if (Objects.isNull(fileName) || fileName.isEmpty())
            return;

        File file = new File(imageFilePath + File.separator + fileName);

        try {
            Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
        }
    }

}
