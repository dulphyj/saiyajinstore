package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;

import com.gonzalodev.saiyajinstore.backend.domain.model.User;
import com.gonzalodev.saiyajinstore.backend.domain.model.UserType;
import com.gonzalodev.saiyajinstore.backend.domain.port.UserRepository;
import com.gonzalodev.saiyajinstore.backend.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;


@Repository
public class UserCrudRepositoryImp implements UserRepository {
    private final UserCRUDRepository userCRUDRepository;
    private final UserMapper userMapper;

    public UserCrudRepositoryImp(UserCRUDRepository userCRUDRepository, UserMapper userMapper) {
        this.userCRUDRepository = userCRUDRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        return userMapper.toUser(userCRUDRepository.save(userMapper.toUserEntity(user)));
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.toUser(userCRUDRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException("User with email: "+ email +" not found")
        ));
    }

    @Override
    public User findById(Integer id) {
        return userMapper.toUser(userCRUDRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("User with id: "+id+" not found")
        ));
    }

    @Override
    public Iterable<User> findAllUser() {
        return userMapper.toUsers(userCRUDRepository.findAll());
    }

    @Override
    public void deleteUserById(Integer id) {
        userCRUDRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("User with id: "+id+" no found")
        );
        userCRUDRepository.deleteById(id);
    }

    @Override
    public User updateUserById(Integer id, String userType) {
         var user = userCRUDRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("User with id: "+id+" no found")
        );
         UserType newType;
         try {
             newType = UserType.valueOf(userType.toUpperCase());
         } catch (IllegalArgumentException e){
             throw new RuntimeException("Invalid user type" + userType);
         }

         user.setUserType(newType);
         return userMapper.toUser(userCRUDRepository.save(user));
    }
}
