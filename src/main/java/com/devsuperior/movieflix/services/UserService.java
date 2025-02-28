package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entites.User;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthService authService;
	
	public UserDTO userAuthenticated() {
		User user = authService.authenticated();
		return new UserDTO(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Email não encontrado");
		}
		return user;
	}

}
