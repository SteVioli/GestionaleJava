package com.EasyLoadGestioneImpresa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EasyLoadGestioneImpresa.auth.entity.User;
import com.EasyLoadGestioneImpresa.auth.repository.UserRepository;

@Service
public class UserService {
	@Autowired UserRepository userRepo;
	
	public User trovaUtenteById(Long id) {
		return userRepo.findById(id).get();
	}
	
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	
	public void deleteUserById(Long id) {
		userRepo.deleteById(id);
		System.out.println("Utente id "+id+ "cancellato!");
	}
	
	public void deleteUserByUsername(String username) {
		User u = userRepo.findByUsername(username).get();
		userRepo.delete(u);
	}
}
