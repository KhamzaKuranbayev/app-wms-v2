package webbrain.incomeexpenseapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MeasurementNotFoundException extends RuntimeException{
    public MeasurementNotFoundException(String message) {
        super(message);
    }
}
