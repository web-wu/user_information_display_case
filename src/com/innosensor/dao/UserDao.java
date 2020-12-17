package com.innosensor.dao;

import com.innosensor.entity.User;


import java.util.List;

public interface UserDao {
    User login(User user);

    List<User> getUserList();

    void addUSer(User user);

    void delUser(int id);

    User updateUser(int id);

    void modifyUser(User user);
}
