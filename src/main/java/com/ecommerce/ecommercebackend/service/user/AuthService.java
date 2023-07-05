package com.ecommerce.ecommercebackend.service.user;

import com.ecommerce.ecommercebackend.dto.AuthenticationRequest;
import com.ecommerce.ecommercebackend.dto.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse register(AuthenticationRequest authenticationDTO);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationDTO);
}
