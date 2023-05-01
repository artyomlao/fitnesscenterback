package fitness.service;

import fitness.entity.UserEntity;
import fitness.exception.UserAlreadyExistsException;
import fitness.exception.UserNotFoundException;
import fitness.model.AuthenticationRequestDTO;
import fitness.model.Role;
import fitness.model.Status;
import fitness.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity signUp(final AuthenticationRequestDTO userDto) {
        if (userRepository.findFirstByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with such login already exists. Choose another one");
        }

        return userRepository.save(new UserEntity().setEmail(userDto.getEmail())
                .setName(userDto.getName())
                .setPassword(new BCryptPasswordEncoder(12).encode(userDto.getPassword()))
                .setRegistrationTime(LocalDateTime.now())
                .setRole(Role.USER)
                .setStatus(Status.ACTIVE));
    }

    public UserEntity findByEmail(final String email) {
        return userRepository.findFirstByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User with such login doesn't exist"));
    }
}
