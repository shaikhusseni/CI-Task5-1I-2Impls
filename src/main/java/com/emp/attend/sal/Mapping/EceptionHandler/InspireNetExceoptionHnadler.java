package com.emp.attend.sal.Mapping.EceptionHandler;


import com.emp.attend.sal.Mapping.Controllers.EmployeeController;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@NoArgsConstructor
@ControllerAdvice
public class InspireNetExceoptionHnadler extends Throwable {

    private ApiErrorCodes apiErrorCodes;
    private  String message;

    public InspireNetExceoptionHnadler(ApiErrorCodes noAttendanceRecords) {
    }


    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {

        ApiErrorCodes errorCode;
        String errorMessage;

        if (ex.getMessage().equals(ApiErrorCodes.EMPLOYEE_ALREADY_EXISTS.getMessage())) {
            errorCode = ApiErrorCodes.EMPLOYEE_ALREADY_EXISTS;
        } else if (ex.getMessage().equals(ApiErrorCodes.NO_ATTENDANCE_RECORDS.getMessage())) {
            errorCode = ApiErrorCodes.NO_ATTENDANCE_RECORDS;
        } else {
            errorCode = ApiErrorCodes.EMPLOYEE_ALREADY_EXISTS; // Add an appropriate default error code
        }

        errorMessage = ex.getMessage();
        ApiErrorCodeResponse response = new ApiErrorCodeResponse(errorCode, errorMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);




    }

 }
