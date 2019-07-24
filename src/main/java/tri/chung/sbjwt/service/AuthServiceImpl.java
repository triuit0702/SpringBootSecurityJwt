package tri.chung.sbjwt.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tri.chung.sbjwt.entity.Role;
import tri.chung.sbjwt.entity.User;
import tri.chung.sbjwt.model.SignUpRequest;
import tri.chung.sbjwt.repository.RoleRepository;
import tri.chung.sbjwt.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  UserRepository userRepository;
  @Autowired
  BCryptPasswordEncoder passwordEncoder;
  
  @Autowired
  RoleRepository roleRepository;

  @Override
  public User postUser(SignUpRequest signUpRequest) {
    String encodePass = passwordEncoder.encode(signUpRequest.getPassword());
    User user = new User(signUpRequest.getUsername(), encodePass, signUpRequest.getFullname());
    Role roleUser = roleRepository.findByCode("USER");
    Set<Role> listRole = new HashSet<>();
    listRole.add(roleUser);
    user.setRoles(listRole);
    User newUser = userRepository.save(user);
    return newUser;
  }

}
