package com.EasyLoadGestioneImpresa.auth.runner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.EasyLoadGestioneImpresa.auth.entity.ERole;
import com.EasyLoadGestioneImpresa.auth.entity.Role;
import com.EasyLoadGestioneImpresa.auth.entity.User;
import com.EasyLoadGestioneImpresa.auth.payload.RegisterDto;
import com.EasyLoadGestioneImpresa.auth.repository.RoleRepository;
import com.EasyLoadGestioneImpresa.auth.repository.UserRepository;
import com.EasyLoadGestioneImpresa.auth.service.AuthService;


@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired RoleRepository roleRepository;
	@Autowired UserRepository userRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired AuthService authService;
	
	private Set<Role> adminRole;
	private Set<Role> moderatorRole;
	private Set<Role> userRole;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
//		setRoleDefault();
		checkRoleDefault();
		checkAdminDefault();
	}
	
	private void checkAdminDefault(){
		
		
		User user = new User();
		user.setName("admin1");
		user.setLastname("admin1");
		user.setPassword(passwordEncoder.encode("admin1"));
		user.setEmail("admin1@example.com");
		user.setUsername("admin1");
		Set<Role> adminRoleSet = new HashSet<>();
		adminRoleSet.add(roleRepository.findByRoleName(ERole.ROLE_ADMIN));
		adminRoleSet.add(roleRepository.findByRoleName(ERole.ROLE_USER));
		adminRoleSet.add(roleRepository.findByRoleName(ERole.ROLE_MODERATOR));
		user.setRoles(adminRoleSet);
		if(userRepository.existsByUsername("admin1")){
			System.out.println("Amministratore già creato");
		}else userRepository.save(user);
	}
	
	private void checkRoleDefault() {
		if(roleRepository.existsByRoleName(ERole.ROLE_ADMIN) 
				&& roleRepository.existsByRoleName(ERole.ROLE_USER) 
				&& roleRepository.existsByRoleName(ERole.ROLE_MODERATOR)) {
			System.out.println("Ruoli già creati");
		}else setRoleDefault();
	}
	
	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);
		
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);
		
		Role moderator = new Role();
		moderator.setRoleName(ERole.ROLE_MODERATOR);
		roleRepository.save(moderator);
		
		adminRole = new HashSet<Role>();
		adminRole.add(admin);
		adminRole.add(moderator);
		adminRole.add(user);
		
		moderatorRole = new HashSet<Role>();
		moderatorRole.add(moderator);
		moderatorRole.add(user);
		
		userRole = new HashSet<Role>();
		userRole.add(user);
	}
	

}
