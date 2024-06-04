package com.example.springwebtask.dao;

import com.example.springwebtask.record.CategoriesRecord;

import java.util.List;

public interface ICategoriesDao {
    List<CategoriesRecord> findAll();
    int insert(CategoriesRecord data);
    int update(CategoriesRecord data);
    int delete(int id);
}
