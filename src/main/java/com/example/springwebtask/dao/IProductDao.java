package com.example.springwebtask.dao;

import com.example.springwebtask.record.ProductsRecord;

import java.util.List;

public interface IProductDao {
    List<ProductsRecord> findAll();
    ProductsRecord findById(int searchId);
    int insert(ProductsRecord data);
    int update(ProductsRecord data);
    int delete(int id);
}
