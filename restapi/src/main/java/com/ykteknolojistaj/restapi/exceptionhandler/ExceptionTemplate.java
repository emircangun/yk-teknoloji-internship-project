package com.ykteknolojistaj.restapi.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ExceptionTemplate {

    @Getter @Setter private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Getter @Setter private LocalDateTime timestamp;
    @Getter @Setter private String message;
    @Getter @Setter private String debugMessage;

    private ExceptionTemplate() {
        timestamp = LocalDateTime.now();
    }

    ExceptionTemplate(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

}
