package gov.edu.anm.presenter.domain.exceptions;

public class OutOfEventException extends RuntimeException {
    public OutOfEventException(String message) {
        super(message);
    }
}
