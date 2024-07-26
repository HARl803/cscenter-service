package com.haribo.cscenter_service.user.reportcontent.presentation;

import com.haribo.cscenter_service.user.reportcontent.presentation.response.UserReportContentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerForContentReportInUser {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<UserReportContentResponse<Object>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(UserReportContentResponse.error(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserReportContentResponse<Object>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(UserReportContentResponse.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
