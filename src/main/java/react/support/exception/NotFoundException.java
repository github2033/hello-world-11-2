package react.support.exception;

// or directly throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
// without creating an exception class
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class NotFoundException extends RuntimeException {

    public NotFoundException (String msg) {
        super(msg);
    }
}
