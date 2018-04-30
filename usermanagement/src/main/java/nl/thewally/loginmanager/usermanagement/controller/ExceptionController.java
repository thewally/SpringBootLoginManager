package nl.thewally.loginmanager.usermanagement.controller;

import nl.thewally.loginmanager.usermanagement.errorhandler.ErrorCode;
import nl.thewally.loginmanager.usermanagement.response.ErrorResponse;
import nl.thewally.loginmanager.usermanagement.errorhandler.FunctionalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(ErrorCode.ERROR9999.getErrorCode());
        error.setMessage(ErrorCode.ERROR9999.getErrorMessage() + ex);
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ExceptionHandler(FunctionalException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(FunctionalException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(ex.getErrorCode());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

}
