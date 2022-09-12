package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    Optional<Role>getRole(Long id){
        return roleRepository.findById(id);
    }
}
