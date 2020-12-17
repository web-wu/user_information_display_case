package com.innosensor.dao.impl;

import com.innosensor.dao.UserDao;
import com.innosensor.entity.User;
import com.innosensor.utils.JdbcUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtil.getDateSource());
    @Override
    public User login(User user) {
        try {
            String sql = "select * from users where name = ? and email = ?";
            User user1 = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getName(), user.getEmail());
            return user1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getUserList() {
        try {
            String sql = "select * from users";
            List<User> userList = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUSer(User user) {
        String sql = "insert into users values(null,?,?,?,?,?)";
        template.update(sql,user.getName(),user.getAge(),user.getGender(),user.getEmail(),user.getAddress());
    }

    @Override
    public void delUser(int id) {
        String sql = "delete from users where id = ?";
        template.update(sql,id);
    }

    @Override
    public User updateUser(int id) {
        try {
            String sql = "select * from users where id = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void modifyUser(User user) {
        try {
            String sql = "update users set name = ?, age = ?, gender = ?, email = ?, address = ? where id = ?";
            template.update(sql, user.getName(), user.getAge(), user.getGender(), user.getEmail(), user.getAddress(), user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
