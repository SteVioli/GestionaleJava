package com.EasyLoadGestioneImpresa.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EasyLoadGestioneImpresa.auth.entity.ERole;
import com.EasyLoadGestioneImpresa.auth.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Role findByRoleName(ERole roleName);
	Boolean existsByRoleName(ERole roleName);
}
