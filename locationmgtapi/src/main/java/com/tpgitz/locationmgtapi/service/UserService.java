package com.tpgitz.locationmgtapi.service;

import com.tpgitz.locationmgtapi.exception.BusinessException;
import com.tpgitz.locationmgtapi.model.UserModel;

public interface UserService {
    public boolean login(UserModel userModel) throws BusinessException;
}
