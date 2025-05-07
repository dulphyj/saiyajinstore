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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnUser(){
        User user = new User(1, "testUserName", "testFirstName", "testLastName", "test@mail.com", "12345", UserType.USER, LocalDateTime.parse("2025-06-05T00:00:00"), LocalDateTime.parse("2025-06-05T10:00:00"));

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository).save(user);
    }

    @Test
    void findById_shouldReturnUser(){
        User user = new User(1, "testUserName", "testFirstName", "testLastName", "test@mail.com", "12345", UserType.USER, LocalDateTime.parse("2025-06-05T00:00:00"), LocalDateTime.parse("2025-06-05T10:00:00"));

        when(userRepository.findById(1)).thenReturn(user);

        User result = userService.findById(1);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository).findById(1);
    }

    @Test
    void findAllUsers_shouldReturnListUsers(){
        User user = new User(1, "testUserName", "testFirstName", "testLastName", "test@mail.com", "12345", UserType.USER, LocalDateTime.parse("2025-06-05T00:00:00"), LocalDateTime.parse("2025-06-05T10:00:00"));
        User user2 = new User(2, "testUserName2", "testFirstName2", "testLastName2", "test2@mail.com", "12345", UserType.USER, LocalDateTime.parse("2025-06-05T00:00:00"), LocalDateTime.parse("2025-06-05T10:00:00"));

        List<User> users = Arrays.asList(user, user2);
        when(userRepository.findAllUser()).thenReturn(users);

        Iterable<User> result = userService.findAllUsers();

        assertNotNull(result);
        assertEquals(users, result);
        verify(userRepository).findAllUser();
    }

    @Test
    void deleteUserById_shouldDeleteUser(){
        Integer id = 1;

        doNothing().when(userRepository).deleteUserById(id);

        userService.deleteUserById(id);

        verify(userRepository).deleteUserById(id);
    }
}
