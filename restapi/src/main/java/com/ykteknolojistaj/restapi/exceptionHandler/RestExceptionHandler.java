package com.ykteknolojistaj.restapi.exceptionHandler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.grpc.StatusRuntimeException;
import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // Exception template builder
    private ResponseEntity<Object> buildResponseEntity(ExceptionTemplate template) {
        return new ResponseEntity<>(template, template.getStatus());
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = "Malformed JSON request";
        return buildResponseEntity(new ExceptionTemplate(HttpStatus.BAD_REQUEST, errorMessage, ex));
    }

    // handler for no such API endpoint exists
    @Override
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = "No handler found for the request.";
        return buildResponseEntity(new ExceptionTemplate(HttpStatus.NOT_FOUND, errorMessage, ex));
    }

    // missing request parameter error handling
    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = "Request parameter is missing.";
        return buildResponseEntity(new ExceptionTemplate(HttpStatus.BAD_REQUEST, errorMessage, ex));
    }

    // gRPC connection error handling
    @ExceptionHandler(StatusRuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleEntityNotFound(StatusRuntimeException ex) {
        String errorMessage = "Error while fetching the data from the database microservice.";
        return buildResponseEntity(new ExceptionTemplate(HttpStatus.NOT_FOUND, errorMessage, ex));
    }

    // request param validation error handling
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = "Request parameter must provide conditions.";
        return buildResponseEntity(new ExceptionTemplate(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage, ex));
    }

}