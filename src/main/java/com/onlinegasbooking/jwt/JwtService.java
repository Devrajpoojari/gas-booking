package com.onlinegasbooking.jwt;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlinegasbooking.entity.Admin;
import com.onlinegasbooking.entity.Customer;
import com.onlinegasbooking.repository.IAdminRepository;
import com.onlinegasbooking.repository.ICustomerRepository;

@Service
public class JwtService implements UserDetailsService {

	@Autowired(required = true)
	private JwtUtil jwtUtil;

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IAdminRepository adminRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		authenticate(userName, userPassword);

		UserDetails userDetails = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);

		return new JwtResponse(newGeneratedToken);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin a = null;
		Customer c = null;
		try {
			c = customerRepository.getCustomerByUsername(username).get();
			a = (Admin) adminRepository.getAdminByName(username).get();
		} catch (Exception e) {
			e.printStackTrace();

		}

		if (c != null) {
			return new org.springframework.security.core.userdetails.User(c.getUserName(), c.getPassword().toString(),
					getAuthority());
		}
		if (a != null) {
			return new org.springframework.security.core.userdetails.User(a.getUserName(), a.getPassword().toString(),
					getAuthority());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}

	private Set getAuthority() {
		Set authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + "User"));
		return authorities;
	}

	private void authenticate(String userName, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
