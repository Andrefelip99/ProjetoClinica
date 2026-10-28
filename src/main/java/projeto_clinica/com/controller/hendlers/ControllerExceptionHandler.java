package projeto_clinica.com.controller.hendlers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;
import projeto_clinica.com.service.exceptions.DatabaseException;
import projeto_clinica.com.service.exceptions.ForbiddenException;
import projeto_clinica.com.service.exceptions.ResourceNotFoundException;
import projeto_clinica.com.service.exceptions.ConflictException;
import projeto_clinica.com.service.exceptions.BusinessRuleException;
import projeto_clinica.com.service.exceptions.UnauthorizedException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> resourceNotFound(ResourceNotFoundException e,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Map<String, Object> err = new HashMap<>();
        err.put("timestamp", Instant.now());
        err.put("status", status.value());
        err.put("error", e.getMessage());
        err.put("path", request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<Map<String, Object>> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String, Object> err = new HashMap<>();
        err.put("timestamp", Instant.now());
        err.put("status", status.value());
        err.put("error", e.getMessage());
        err.put("path", request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Map<String, Object>> malformedRequest(Exception e, HttpServletRequest request) {
        return error(HttpStatus.BAD_REQUEST, "A requisição contém dados inválidos ou malformados.", request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> methodArgumentNotValid(MethodArgumentNotValidException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;
        Map<String, Object> err = new HashMap<>();
        err.put("timestamp", Instant.now());
        err.put("status", status.value());
        err.put("error", "Dados inválidos");
        err.put("path", request.getRequestURI());

        List<Map<String, String>> fieldErrors = new ArrayList<>();
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            Map<String, String> fieldError = new HashMap<>();
            fieldError.put("field", f.getField());
            fieldError.put("message", f.getDefaultMessage());
            fieldErrors.add(fieldError);
        }
        err.put("errors", fieldErrors);

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Map<String, Object>> forbidden(ForbiddenException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        Map<String, Object> err = new HashMap<>();
        err.put("timestamp", Instant.now());
        err.put("status", status.value());
        err.put("error", e.getMessage());
        err.put("path", request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, Object>> conflict(ConflictException e, HttpServletRequest request) {
        return error(HttpStatus.CONFLICT, e.getMessage(), request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> dataConflict(DataIntegrityViolationException e,
            HttpServletRequest request) {
        return error(HttpStatus.CONFLICT, "A operação viola uma restrição de dados.", request);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, Object>> unauthorized(UnauthorizedException e, HttpServletRequest request) {
        return error(HttpStatus.UNAUTHORIZED, e.getMessage(), request);
    }

    @ExceptionHandler({ BusinessRuleException.class, IllegalArgumentException.class })
    public ResponseEntity<Map<String, Object>> badRequest(RuntimeException e, HttpServletRequest request) {
        return error(HttpStatus.BAD_REQUEST, e.getMessage(), request);
    }

    private ResponseEntity<Map<String, Object>> error(HttpStatus status, String message,
            HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", message);
        body.put("path", request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

}
