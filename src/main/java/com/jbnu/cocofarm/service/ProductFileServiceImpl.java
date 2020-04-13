package com.jbnu.cocofarm.service;

import com.jbnu.cocofarm.domain.utility.ProductFile;
import com.jbnu.cocofarm.domain.utility.ProductFileRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductFileServiceImpl implements ProductFileService {

    private ProductFileRepository repo;

    @Override
    public void saveFile(ProductFile productFile) {
        repo.save(productFile);
        System.out.println("저장 성공!");
    }

}