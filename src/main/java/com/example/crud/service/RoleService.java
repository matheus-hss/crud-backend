package com.example.crud.service;

import com.example.crud.model.Role;
import com.example.crud.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll(){
        return this.roleRepository.findAll();
    }
}
