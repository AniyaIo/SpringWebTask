package com.example.springwebtask.service;

import com.example.springwebtask.record.ProductsRecord;
import com.example.springwebtask.dao.IProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Override
    public List<ProductsRecord> findAll(){
        return productDao.findAll();
    }

    @Override
    public ProductsRecord findById(int searchId){
        return productDao.findById(searchId);
    }

    @Override
    public List<ProductsRecord> findByName(String searchName, String sortRule) {
        return productDao.findByName(searchName,sortRule);
    }

    @Override
    public int countRecord(){
        return productDao.countRecord();
    }

    @Override
    public int insert(ProductsRecord data){
        return productDao.insert(data);
    }
    @Override
    public int update(ProductsRecord data){
        return productDao.update(data);
    }

    @Override
    public int delete(int id){
        return productDao.delete(id);
    }

}
