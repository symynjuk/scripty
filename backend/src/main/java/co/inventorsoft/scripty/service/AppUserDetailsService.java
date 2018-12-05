package co.inventorsoft.scripty.service;

import co.inventorsoft.scripty.model.entity.User;
import co.inventorsoft.scripty.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author lzabidovsky 
 */
@Component
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return userRepository.findByEmail(s)
				.filter(User::isEnabled)
				.map(user -> new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole()))))
				.orElseThrow(() -> new UsernameNotFoundException("The user "+s+" doesn't exist"));
	}

}
