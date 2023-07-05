package com.ecommerce.ecommercebackend.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommercebackend.dto.AuthenticationRequest;
import com.ecommerce.ecommercebackend.dto.AuthenticationResponse;
import com.ecommerce.ecommercebackend.model.user.User;
import com.ecommerce.ecommercebackend.repository.user.UserRepository;
import com.ecommerce.ecommercebackend.security.JwtUtils;
import com.ecommerce.ecommercebackend.service.user.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(AuthenticationRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);

        String token = jwtUtils.generateToken(user.getEmail());
        return AuthenticationResponse.builder().token(token).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()));
        User user = userRepository.findFirstByEmail(request.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("Incorrect username or password");
        }
        String token = jwtUtils.generateToken(user.getEmail());
        return AuthenticationResponse.builder().token(token).build();
    }

}
