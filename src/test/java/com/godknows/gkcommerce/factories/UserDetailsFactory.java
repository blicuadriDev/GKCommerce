package com.godknows.gkcommerce.factories;

import java.util.ArrayList;
import java.util.List;

import com.godknows.gkcommerce.projections.UserDetailsProjection;

public class UserDetailsFactory {
	
	public static List<UserDetailsProjection> createCustomClientUser (String username){
		List<UserDetailsProjection> list = new ArrayList<>();
		list.add(new UserDetailsImpl(username, "123", 1L,"ROLE_CLIENT" ));
		return list;
	}
	
	public static List<UserDetailsProjection> createCustomAdminUser (String username){
		List<UserDetailsProjection> list = new ArrayList<>();
		list.add(new UserDetailsImpl(username, "123", 2L,"ROLE_ADMIN" ));
		return list;
	}
	
	public static List<UserDetailsProjection> createCustomClientAdmintUser (String username){
		List<UserDetailsProjection> list = new ArrayList<>();
		list.add(new UserDetailsImpl(username, "123", 1L,"ROLE_CLIENT" ));
		list.add(new UserDetailsImpl(username, "123", 2L,"ROLE_ADMIN" ));
		return list;
	}
		

}

class UserDetailsImpl implements UserDetailsProjection {
	
	private String userName;
	private String password;
	private Long roleId;
	private String authority;
	
	public UserDetailsImpl() {
	}

	public UserDetailsImpl(String userName, String password, Long roleId, String authority) {
		this.userName = userName;
		this.password = password;
		this.roleId = roleId;
		this.authority = authority;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Long getRoleId() {
		return roleId;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

}