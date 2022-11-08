package com.example.firstspringbootsecurityapp.service;

import com.example.firstspringbootsecurityapp.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> allRoles();

    Role getRoleByName(String name);

    List<Role> getRoleByNames(String[] names);

}
