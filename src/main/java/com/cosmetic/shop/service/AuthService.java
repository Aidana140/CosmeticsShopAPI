package com.cosmetic.shop.service;

import com.cosmetic.shop.dto.AuthResponse;
import com.cosmetic.shop.dto.LoginRequest;
import com.cosmetic.shop.dto.UserRegisterRequest;
import com.cosmetic.shop.exception.ApiException;
import com.cosmetic.shop.model.User;
import com.cosmetic.shop.repository.UserRepository;
import com.cosmetic.shop.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager manager;
    private final JwtUtil jwtUtil;


    @Transactional
    public AuthResponse register(UserRegisterRequest request) {
        User user =  User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        User user1 = userRepository.save(user);

        return AuthResponse.builder()
                .userId(user1.getId())
                .email(user1.getEmail())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException("User with: " + request.getEmail() + " not found !"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ApiException("The password is incorrect !");
        }
        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String jwt = jwtUtil.generateToken(user);
        return AuthResponse.builder()
                .email(user.getEmail())
                .accessToken(jwt)
                .build();
    }

}
