package com.example.springwebtask.dao;

import com.example.springwebtask.record.UsersRecord;

import java.util.List;

public interface IUsersDao {
    List<UsersRecord> findAll();
    UsersRecord findById(int searchId);
    int findByAccount(String loginId,String password);
    int insert(UsersRecord data);
    int update(UsersRecord data);
    int delete(int id);
}
