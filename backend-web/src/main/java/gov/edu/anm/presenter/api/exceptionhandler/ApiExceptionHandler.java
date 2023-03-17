package gov.edu.anm.presenter.api.exceptionhandler;

import gov.edu.anm.presenter.domain.exceptions.OutOfEventException;
import gov.edu.anm.presenter.domain.exceptions.UnauthorizedRoleException;
import gov.edu.anm.presenter.domain.exceptions.UnavailableSubjectException;
import gov.edu.anm.presenter.domain.exceptions.UnmatchedCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ObjectError error = ex.getBindingResult().getAllErrors().get(0);
        String field = ((FieldError) error).getField();
        String fieldMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
        String message = field + " " + fieldMessage;

        var errorMessage = new ResponseErrorMessage(status.value(), OffsetDateTime.now(), message);

        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
    }

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

    @ExceptionHandler(OutOfEventException.class)
    public ResponseEntity<Object> handleOutOfEventException(OutOfEventException ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        var errorMessage = new ResponseErrorMessage(status.value(), OffsetDateTime.now(), ex.getMessage());

        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
    }

}
