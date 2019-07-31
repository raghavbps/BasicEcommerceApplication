package com.firstproject.basicecommercewebsite.exceptionhandlers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Date;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        int status=HttpStatus.BAD_REQUEST.value();
        String error="Argument Types Not Matching";
        String message=ex.getName() + " should be of type " + ex.getRequiredType().getName();
        String path=((ServletWebRequest)request).getRequest().getRequestURI();
        ExceptionResponse exceptionResponse = new ExceptionResponse
                (status,error,path,message,new Date());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        int status_val = HttpStatus.BAD_REQUEST.value();
        String error = "Method Not Supported";
        String message = "";
        StringBuilder builder=new StringBuilder();
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            builder.append(erro.getField() + " not valid " + erro.getDefaultMessage());
        }
        message=builder.toString();
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        ExceptionResponse exceptionResponse = new ExceptionResponse
                (status_val, error, path, message, new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String name = ex.getParameterName();
        int status_val = HttpStatus.BAD_REQUEST.value();
        String error = "URL parameter missing or mismatch";
        String message = name + " parameter is missing";
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        ExceptionResponse exceptionResponse = new ExceptionResponse
                (status_val, error, path, message, new Date());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserNotFoundException.class,ProductNotFoundException.class,
    QuantityNotAvailableException.class,DatabaseException.class})
    public final ResponseEntity<ExceptionResponse> handleCustomException(Exception ex, WebRequest request) {
        int status=HttpStatus.INTERNAL_SERVER_ERROR.value();
        String error="Internal Server Error";
        String message=ex.getLocalizedMessage();
        String path=((ServletWebRequest)request).getRequest().getRequestURI();
        ExceptionResponse exceptionResponse = new ExceptionResponse
                (status,error,path,message,new Date());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({SQLException.class})
    public final ResponseEntity<ExceptionResponse> handleDatabaseException(Exception ex, WebRequest request) {
        int status=HttpStatus.INTERNAL_SERVER_ERROR.value();
        String error="Internal Server Error";
        String message="Error in using the Database";
        String path=((ServletWebRequest)request).getRequest().getRequestURI();
        ExceptionResponse exceptionResponse = new ExceptionResponse
                (status,error,path,message,new Date());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<ExceptionResponse> handleConstraintException(Exception ex, WebRequest request) {
        int status=HttpStatus.INTERNAL_SERVER_ERROR.value();
        String error="Internal Server Error";
        String message=ex.getLocalizedMessage();
        String path=((ServletWebRequest)request).getRequest().getRequestURI();
        ExceptionResponse exceptionResponse = new ExceptionResponse
                (status,error,path,message,new Date());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ExceptionResponse> handleAllOtherException(Exception ex, WebRequest request) {
        int status=HttpStatus.INTERNAL_SERVER_ERROR.value();
        String error="Internal Server Error";
        String message="Some error occurred!!";
        String path=((ServletWebRequest)request).getRequest().getRequestURI();
        ExceptionResponse exceptionResponse = new ExceptionResponse
                (status,error,path,message,new Date());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(
                " method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        int status_val = HttpStatus.BAD_REQUEST.value();
        String error = "Method Not Supported";
        String message = builder.toString();
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        ExceptionResponse exceptionResponse = new ExceptionResponse
                (status_val, error, path, message, new Date());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
