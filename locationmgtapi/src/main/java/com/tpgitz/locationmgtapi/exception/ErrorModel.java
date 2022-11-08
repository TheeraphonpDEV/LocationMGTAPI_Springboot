package com.tpgitz.locationmgtapi.exception;


import lombok.Data;

@Data
public class ErrorModel {

    private String code;
    private String message;
}
