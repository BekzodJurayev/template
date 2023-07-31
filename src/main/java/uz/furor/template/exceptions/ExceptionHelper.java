package uz.furor.template.exceptions;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import uz.furor.template.utils.ResponseBuilder;

@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionHelper {
    private static final Logger _logger = LogManager.getLogger(ExceptionHelper.class);

    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<?> handleException1(RestException ex) {
        _logger.error(ex);
        return ResponseBuilder.get(ex.getMsg(), ex.getStatus());
    }

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestPartException.class,
            ServletRequestBindingException.class,
            MissingServletRequestParameterException.class,
            EmptyResultDataAccessException.class,
            BindException.class
    })
    public ResponseEntity<?> handleException2(Exception ex) {
        _logger.error(ex);
        return ResponseBuilder.get(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<?> handleException3(Exception ex) {
        _logger.error(ex);
        return ResponseBuilder.get("Access denied", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {
            MissingPathVariableException.class,
            NoHandlerFoundException.class
    })
    public ResponseEntity<?> handleException4(Exception ex) {
        _logger.error(ex);
        return ResponseBuilder.get(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            HttpMediaTypeNotSupportedException.class
    })
    public ResponseEntity<?> handleException5(Exception ex) {
        _logger.error(ex);
        return ResponseBuilder.get(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {
            ConversionNotSupportedException.class,
            HttpMessageNotWritableException.class,
            Exception.class,
    })
    public ResponseEntity<?> handleException6(Exception ex) {
        _logger.fatal(ex);
        return ResponseBuilder.get(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {AsyncRequestTimeoutException.class})
    public ResponseEntity<?> handleException7(Exception ex) {
        _logger.fatal(ex);
        return ResponseBuilder.get(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
