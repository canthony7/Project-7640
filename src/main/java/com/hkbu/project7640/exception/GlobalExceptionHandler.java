package com.hkbu.project7640.exception;

import com.hkbu.project7640.dto.ResponseBean;
import com.hkbu.project7640.dto.ResponseEnum;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Chet
 * @date 11/2/2023 3:42 pm
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseBean ExceptionHandler(Exception e){
        if (e instanceof GlobalException){
            GlobalException exception = (GlobalException) e;
            return ResponseBean.fail(exception.getResponseEnum());
        } else{
            return ResponseBean.fail(ResponseEnum.FAIL);
        }
    }
//
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(ShiroException.class)
//    public ResponseBean handle401(ShiroException e) {
//        return new ResponseBean(401, e.getMessage(), null);
//    }
}
