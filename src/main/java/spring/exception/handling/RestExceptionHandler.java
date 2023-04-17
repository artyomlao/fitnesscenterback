package spring.exception.handling;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.exception.EntityNotFoundException;
import spring.model.EntityNotFoundResponse;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseBody
    public ResponseEntity<?> handleFieldException(final EntityNotFoundException e, final HttpServletRequest req) {
        return new ResponseEntity<>(new EntityNotFoundResponse(e.getClass().getCanonicalName(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
