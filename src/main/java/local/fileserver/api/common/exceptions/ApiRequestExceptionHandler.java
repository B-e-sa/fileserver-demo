package local.fileserver.api.common.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiRequestExceptionHandler {
    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
    	HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    	
        ApiException apiException = new ApiException(
                e.getMessage(),
                httpStatus,
                httpStatus.value());

        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
    
    @ExceptionHandler(value = { ConflictException.class })
    public ResponseEntity<Object> handleConflictException(ConflictException e) {
    	HttpStatus httpStatus = HttpStatus.CONFLICT;
    	
        ApiException apiException = new ApiException(
                e.getMessage(),
                httpStatus,
                httpStatus.value());

        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
    
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
    	Map<String, String> fields = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(field -> 
        	fields.put(field.getField(), field.getDefaultMessage()));
    	
        ValidationException validationException = new ValidationException(fields);

        return new ResponseEntity<>(validationException, validationException.getHttpStatus());
    }
}
