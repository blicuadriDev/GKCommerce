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
		
//		refactoring and breaking in two conditional for unit test purposes:
//		if( !loggedUser.hasRole("ROLE_ADMIN") && !loggedUser.getId().equals(userId)) {
//			throw new ForbiddenException("Acesso negado!");
//		}
		if(loggedUser.hasRole("ROLE_ADMIN")) {
			return;
		}
		if(!loggedUser.getId().equals(userId)) {
			throw new ForbiddenException("Acess denied! Should be self or admin");
		}
	}

}
