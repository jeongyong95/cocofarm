package com.jbnu.cocofarm.service;

import com.jbnu.cocofarm.domain.utility.ProductFile;
import com.jbnu.cocofarm.domain.utility.ProductFileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductFileServiceImpl implements ProductFileService {

    @Autowired
    private ProductFileRepository repo;

    @Override
    public void saveFile(ProductFile productFile) {
        repo.save(productFile);
        System.out.println("저장 성공!");
    }

}