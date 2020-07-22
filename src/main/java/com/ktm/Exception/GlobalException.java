package com.ktm.Exception;


import com.ktm.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalException {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handler(Exception e) {
        log.error(e.getMessage());
        if (e instanceof UnknownAccountException) {
            return new Result("用户名错误", null, 500);
        } else if (e instanceof IncorrectCredentialsException) {
            return new Result("密码不正确", null, 500);
        } else {
            log.error(e.getMessage());
            return new Result(e.getMessage(),null,500);
        }

    }
}
