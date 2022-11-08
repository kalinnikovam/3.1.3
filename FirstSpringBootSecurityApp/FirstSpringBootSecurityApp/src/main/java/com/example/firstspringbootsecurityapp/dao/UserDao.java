package com.example.firstspringbootsecurityapp.dao;






import com.example.firstspringbootsecurityapp.models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(long id);

    void add(User user);

    void update(Long id, User newUser);

    void delete(long id);

    List<User> getUserByUsername(String username);
}
