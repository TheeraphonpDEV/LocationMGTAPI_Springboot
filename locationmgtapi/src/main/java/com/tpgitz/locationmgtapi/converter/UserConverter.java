package com.tpgitz.locationmgtapi.converter;

import com.tpgitz.locationmgtapi.entity.UserEntity;
import com.tpgitz.locationmgtapi.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertModelToEntity(UserModel userModel){

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userModel.getEmail());
        userEntity.setFullName(userModel.getFullName());
        userEntity.setMobileNumber(userModel.getMobileNumber());
        userEntity.setPassword(userModel.getPassword());



        return userEntity;
    }
}
