package tri.chung.sbjwt.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tri.chung.sbjwt.entity.User;
import tri.chung.sbjwt.model.JwtAuthenticationResponse;
import tri.chung.sbjwt.model.LoginRequest;
import tri.chung.sbjwt.model.SignUpRequest;
import tri.chung.sbjwt.repository.UserRepository;
import tri.chung.sbjwt.security.CustomUserDetails;
import tri.chung.sbjwt.security.JwtTokenProvider;
import tri.chung.sbjwt.service.AuthService;

@RestController
@RequestMapping("/api/")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  UserRepository userRepository;

  @Autowired
  AuthService authService;

  @GetMapping("/index")
  public String index() {
    return "<html><h1>Chao cac ban</h1></html>";
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    Authentication authentication =
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
    String jwt = tokenProvider.generateToken(customUserDetails.getId());
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
  }

  @GetMapping("/getUser/{username}")
  public ResponseEntity<?> welcome(@PathVariable String username) {
    User user = userRepository.findByUsername(username);
    return new ResponseEntity<User>(user, null, HttpStatus.OK);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
    if (userRepository.findByUsername(signUpRequest.getUsername()) != null) {
      return new ResponseEntity<String>("User name is aldready taken!", null, HttpStatus.BAD_REQUEST);
    }
    User newUser = authService.postUser(signUpRequest);
    return new ResponseEntity<User>(newUser, null, HttpStatus.CREATED);
  }
  
  @GetMapping("/getAllUsers")
  public ResponseEntity<?> getAllUser() {
    List<User> listUser = userRepository.findAll();
    return new ResponseEntity<List<User>>(listUser, null, HttpStatus.OK);
  }
}
