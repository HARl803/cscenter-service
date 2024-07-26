package com.haribo.cscenter_service.user.reportuser.presentation;

import com.haribo.cscenter_service.user.reportuser.presentation.response.UserReportUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerForUserReportInUser {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<UserReportUserResponse<Object>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(UserReportUserResponse.error(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserReportUserResponse<Object>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(UserReportUserResponse.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
