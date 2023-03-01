package gov.edu.anm.presenter.domain.exceptions;

public class UnavailableSubjectException extends RuntimeException {
    public UnavailableSubjectException(String message) {
        super(message);
    }
}
