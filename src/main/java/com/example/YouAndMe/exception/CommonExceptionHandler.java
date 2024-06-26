package com.example.YouAndMe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.YouAndMe.payload.response.ErrorResponse;

@RestControllerAdvice
public class CommonExceptionHandler {
    public final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        // Xử lý các ngoại lệ chung ở đây và trả về một phản hồi JSON
        logger.error("Error: type={}, message={}", ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Xử lý các ngoại lệ cụ thể ở đây và trả về một phản hồi JSON
        logger.error("Error: type={}, message={}", ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Error: type={}, message={}", ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String message = ex.getParameterName() + " parameter is missing";
        logger.error("Error: type={}, message={}", ex.getClass() ,ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(message), HttpStatus.BAD_REQUEST);
    }

}
