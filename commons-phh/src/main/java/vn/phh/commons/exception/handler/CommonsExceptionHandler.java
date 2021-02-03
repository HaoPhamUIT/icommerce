package vn.phh.commons.exception.handler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.ExhaustedRetryException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.phh.commons.exception.*;
import vn.phh.commons.model.response.Response;

@ControllerAdvice
public class CommonsExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Response> handleJwtExceptionException(JwtException e) {
        FileProcessingException fileProcessingException = new FileProcessingException(e.getMessage());
        return ResponseEntity.ok(new Response(fileProcessingException));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Response> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.ok(new Response(new AccessDeniedException(e.getMessage())));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> handle(AccessDeniedException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> handle(BadRequestException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(ConnectionException.class)
    public ResponseEntity<Response> handle(ConnectionException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<Response> handle(DataBaseException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(FileProcessingException.class)
    public ResponseEntity<Response> handle(FileProcessingException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<Response> handle(GenericException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(IllegalServiceException.class)
    public ResponseEntity<Response> handle(IllegalServiceException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Response> handle(InternalServerException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(RequestTimeoutException.class)
    public ResponseEntity<Response> handle(RequestTimeoutException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handle(ResourceNotFoundException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(ThirdPartyException.class)
    public ResponseEntity<Response> handle(ThirdPartyException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response> handle(ValidationException e) {
        return ResponseEntity.ok(new Response(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handle(Exception e) {
        UnknownException unknownException = new UnknownException(e);
        return ResponseEntity.ok(new Response(unknownException));
    }

    @ExceptionHandler(ExhaustedRetryException.class)
    public ResponseEntity<Response> handle(ExhaustedRetryException e) {
        if (e.getCause() instanceof GenericException) {
            return ResponseEntity.ok(new Response((GenericException) e.getCause()));
        } else {
            return ResponseEntity.ok(new Response(e));
        }
    }
}
