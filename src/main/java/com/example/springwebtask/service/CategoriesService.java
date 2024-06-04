package com.example.springwebtask.service;

import com.example.springwebtask.dao.ICategoriesDao;
import com.example.springwebtask.record.CategoriesRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService implements ICategoriesService {
    @Autowired
    private ICategoriesDao categoriesDao;

    @Override
    public List<CategoriesRecord> findAll() {
        return categoriesDao.findAll();
    }

    @Override
    public int insert(CategoriesRecord data) {
        return categoriesDao.insert(data);
    }

    @Override
    public int update(CategoriesRecord data) {
        return categoriesDao.update(data);
    }

    @Override
    public int delete(int id) {
        return categoriesDao.delete(id);
    }
}
