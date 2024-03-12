package theotrin.clientes.resources.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import theotrin.clientes.services.exceptions.EntityNotFoundException;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandle {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound (EntityNotFoundException entityError, HttpServletRequest request){
        Integer status = HttpStatus.NOT_FOUND.value();
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status);
        error.setError("Resource not found");
        error.setMessage(entityError.getMessage());
        error.setPath(request.getRequestURI());

        return  ResponseEntity.status(status).body(error);

    }
}
