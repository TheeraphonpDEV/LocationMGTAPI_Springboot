package com.tpgitz.locationmgtapi.service;


import com.tpgitz.locationmgtapi.constant.ErrorType;
import com.tpgitz.locationmgtapi.converter.UserConverter;
import com.tpgitz.locationmgtapi.entity.UserEntity;
import com.tpgitz.locationmgtapi.exception.BusinessException;
import com.tpgitz.locationmgtapi.exception.ErrorModel;
import com.tpgitz.locationmgtapi.model.UserModel;
import com.tpgitz.locationmgtapi.repository.UserEntityRepository;
import com.tpgitz.locationmgtapi.validation.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserEntityRepository entityRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserValidator userValidator;


    @Override
    public boolean login(UserModel userModel) throws BusinessException {
        logger.debug("Enter method login");
        //empty check email & password
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if(!CollectionUtils.isEmpty(errorModelList)) {
            throw new BusinessException(errorModelList);
        }

        boolean result = false;

        UserEntity userEntity = entityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());
        if(userEntity == null) {
            List<ErrorModel> errorList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.AUTH_INVALID_CREDENTIALS.toString());
            errorModel.setMessage("อีเมลหรือรหัสผ่านไม่ถูกต้อง");

            errorList.add(errorModel);
            logger.warn("Invalid login attempt");
            throw new BusinessException(errorList);
        }else {
            result = true;
            logger.info("ล็อคอินสำเร็จ");
        }
        logger.debug("Exiting method login");
        return result;
    }

    @Override
    public long register(UserModel userModel) throws BusinessException {

        //empty check email & password
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if(!CollectionUtils.isEmpty(errorModelList)) {
            throw new BusinessException(errorModelList);
        }

        //check if user already exist
        UserEntity ue = entityRepository.findByEmail(userModel.getEmail());
        if(null != ue) {

            List<ErrorModel> errorList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.ALREADY_EXIST.toString());
            errorModel.setMessage("อีเมลนี้ได้ถูกใช้งานในระบบเเล้ว กรุณาลองอีเมลอื่น");

            errorList.add(errorModel);
            throw new BusinessException(errorList);

        }


        UserEntity userEntity = userConverter.convertEntityToModel(userModel);
        //check if user already exist
        UserEntity userEntity1 = entityRepository.save(userEntity);
        return userEntity1.getId();
    }
}








