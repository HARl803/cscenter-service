package com.haribo.cscenter_service.reportuser.presentation;

import com.haribo.cscenter_service.reportuser.presentation.response.ReportUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerForReportUser {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ReportUserResponse<Object>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ReportUserResponse.error(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ReportUserResponse<Object>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ReportUserResponse.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
