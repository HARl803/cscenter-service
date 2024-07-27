package com.haribo.cscenter_service.inquiry.presentation;

import com.haribo.cscenter_service.inquiry.presentation.response.InquiryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerForInquiry {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<InquiryResponse<Object>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(InquiryResponse.error(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<InquiryResponse<Object>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(InquiryResponse.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
