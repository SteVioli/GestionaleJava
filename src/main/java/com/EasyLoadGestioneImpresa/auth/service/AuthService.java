package com.EasyLoadGestioneImpresa.auth.service;

import com.EasyLoadGestioneImpresa.auth.payload.LoginDto;
import com.EasyLoadGestioneImpresa.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
