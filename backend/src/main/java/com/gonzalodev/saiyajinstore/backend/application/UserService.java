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
        try {
            return userRepository.save(user);
        } catch (Exception e){
            throw new RuntimeException("Error saving user", e);
        }
    }

    public User findById(Integer id){
        try {
            return userRepository.findById(id);
        } catch (Exception e){
            throw new RuntimeException("Error finding user", e);
        }
    }

    public User findByEmail(String email){
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e){
            throw new RuntimeException("Error finding user by email", e);
        }
    }

    public Iterable<User> findAllUsers(){
        return userRepository.findAllUser();
    }

    public void deleteUserById(Integer id){
        try {
            userRepository.deleteUserById(id);
        } catch (Exception e){
            throw new RuntimeException("Error deleting user", e);
        }
    }

    public User updateUserById(Integer id, String userType){
        try {
            return userRepository.updateUserById(id, userType);
        } catch (Exception e){
            throw new RuntimeException("Error updating user", e);
        }
    }
}
