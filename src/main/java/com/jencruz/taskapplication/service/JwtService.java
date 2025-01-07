package com.jencruz.taskapplication.service;

import org.springframework.stereotype.Service;

public interface JwtService {
    String extractUsername(String token);
}
