package com.ktm.Exception;


import com.ktm.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(Exception.class)
    public Result handler(Exception e) {
        return new Result(e.getMessage());
    }
}
