package com.contactkeeper.exception;


import com.contactkepper.exception.model.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = { UserAlreadyExistException.class })
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        GlobalException globalException = new GlobalException();
        globalException.setMessage(ex.getMessage());
        globalException.setStatus(HttpStatus.CONFLICT.value());
        return new ResponseEntity<Object>(globalException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {UserNotAuthorizedException.class})
    public ResponseEntity<Object> handleUserNotAuthorizedException(UserNotAuthorizedException ex) {
        GlobalException globalException = new GlobalException();
        globalException.setMessage(ex.getMessage());
        globalException.setStatus(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<Object>(globalException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ContactNotFoundException.class})
    public ResponseEntity<Object> handleContactNotFoundException(ContactNotFoundException ex) {
        GlobalException globalException = new GlobalException();
        globalException.setMessage(ex.getMessage());
        globalException.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<Object>(globalException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception ex) {
        GlobalException globalException = new GlobalException();
        globalException.setMessage(ex.getMessage());
        globalException.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<Object>(globalException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
