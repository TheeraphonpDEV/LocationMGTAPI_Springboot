package com.tpgitz.locationmgtapi.validation;


import com.tpgitz.locationmgtapi.constant.ErrorType;
import com.tpgitz.locationmgtapi.exception.ErrorModel;
import com.tpgitz.locationmgtapi.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public List<ErrorModel> validateRequest(UserModel userModel) {

        List<ErrorModel> errorModelList = new ArrayList<>();

        if (null != userModel && userModel.getEmail() == null) {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("กรุณาใส่อีเมลด้วยงับ");

            errorModelList.add(errorModel);
        }
        if (null != userModel && userModel.getPassword() == null) {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("กรุณาใส่อรหัสผ่าน้วยงับ");

            errorModelList.add(errorModel);
        }
        return errorModelList;
    }
}
