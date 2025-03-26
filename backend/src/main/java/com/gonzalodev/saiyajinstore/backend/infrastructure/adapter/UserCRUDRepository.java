package com.gonzalodev.saiyajinstore.backend.infrastructure.adapter;

import com.gonzalodev.saiyajinstore.backend.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserCRUDRepository extends CrudRepository<UserEntity, Integer> {
}
