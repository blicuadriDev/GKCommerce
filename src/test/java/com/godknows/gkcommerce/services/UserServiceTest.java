package com.godknows.gkcommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.godknows.gkcommerce.entities.User;
import com.godknows.gkcommerce.factories.UserDetailsFactory;
import com.godknows.gkcommerce.factories.UserFactory;
import com.godknows.gkcommerce.projections.UserDetailsProjection;
import com.godknows.gkcommerce.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService service;
	
	@Mock
	private UserRepository repository;
	
	
	private String existingUsername, unexistingUsername;
	private User user;
	private List<UserDetailsProjection> userDetails;
	
	@BeforeEach
	void setUp() throws Exception {
		existingUsername = "maria@gmail.com";
		unexistingUsername = "user@gmail.com";
		
		user = UserFactory.createCustomClientnUser(1L, existingUsername);
		
		userDetails = UserDetailsFactory.createCustomAdminUser(existingUsername);
		
		Mockito.when(repository.searchUserAndRoleByEmail(existingUsername)).thenReturn(userDetails);
		Mockito.when(repository.searchUserAndRoleByEmail(unexistingUsername)).thenReturn(new ArrayList<>());
	}
	
	
	@Test
	public void loadUserByUsernameShouldReturnUserDetailsWhenUsernameExists() {
		
		UserDetails result = service.loadUserByUsername(existingUsername);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getUsername(), existingUsername);
		
	}
	
	@Test
	public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUsernameDoesNotExists() {
		
		Assertions.assertThrows(UsernameNotFoundException.class, ()->{
			service.loadUserByUsername(unexistingUsername);
		});
	}

}
