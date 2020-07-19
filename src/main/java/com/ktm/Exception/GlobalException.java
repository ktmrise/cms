package com.ktm.Exception;


import com.ktm.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException {


    @ExceptionHandler(Exception.class)
    public Result handler(Exception e) {
        log.error(e.getMessage());
        return new Result(e.getMessage(),null,500);
    }
}
