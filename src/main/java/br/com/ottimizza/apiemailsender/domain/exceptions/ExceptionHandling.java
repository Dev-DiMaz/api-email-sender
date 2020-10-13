package br.com.ottimizza.apiemailsender.domain.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.nio.file.AccessDeniedException;
import java.util.Locale;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ottimizza.apiemailsender.domain.responses.ErrorResponse;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler({ RuntimeException.class })
    public HttpEntity<?> handleRunTimeException(RuntimeException e, Locale locale) {
        return error(INTERNAL_SERVER_ERROR, "internal_server_error", e.getMessage(), e);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public HttpEntity<?> handleAccessDeniedException(AccessDeniedException e, Locale locale) {
        return error(BAD_REQUEST, "access_denied", e.getMessage(), e);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public HttpEntity<?> handleIllegalArgumentException(RuntimeException e, Locale locale) {
        return error(BAD_REQUEST, "illegal_arguments", e.getMessage(), e);
    }

    private HttpEntity<?> error(HttpStatus status, String error, String errorDescription, Exception e) {
        // e.printStackTrace();
        ErrorResponse response = new ErrorResponse(error, errorDescription);
        return ResponseEntity.status(status).body(response);
    }
}