package fitness.controller;

import fitness.entity.UserEntity;
import fitness.exception.UserNotFoundException;
import fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

@Controller
public abstract class BaseController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public BaseController(final AuthenticationManager authenticationManager, final UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public void auth(final String email, final String password) {
        final UserEntity user = userRepository.findFirstByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User with such login doesn't exist"));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
