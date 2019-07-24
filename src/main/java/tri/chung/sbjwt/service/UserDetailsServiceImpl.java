package tri.chung.sbjwt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tri.chung.sbjwt.entity.Role;
import tri.chung.sbjwt.entity.User;
import tri.chung.sbjwt.repository.UserRepository;
import tri.chung.sbjwt.security.CustomUserDetails;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
//		Set<GrantedAuthority> grantedAuthority = new HashSet<>();
//		for (Role role : user.getRoles()) {
//			grantedAuthority.add(new SimpleGrantedAuthority(role.getCode()));
//		}

		return CustomUserDetails.create(user);
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				grantedAuthority);
	}
	
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("User not found with id: " + id));

		return CustomUserDetails.create(user);
	}

}
