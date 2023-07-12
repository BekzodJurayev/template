package uz.furor.template.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestException extends RuntimeException {

    private String msg;
    private HttpStatus status;

    public RestException(String msg, HttpStatus status) {
        super(msg);
        this.msg = msg;
        this.status = status;
    }

    private RestException(HttpStatus status) {
        this.status = status;
    }

    public static RestException restThrow(String msg, HttpStatus status) {
        return new RestException(msg, status);
    }

    public static RestException restThrow(String msg) {
        return new RestException(msg, HttpStatus.BAD_REQUEST);
    }

    public static RestException restThrow(HttpStatus status) {
        return new RestException(status);
    }
}
