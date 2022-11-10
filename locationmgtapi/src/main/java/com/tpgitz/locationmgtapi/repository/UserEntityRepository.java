package com.tpgitz.locationmgtapi.repository;

import com.tpgitz.locationmgtapi.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

    public UserEntity findByEmailAndPassword( String email, String password);
    public UserEntity findByEmail( String email);
}
