package com.example.springwebtask.service;

import com.example.springwebtask.record.ProductsRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<ProductsRecord> findAll();
    ProductsRecord findById(int searchId);
    List<ProductsRecord> findByName(String searchName, String sortRule);
    int countRecord();
    int insert(ProductsRecord data);
    int update(ProductsRecord data);
    int delete(int id);
}
