package com.tpgitz.locationmgtapi.exception;


import lombok.Data;

import java.util.List;

@Data
public class BusinessException extends Exception {

    private List<ErrorModel> errorList;

    public BusinessException(List<ErrorModel> errorList) {
        this.errorList = errorList;
    }
}
