package tri.chung.sbjwt.service;

import tri.chung.sbjwt.entity.User;
import tri.chung.sbjwt.model.SignUpRequest;

public interface AuthService {

  User postUser(SignUpRequest signUpRequest);
}
