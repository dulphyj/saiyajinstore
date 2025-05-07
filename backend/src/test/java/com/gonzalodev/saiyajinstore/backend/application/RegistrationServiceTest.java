package com.gonzalodev.saiyajinstore.backend.application;

import com.gonzalodev.saiyajinstore.backend.domain.model.User;
import com.gonzalodev.saiyajinstore.backend.domain.model.UserType;
import com.gonzalodev.saiyajinstore.backend.domain.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RegistrationServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_shouldReturnUser(){
        User user = new User(1, "testUserName", "testFirstName", "testLastName", "test@mail.com", "12345", UserType.USER, LocalDateTime.parse("2025-06-05T00:00:00"), LocalDateTime.parse("2025-06-05T10:00:00"));

        when(userRepository.save(user)).thenReturn(user);

        User result = registrationService.register(user);

        assertEquals(user, result);
        verify(userRepository).save(user);
    }
}
