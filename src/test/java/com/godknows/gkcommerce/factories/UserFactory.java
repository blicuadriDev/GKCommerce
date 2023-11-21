package com.godknows.gkcommerce.factories;

import java.time.LocalDate;

import com.godknows.gkcommerce.entities.Role;
import com.godknows.gkcommerce.entities.User;

public class UserFactory {
	
	public static User createClientUser() {
		User user = new User(1L,"Maria","maria@gmail.com","777777777",LocalDate.parse("2001-07-25"),"$2a$10$mw7stpbfKWYIrI..FzovYOV2GgA/sTzBJ7tTg.9h4D8YsF20noiP2");
		user.addRole(new Role(1L,"ROLE_CLIENT"));
		return user;
	}
	
	public static User createAdminUser() {
		User user = new User(2L,"Alex","alex@gmail.com","888888888",LocalDate.parse("1987-12-13"),"$2a$10$mw7stpbfKWYIrI..FzovYOV2GgA/sTzBJ7tTg.9h4D8YsF20noiP2");
		user.addRole(new Role(2L,"ROLE_ADMIN"));
		return user;
	}
	
	public static User createCustomClientnUser(Long id, String username) {
		User user = new User(id, "Maria", username, "777777777", LocalDate.parse("2001-07-25"), "$2a$10$mw7stpbfKWYIrI..FzovYOV2GgA/sTzBJ7tTg.9h4D8YsF20noiP2");
		user.addRole(new Role(1L,"ROLE_CLIENT"));
		return user;
	}
	
	public static User createCustomAdminUser(Long id, String username) {
		User user = new User(id, "Alex", username, "888888888", LocalDate.parse("1987-12-13"), "$2a$10$mw7stpbfKWYIrI..FzovYOV2GgA/sTzBJ7tTg.9h4D8YsF20noiP2");
		user.addRole(new Role(2L,"ROLE_ADMIN"));
		return user;
	}

}
