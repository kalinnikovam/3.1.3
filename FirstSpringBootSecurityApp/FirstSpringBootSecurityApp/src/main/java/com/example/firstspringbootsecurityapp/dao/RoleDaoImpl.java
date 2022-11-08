package com.example.firstspringbootsecurityapp.dao;

import com.example.firstspringbootsecurityapp.models.Role;
import com.example.firstspringbootsecurityapp.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery(
                "select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> listRoles = entityManager.createQuery(
                "select r from Role r where r.name = :name", Role.class);
        listRoles.setParameter("name", name);
        System.out.println(listRoles.getResultList());

        return listRoles.getSingleResult();
    }
}
