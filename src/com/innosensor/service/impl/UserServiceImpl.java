package com.innosensor.service.impl;

import com.innosensor.dao.UserDao;
import com.innosensor.dao.impl.UserDaoImpl;
import com.innosensor.entity.User;
import com.innosensor.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public void addUser(User user) {
        userDao.addUSer(user);
    }

    @Override
    public void delUser(String id) {
        userDao.delUser(Integer.parseInt(id));
    }

    @Override
    public User updateUser(String id) {
        return userDao.updateUser(Integer.parseInt(id));
    }

    @Override
    public void modifyUser(User user) {
        userDao.modifyUser(user);
    }
}
