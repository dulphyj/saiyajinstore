package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;

import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCRUDRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
