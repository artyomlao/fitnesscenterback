package fitness.controller;

import fitness.dto.AuthenticationRequestDTO;
import fitness.entity.UserEntity;
import fitness.exception.UserNotFoundException;
import fitness.repository.UserRepository;
import fitness.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8001")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            final AuthenticationManager authenticationManager,
            final UserRepository userRepository,
            final JwtTokenProvider jwtTokenProvider) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(final @RequestBody AuthenticationRequestDTO request){
        try {
            final UserEntity user = userRepository.findFirstByEmail(request.getEmail()).orElseThrow(
                    () -> new UserNotFoundException("User with such login doesn't exist"));

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            final String token = jwtTokenProvider.generateToken(request.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();

            response.put("email", request.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (final AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }
}
