package com.SBP1.digital_library.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.Map;

@ControllerAdvice
public class SQLExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(SQLException ex){
        String message = ex.getMessage();
        if (ex.getSQLState().equals("23505")) { // unique violation
            if (message.contains("lib_user_phone_no_key")) {
                message = "Phone number already exists.";
            } else if (message.contains("lib_user_email_key")) {
                message = "Email already exists.";
            }
        }
        Map<String, Object> error = Map.of(
                "error", Map.of(
                        "code", 400,
                        "message", message
                )
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
