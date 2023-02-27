package gov.edu.anm.presenter.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ResponseErrorMessage {
    private Integer status;
    private OffsetDateTime dateTime;
    private String message;
}
