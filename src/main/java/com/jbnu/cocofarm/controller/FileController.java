package com.jbnu.cocofarm.controller;

import java.io.IOException;

import com.jbnu.cocofarm.domain.utility.ProductFile;
import com.jbnu.cocofarm.service.ProductFileService;
import com.jbnu.cocofarm.service.S3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class FileController {

    private S3Service s3Service;

    @Autowired
    private ProductFileService productFileService;

    @GetMapping("/fileUpload")
    public ModelAndView dispWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("fileUpload");
        return modelAndView;
    }

    @PostMapping("/fileToBucket")
    public ModelAndView execWrite(ProductFile productFile, MultipartFile file) throws IOException {
        String imgPath = s3Service.upload(file);
        productFile.setFilePath(imgPath);
        productFileService.saveFile(productFile);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/fileUpload");

        return modelAndView;
    }
}