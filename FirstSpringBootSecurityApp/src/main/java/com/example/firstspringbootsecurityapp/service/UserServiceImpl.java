package com.example.firstspringbootsecurityapp.service;



import com.example.firstspringbootsecurityapp.dao.UserDao;
import com.example.firstspringbootsecurityapp.exception_handling.BadDataException;
import com.example.firstspringbootsecurityapp.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional

public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    public void add(User user) {
        userDao.add(user);
    }

    public void update(Long id, User newUser) {
        userDao.update(id, newUser);
    }

    public void delete(long id) {
        userDao.delete(id);
    }

    public List<User> getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> user = userDao.getUserByUsername(username);

        if (user.isEmpty()) {
            throw new BadDataException("Wrong username or password. Try again");
        }
        return user.get(0);
    }
}
