package com.tpgitz.locationmgtapi.service;


import com.tpgitz.locationmgtapi.constant.ErrorType;
import com.tpgitz.locationmgtapi.entity.UserEntity;
import com.tpgitz.locationmgtapi.exception.BusinessException;
import com.tpgitz.locationmgtapi.exception.ErrorModel;
import com.tpgitz.locationmgtapi.model.UserModel;
import com.tpgitz.locationmgtapi.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityRepository entityRepository;


    @Override
    public boolean login(UserModel userModel) throws BusinessException {

        boolean result = false;

        UserEntity userEntity = entityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());
        if(userEntity == null) {
            List<ErrorModel> errorList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.AUTH_INVALID_CREDENTIALS.toString());
            errorModel.setMessage("อีเมลหรือรหัสผ่านไม่ถูกต้อง");

            errorList.add(errorModel);
            throw new BusinessException(errorList);
        }else {
            result = true;
        }
        return result;
    }
}
