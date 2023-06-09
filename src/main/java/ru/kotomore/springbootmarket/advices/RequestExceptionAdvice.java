package ru.kotomore.springbootmarket.advices;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kotomore.springbootmarket.dto.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RequestExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), new HttpHeaders(),
                HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<ErrorMessage> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            String message = "Поле " + fieldName + " " + errorMessage;
            errorMessages.add(new ErrorMessage(message));
        });
        return new ResponseEntity<>(errorMessages, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
