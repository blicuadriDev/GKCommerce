package com.godknows.gkcommerce.projections;

public interface UserDetailsProjection {
	
	String getUsername();
	String getPassword();
	Long getRoleId();
	String getAuthority();

}
