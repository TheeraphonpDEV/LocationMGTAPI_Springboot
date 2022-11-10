package com.tpgitz.locationmgtapi.service;


import com.tpgitz.locationmgtapi.converter.UserConverter;
import com.tpgitz.locationmgtapi.entity.UserEntity;
import com.tpgitz.locationmgtapi.exception.BusinessException;
import com.tpgitz.locationmgtapi.exception.ErrorModel;
import com.tpgitz.locationmgtapi.model.UserModel;
import com.tpgitz.locationmgtapi.repository.UserEntityRepository;
import com.tpgitz.locationmgtapi.validation.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserEntityRepository userEntityRepository;

    @Mock
    private UserConverter userConverter;

    @Test
    public void test_login_error() {

        UserModel userModel = new UserModel();
        List<ErrorModel> errorModelList = new ArrayList<>();

        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode("invalid_email");
        errorModel.setMessage("invalid email");
        errorModelList.add(errorModel);

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);

        Assertions.assertThrows(BusinessException.class, () -> {
            userService.login(userModel);
        });
    }

    @Test
    public void test_login_with_wrong_credentials() {

        UserModel userModel = new UserModel();
        userModel.setEmail("xxx@g.cpm");
        userModel.setPassword("xxxx123");
        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = null;

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(userEntityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword()))
                .thenReturn(userEntity);

        Assertions.assertThrows(BusinessException.class, () -> {
            userService.login(userModel);
        });
    }

    @Test
    public void test_login_with_correct_credentials() throws BusinessException {

        UserModel userModel = new UserModel();
        userModel.setEmail("devil@gmail.com");
        userModel.setPassword("abcd");

        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("devil@gmail.com");

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);

        Mockito.when(userEntityRepository.findByEmailAndPassword(userModel.getEmail(),
                userModel.getPassword())).thenReturn(userEntity);
        boolean result = userService.login(userModel);

        Assertions.assertTrue(result);
    }
    @Test
    public void test_register_error(){

        UserModel userModel = new UserModel();
        List<ErrorModel> errorModelList = new ArrayList<>();

        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode("invalid_email");
        errorModel.setMessage("invalid email");
        errorModelList.add(errorModel);

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);

        Assertions.assertThrows(BusinessException.class, ()->{
            userService.register(userModel);
        });
    }

    @Test
    public void test_register_with_existing_user() {

        UserModel userModel = new UserModel();
        userModel.setEmail("abc@g.com");
        userModel.setPassword("xyz123");
        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("abc@g.com");

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(userEntityRepository.findByEmail(userModel.getEmail())).thenReturn(userEntity);

        Assertions.assertThrows(BusinessException.class, ()->{
            userService.register(userModel);
        });
    }

    @Test
    @DisplayName("Testing new user registration flow")
    public void test_register_with_new_user() throws BusinessException {

        UserModel userModel = new UserModel();
        userModel.setEmail("abc@g.com");
        userModel.setPassword("xyz123");

        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = null;

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setEmail("abc@g.com");
        userEntity1.setPassword("xyz123");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("abc@g.com");
        userEntity2.setPassword("xyz123");
        userEntity2.setId(11L);

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(userEntityRepository.findByEmail(userModel.getEmail())).thenReturn(userEntity);

        Mockito.when(userConverter.convertEntityToModel(userModel)).thenReturn(userEntity1);
        Mockito.when(userEntityRepository.save(userEntity1)).thenReturn(userEntity2);

        Long userId = userService.register(userModel);

        Assertions.assertEquals(11L, userId);
    }
}








