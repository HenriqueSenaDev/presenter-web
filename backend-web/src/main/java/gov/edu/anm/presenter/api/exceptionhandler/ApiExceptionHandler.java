package gov.edu.anm.presenter.api.exceptionhandler;

import gov.edu.anm.presenter.exceptions.UnauthorizedRoleException;
import gov.edu.anm.presenter.exceptions.UnavailableSubjectException;
import gov.edu.anm.presenter.exceptions.UnmatchedCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var errorMessage = new ResponseErrorMessage(status.value(), OffsetDateTime.now(), ex.getMessage());

        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UnauthorizedRoleException.class)
    public ResponseEntity<Object> handleUnauthorizedRoleException(UnauthorizedRoleException ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        var errorMessage = new ResponseErrorMessage(status.value(), OffsetDateTime.now(), ex.getMessage());

        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UnavailableSubjectException.class)
    public ResponseEntity<Object> handleUnavailableSubjectException(UnavailableSubjectException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var errorMessage = new ResponseErrorMessage(status.value(), OffsetDateTime.now(), ex.getMessage());

        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UnmatchedCodeException.class)
    public ResponseEntity<Object> handleUnmatchedCodeException(UnmatchedCodeException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var errorMessage = new ResponseErrorMessage(status.value(), OffsetDateTime.now(), ex.getMessage());

        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
    }

}
