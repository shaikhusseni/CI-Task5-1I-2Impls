package com.emp.attend.sal.Mapping.EceptionHandler;

public class ApiErrorCodeResponse {


        private final ApiErrorCodes errorCode;
        private final String errorMessage;

        ApiErrorCodeResponse(ApiErrorCodes errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public ApiErrorCodes getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

