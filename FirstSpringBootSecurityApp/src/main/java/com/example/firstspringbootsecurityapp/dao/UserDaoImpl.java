package com.example.firstspringbootsecurityapp.dao;



import com.example.firstspringbootsecurityapp.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(Long id, User newUser) {
        User oldUser = getUserById(id);
        oldUser.setUsername(newUser.getUsername());
        oldUser.setAge(newUser.getAge());
        oldUser.setPassword(newUser.getPassword());
    }

    @Override
    public void delete(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public List<User> getUserByUsername(String username) {
        TypedQuery<User> listUser = entityManager.createQuery(
                "select u from User u where u.username = :username", User.class);
        listUser.setParameter("username", username);
        System.out.println(listUser.getResultList());
        return listUser.getResultList();
    }
}
