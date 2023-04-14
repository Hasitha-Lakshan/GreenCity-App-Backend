package com.greencity.app.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greencity.app.dto.UserDetail;
import com.greencity.app.entity.CollectionCenter;
import com.greencity.app.entity.User;
import com.greencity.app.repository.CollectionCenterRepository;
import com.greencity.app.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CollectionCenterRepository collectionCenterRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		CollectionCenter collectionCenter = collectionCenterRepository.findByUsername(username);
		UserDetail userdetail = new UserDetail();

		if (user != null) {
			userdetail.setUsername(user.getUsername());
			userdetail.setPassword(user.getPassword());
			userdetail.setStatus(user.isAccountStatus());

			if (user.isAdmin()) {
				userdetail.setUserRole("ADMIN");
			} else {
				userdetail.setUserRole("USER");
			}
		}

		if (collectionCenter != null) {
			userdetail.setUsername(collectionCenter.getUsername());
			userdetail.setPassword(collectionCenter.getPassword());
			userdetail.setStatus(collectionCenter.isAccountStatus());
			userdetail.setUserRole("COLLECTION_CENTER");
		}

		return new org.springframework.security.core.userdetails.User(userdetail.getUsername(), userdetail.getPassword(),
				true, true, true, userdetail.getStatus(), getAuthorities("ROLE_" + userdetail.getUserRole()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
		return Collections.singletonList(new SimpleGrantedAuthority(role_user));
	}
}
