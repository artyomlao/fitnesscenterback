package fitness.exception.handling;

import fitness.exception.UserAlreadyExistsException;
import fitness.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import fitness.exception.EntityNotFoundException;
import fitness.model.EntityNotFoundResponse;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseBody
    public ResponseEntity<?> handleFieldException(final EntityNotFoundException e, final HttpServletRequest req) {
        return new ResponseEntity<>(new EntityNotFoundResponse(e.getClass().getCanonicalName(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseBody
    public ResponseEntity<?> handleFieldException(final UserNotFoundException e, final HttpServletRequest req) {
        return new ResponseEntity<>(new EntityNotFoundResponse(e.getClass().getCanonicalName(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    @ResponseBody
    public ResponseEntity<?> handleFieldException(final UserAlreadyExistsException e, final HttpServletRequest req) {
        return new ResponseEntity<>(new EntityNotFoundResponse(e.getClass().getCanonicalName(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
