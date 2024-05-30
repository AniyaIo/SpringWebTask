package com.example.springwebtask.service;

import com.example.springwebtask.dao.IProductDao;
import com.example.springwebtask.dao.IUsersDao;
import com.example.springwebtask.record.UsersRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements IUsersService {
    @Autowired
    private IUsersDao usersDao;
    @Override
    public List<UsersRecord> findAll(){
        return usersDao.findAll();
    }

    @Override
    public UsersRecord findById(int searchId){
        return usersDao.findById(searchId);
    }

    @Override
    public int insert(UsersRecord data){
        return usersDao.insert(data);
    }
    @Override
    public int update(UsersRecord data){
        return usersDao.update(data);
    }

    @Override
    public int delete(int id){
        return usersDao.delete(id);
    }

    @Override
    public int login(String loginId,String password){
        return usersDao.findByAccount(loginId, password);
    }
}
