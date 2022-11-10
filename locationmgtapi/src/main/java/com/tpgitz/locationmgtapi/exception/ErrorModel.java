package com.tpgitz.locationmgtapi.exception;


import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorModel implements Serializable {

    private String code;
    private String message;
}
