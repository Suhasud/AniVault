package com.suhas.anivault.service;

import com.suhas.anivault.dto.RegisterRequestDTO;
import com.suhas.anivault.entity.User;
import com.suhas.anivault.enums.Role;
import com.suhas.anivault.exception.InvalidCredentialsException;
import com.suhas.anivault.exception.UserAlreadyExistsException;
import com.suhas.anivault.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.suhas.anivault.dto.LoginRequestDTO;
import com.suhas.anivault.dto.LoginResponseDTO;
import com.suhas.anivault.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Service
public class AuthService {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequestDTO requestDTO) {

        logger.info("Registering user: {}", requestDTO.getUsername());

        if (userRepository.findByUsername(requestDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();

        user.setUsername(requestDTO.getUsername());
        user.setEmail(requestDTO.getEmail());

        user.setPassword(
                passwordEncoder.encode(requestDTO.getPassword())
        );

        user.setRole(Role.USER);

        userRepository.save(user);

        logger.info("User registered successfully: {}", user.getUsername());

        return "User registered successfully";
    }
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {

        logger.info("Login attempt for user: {}", requestDTO.getUsername());

        try {

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    requestDTO.getUsername(),
                                    requestDTO.getPassword()
                            )
                    );

            String token = jwtService.generateToken(
                    (org.springframework.security.core.userdetails.UserDetails)
                            authentication.getPrincipal()
            );

            logger.info("Login successful for user: {}", requestDTO.getUsername());

            return new LoginResponseDTO(token);

        } catch (AuthenticationException ex) {

            logger.warn("Invalid login attempt for user: {}", requestDTO.getUsername());

            throw new InvalidCredentialsException("Invalid username or password");
        }
    }
}
