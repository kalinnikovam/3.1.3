package com.example.firstspringbootsecurityapp.service;

import com.example.firstspringbootsecurityapp.dao.RoleDao;
import com.example.firstspringbootsecurityapp.dao.UserDao;
import com.example.firstspringbootsecurityapp.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {

        this.roleDao = roleDao;
    }


    @Override
    public List<Role> allRoles() {
        return roleDao.allRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public List<Role> getRoleByNames(String[] names) {
        List<Role> roleList = new ArrayList<>();
        for (String name:names) {
            roleList.add(getRoleByName(name));
        }
        return roleList;
    }
}
