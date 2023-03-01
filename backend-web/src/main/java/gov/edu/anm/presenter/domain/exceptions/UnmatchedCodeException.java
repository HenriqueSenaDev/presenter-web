package gov.edu.anm.presenter.domain.exceptions;

public class UnmatchedCodeException extends RuntimeException {
    public UnmatchedCodeException(String message) {
        super(message);
    }
}
