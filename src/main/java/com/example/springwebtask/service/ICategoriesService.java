package com.example.springwebtask.service;

import com.example.springwebtask.record.CategoriesRecord;

import java.util.List;

public interface ICategoriesService {
    List<CategoriesRecord> findAll();
    int insert(CategoriesRecord data);
    int update(CategoriesRecord data);
    int delete(int id);
}
