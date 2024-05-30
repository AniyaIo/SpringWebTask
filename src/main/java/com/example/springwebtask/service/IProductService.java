package com.example.springwebtask.service;

import com.example.springwebtask.form.LoginForm;
import com.example.springwebtask.record.ProductsRecord;

import java.util.List;

public interface IProductService {
    List<ProductsRecord> findAll();
    ProductsRecord findById(int searchId);
    int insert(ProductsRecord data);
    int update(ProductsRecord data);
    int delete(int id);
}
