package com.godknows.gkcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godknows.gkcommerce.entities.User;
import com.godknows.gkcommerce.services.exceptions.ForbiddenException;

@Service
public class AuthService {
	
	@Autowired
	private UserService userService;
	
	public void validateSelfOrAdmin(Long userId) {
		User loggedUser = userService.authenticated();
		if( !loggedUser.hasRole("ROLE_ADMIN") && !loggedUser.getId().equals(userId)) {
			throw new ForbiddenException("Acesso negado!");
		}
	}

}
