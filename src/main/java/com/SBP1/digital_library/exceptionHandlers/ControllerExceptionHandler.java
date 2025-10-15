package com.SBP1.digital_library.exceptionHandlers;

import com.SBP1.digital_library.exceptions.BookException;
import com.SBP1.digital_library.exceptions.TxnException;
import com.SBP1.digital_library.exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();

        Map<String, Object> error = Map.of(
                "error", Map.of(
                        "code", HttpStatus.BAD_REQUEST.value(),
                        "message", message
                )
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<Map<String, Object>> handleUserException(UserException e){
        Map<String, Object> error = Map.of(
                "error", Map.of(
                        "code", HttpStatus.BAD_REQUEST.value(),
                        "message", e.getMessage()
                )
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BookException.class)
    public ResponseEntity<Map<String, Object>> handleBookException(BookException e){
        Map<String, Object> error = Map.of(
                "error", Map.of(
                        "code", HttpStatus.BAD_REQUEST.value(),
                        "message", e.getMessage()
                )
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TxnException.class)
    public ResponseEntity<Map<String, Object>> handleTxnException(TxnException e){
        Map<String, Object> error = Map.of(
                "error", Map.of(
                        "code", HttpStatus.BAD_REQUEST.value(),
                        "message", e.getMessage()
                )
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
