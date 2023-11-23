package com.godknows.gkcommerce.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.godknows.gkcommerce.entities.User;
import com.godknows.gkcommerce.factories.UserFactory;
import com.godknows.gkcommerce.services.exceptions.ForbiddenException;

@ExtendWith(SpringExtension.class)
public class AuthServiceTest {
	
	@InjectMocks
	private AuthService service;
	
	@Mock
	private UserService userService;
	
	private User admin, selfClient, otherClient;
	
	@BeforeEach
	private void setUp() throws Exception {
		admin = UserFactory.createAdminUser();
		selfClient = UserFactory.createCustomClientnUser(1L, "Bob");
		otherClient = UserFactory.createCustomClientnUser(2L, "Ana");
	}
	
	@Test
	public void validateSelfOrAdminShouldDoNothinWhenAdminLogged() {
		Mockito.when(userService.authenticated()).thenReturn(admin);
		
		Long userId = admin.getId();
		
		Assertions.assertDoesNotThrow(()->{
			service.validateSelfOrAdmin(userId);
		});
	}
	
	@Test
	public void validateSelfOrAdminShouldDoNothinWhenSelfClientLogged() {
		Mockito.when(userService.authenticated()).thenReturn(selfClient);
		
		Long userId = selfClient.getId();
		
		Assertions.assertDoesNotThrow(()->{
			service.validateSelfOrAdmin(userId);
		});
	}
	
	@Test
	public void validateSelfOrAdminShouldDoNothinWhenOtherClientLogged() {
		Mockito.when(userService.authenticated()).thenReturn(selfClient);
		
		Long userId = otherClient.getId();
		
		Assertions.assertThrows(ForbiddenException.class, ()->{
			service.validateSelfOrAdmin(userId);
		});
	}

}
