package com.softnet.medical.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;


@Log4j2
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AppException.class })
    @ResponseBody
    public ResponseEntity<Object> handleAppException(Exception ex, WebRequest request) {
        log.error(ex, ex);
        return new ResponseEntity<Object>(
                Map.of(
                        "category", "error",
                        "message", ex.getMessage()
                )
                , new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.fatal("handleHttpMessageNotWritable error :: " + ((ServletWebRequest) request).getRequest().getRequestURI() + " :: " + ex, ex);
        return new ResponseEntity<>(
                Map.of(
                        "category", "error",
                        "message", "Server Error happened."
                )
                , new HttpHeaders(), HttpStatus.OK);
    }
}
