package webbrain.incomeexpenseapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AttachmentNotFoundException extends RuntimeException{

    public AttachmentNotFoundException(String message) {
        super(message);
    }
}
