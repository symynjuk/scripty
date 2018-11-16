package co.inventorsoft.academy.service;

import co.inventorsoft.academy.model.entity.Role;
import co.inventorsoft.academy.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> findById(Long id){
       return roleRepository.findById(id);
    }
    public Role findByName(String name){
        return roleRepository.findByName(name);
    }
}
