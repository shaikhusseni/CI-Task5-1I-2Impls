package com.emp.attend.sal.Mapping.EceptionHandler;

import lombok.AllArgsConstructor;
import org.omg.CORBA.Request;

public enum ApiErrorCodes {




    EMPLOYEE_ALREADY_EXISTS,

    ID_NOT_EXISTS,

    NO_ATTENDANCE_RECORDS;

    private  String message;

    ApiErrorCodes(String message) {
        this.message = message;
    }


    ApiErrorCodes() {
    }

    public String getMessage() {
        return message;
    }

}
