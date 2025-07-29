package com.example.hai_dien_church.repository;

import com.example.hai_dien_church.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {

    Role findByName(String name);

}
