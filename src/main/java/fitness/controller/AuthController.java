package fitness.controller;

import fitness.model.AuthenticationRequestDTO;
import fitness.entity.UserEntity;
import fitness.security.JwtTokenProvider;
import fitness.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            final AuthenticationManager authenticationManager,
            final UserService userService,
            final JwtTokenProvider jwtTokenProvider) {

        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(final @RequestBody AuthenticationRequestDTO request) {
        try {
            final UserEntity user = userService.findByEmail(request.getEmail());

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

    @PostMapping("/registration")
    public ResponseEntity<?> registration(final @RequestBody AuthenticationRequestDTO user) {
        return ResponseEntity.ok(userService.signUp(user));
    }
}
