package com.gonzalodev.saiyajinstore.backend.application;

import com.gonzalodev.saiyajinstore.backend.domain.model.User;
import com.gonzalodev.saiyajinstore.backend.domain.port.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User findById(Integer id){
        return userRepository.findById(id);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Iterable<User> findAllUsers(){
        return userRepository.findAllUser();
    }

    public void deleteUserById(Integer id){
        userRepository.deleteUserById(id);
    }

    public User updateUserById(Integer id, String userType){
        return userRepository.updateUserById(id, userType);
    }
}
