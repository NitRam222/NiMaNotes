package com.nimanotes.service;

import com.nimanotes.model.LoginSession;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@Service
public class LoginSessionService {

    public boolean isExpired(LoginSession session, Clock clock, Duration ttl) {
        Instant now = Instant.now(clock);
        return session.getCreatedAt().plus(ttl).isBefore(now);
    }
}
