package com.example.firstspringbootsecurityapp.dao;

import com.example.firstspringbootsecurityapp.models.Role;

import java.util.List;

public interface RoleDao {

    List<Role> allRoles();

    Role getRoleByName(String name);

}
