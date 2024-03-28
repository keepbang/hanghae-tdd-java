package io.hhplus.tdd;

import io.hhplus.tdd.point.exception.CustomLockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("error: ", e);
        ErrorResponse errorResponse = new ErrorResponse("500", "에러가 발생했습니다.");
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(CustomLockException.class)
    public ResponseEntity<ErrorResponse> handleCustomLockException(Exception e) {
        log.error("error: ", e);
        ErrorResponse errorResponse = new ErrorResponse("500", e.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
