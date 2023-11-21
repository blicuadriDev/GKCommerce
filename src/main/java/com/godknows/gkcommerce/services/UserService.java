package com.godknows.gkcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godknows.gkcommerce.dtos.UserDTO;
import com.godknows.gkcommerce.entities.Role;
import com.godknows.gkcommerce.entities.User;
import com.godknows.gkcommerce.projections.UserDetailsProjection;
import com.godknows.gkcommerce.repositories.UserRepository;
import com.godknows.gkcommerce.utils.CustomUserUtil;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomUserUtil customUserUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<UserDetailsProjection> result = userRepository.searchUserAndRoleByEmail(username);
		
		if(result.size() == 0) {
			throw new UsernameNotFoundException("Email not found");
		}
		
		User user = new User();
		user.setEmail(result.get(0).getUsername());
		user.setPassword(result.get(0).getPassword());
		for(UserDetailsProjection projection : result) {
			user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}

		return user;
	}
	
	protected User authenticated() {
		try {
			String username = customUserUtil.getLoggedUsername();
			return userRepository.findByEmail(username).get(); 
		}
		catch (Exception e) {
			throw new UsernameNotFoundException("Email not found!");
		}
		
	}
	
	
	@Transactional(readOnly = true)
	public UserDTO getMe() {
		User user = authenticated();
		return new UserDTO(user);
	}
	

}
