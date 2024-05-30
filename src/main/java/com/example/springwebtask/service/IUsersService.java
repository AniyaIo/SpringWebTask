package com.example.springwebtask.service;

import com.example.springwebtask.record.UsersRecord;

import java.util.List;

public interface IUsersService {
    List<UsersRecord> findAll();
    UsersRecord findById(int searchId);
    int insert(UsersRecord data);
    int update(UsersRecord data);
    int delete(int id);
    int login(String loginId,String password);
}
