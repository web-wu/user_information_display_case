package com.innosensor.service;

import com.innosensor.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    User login(User user);

    List<User> getUserList();

    void addUser(User user);

    void delUser(String id);

    User updateUser(String id);

    void modifyUser(User user);
}
