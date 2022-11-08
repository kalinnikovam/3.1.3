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
//        user.setId((long) (getAllUsers().size() + 1));
        entityManager.persist(user);
    }

    @Override
    public void update(Long id, User newUser) {
        User oldUser = getUserById(id);
        oldUser.setFirstname(newUser.getFirstname());
        oldUser.setLastname(newUser.getLastname());
        oldUser.setAge(newUser.getAge());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setRoles(newUser.getRoles());
    }

    @Override
    public void delete(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public List<User> getUserByUsername(String username) {
        TypedQuery<User> listUser = entityManager.createQuery(
                "select u from User u where u.email = :email", User.class);
        listUser.setParameter("email", username);
        System.out.println(listUser.getResultList());
        return listUser.getResultList();
    }
}
