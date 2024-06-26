package com.example.springwebtask.service;

import com.example.springwebtask.record.CategoriesRecord;

import java.util.List;

public interface ICategoriesService {
    List<CategoriesRecord> findAll();
    String getNameFromId(int id);
    int insert(CategoriesRecord data);
    int update(CategoriesRecord data);
    int delete(int id);
}
