package com.tpgitz.locationmgtapi.exception;


import com.tpgitz.locationmgtapi.constant.ErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException be) {
        for(ErrorModel errorModel: be.getErrorList()){
            logger.debug("Inside handelBusinessException: {}, {}",
                    errorModel.getCode(), errorModel.getMessage());
        }
        ResponseEntity<List<ErrorModel>> responseEntity
                = new ResponseEntity<>(be.getErrorList(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(Exception ex){

        List<ErrorModel> errorModelList = new ArrayList<>();

        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(ErrorType.UNKNOWN_SERVER_ERROR.toString());
        errorModel.setMessage(" Error อะไรก็ไม่รู้วว");

        logger.debug("Inside handelBusinessException: {}, {}",
                errorModel.getCode(), errorModel.getMessage());
        errorModelList.add(errorModel);

        logger.error("Inside handelBusinessException: {}", ex.getMessage());
        ResponseEntity<List<ErrorModel>> responseEntity
                = new ResponseEntity<>(errorModelList, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }


}
