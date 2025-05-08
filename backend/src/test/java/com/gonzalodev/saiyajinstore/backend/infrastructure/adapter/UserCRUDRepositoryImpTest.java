package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;

import com.gonzalodev.saiyajinstore.backend.domain.model.User;
import com.gonzalodev.saiyajinstore.backend.domain.model.UserType;
import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.UserEntity;
import com.gonzalodev.saiyajinstore.backend.infrastructure.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserCRUDRepositoryImpTest {
    @Mock
    private UserCRUDRepository userCRUDRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private  UserCrudRepositoryImp userCrudRepositoryImp;

    @Test
    void save_shouldReturnUser(){
        LocalDateTime time = LocalDateTime.now();
        User user = new User(1, "test", "testN", "testL", "test@mail.com", "test", UserType.USER, time, time);
        UserEntity userEntity = new UserEntity(1, "test", "testN", "testL", "test@mail.com", "test", UserType.USER, time, time);

        when(userMapper.toUserEntity(user)).thenReturn(userEntity);
        when(userCRUDRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.toUser(userEntity)).thenReturn(user);

        User result = userCrudRepositoryImp.save(user);

        assertEquals(user, result);
        verify(userCRUDRepository).save(userEntity);
        verify(userMapper).toUser(userEntity);
    }

    @Test
    void findByEmail_ShouldReturnUserByEmail(){
        LocalDateTime time = LocalDateTime.now();
        String email = "test@mail.com";
        User user = new User(1, "test", "testN", "testL", email, "test", UserType.USER, time, time);
        UserEntity userEntity = new UserEntity(1, "test", "testN", "testL", email, "test", UserType.USER, time, time);

        when(userCRUDRepository.findByEmail(email)).thenReturn(java.util.Optional.of(userEntity));
        when(userMapper.toUser(userEntity)).thenReturn(user);

        User result = userCrudRepositoryImp.findByEmail(email);

        assertNotNull(result);
        verify(userCRUDRepository).findByEmail(email);
        verify(userMapper).toUser(userEntity);
    }

    @Test
    void findById_shouldReturnUserById(){
        LocalDateTime time = LocalDateTime.now();
        int id = 1;
        User user = new User(id, "test", "testN", "testL", "test@mail.com", "test", UserType.USER, time, time);
        UserEntity userEntity = new UserEntity(id, "test", "testN", "testL", "test@mail.com", "test", UserType.USER, time, time);

        when(userCRUDRepository.findById(id)).thenReturn(java.util.Optional.of(userEntity));
        when(userMapper.toUser(userEntity)).thenReturn(user);

        User result = userCrudRepositoryImp.findById(id);

        assertNotNull(result);
        verify(userCRUDRepository).findById(id);
        verify(userMapper).toUser(userEntity);
    }

    @Test
    void findAllUsers_shouldReturnAllUsers(){
        LocalDateTime time = LocalDateTime.now();
        User user = new User(1, "test", "testN", "testL", "test@mail.com", "test", UserType.USER, time, time);
        UserEntity userEntity = new UserEntity(1, "test", "testN", "testL", "test@mail.com", "test", UserType.USER, time, time);
        User user2 = new User(2, "test2", "testN2", "testL2", "test2@mail.com", "test", UserType.USER, time, time);
        UserEntity userEntity2 = new UserEntity(2, "test2", "testN2", "testL2", "test2@mail.com", "test", UserType.USER, time, time);

        List<User> users = List.of(user, user2);
        List<UserEntity> usersE = List.of(userEntity, userEntity2);

        when(userCRUDRepository.findAll()).thenReturn(usersE);
        when(userMapper.toUsers(usersE)).thenReturn(users);

        Iterable<User> result = userCrudRepositoryImp.findAllUser();

        assertEquals(users, result);
        verify(userCRUDRepository).findAll();
        verify(userMapper).toUsers(usersE);
    }

    @Test
    void deleteUserById_shouldDelteUser(){
        LocalDateTime time = LocalDateTime.now();
        int id = 1;
        UserEntity userEntity = new UserEntity(id, "test", "testN", "testL", "test@mail.com", "test", UserType.USER, time, time);

        when(userCRUDRepository.findById(id)).thenReturn(java.util.Optional.of(userEntity));

        userCrudRepositoryImp.deleteUserById(id);

        verify(userCRUDRepository).findById(id);
        verify(userCRUDRepository).deleteById(id);
    }

    @Test
    void updateUserById_ShouldReturnUserWithNewUsertype(){
        LocalDateTime time = LocalDateTime.now();
        int id = 1;
        String type = "ADMIN";
        UserEntity userEntity = new UserEntity(id, "test", "testN", "testL", "test@mail.com", "test", UserType.USER, time, time);
        User user = new User(id, "test", "testN", "testL", "test@mail.com", "test", UserType.USER, time, time);

        when(userCRUDRepository.findById(id)).thenReturn(java.util.Optional.of(userEntity));

        user.setUserType(UserType.ADMIN);
        userEntity.setUserType(UserType.ADMIN);

        when(userCRUDRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.toUser(userEntity)).thenReturn(user);

        User result = userCrudRepositoryImp.updateUserById(id, type);

        assertEquals(type, result.getUserType().toString());
    }
}
