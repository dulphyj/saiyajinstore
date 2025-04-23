package com.gonzalodev.saiyajinstore.backend.domain.port;

import com.gonzalodev.saiyajinstore.backend.domain.model.User;

public interface UserRepository {
    User save(User user);
    User findByEmail(String email);
    User findById(Integer id);
    Iterable<User> findAllUser();
    void deleteUserById(Integer id);
}
