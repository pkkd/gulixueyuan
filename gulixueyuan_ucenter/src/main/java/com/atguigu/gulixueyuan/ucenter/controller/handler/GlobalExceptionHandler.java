package com.atguigu.gulixueyuan.ucenter.controller.handler;

import com.atguigu.entity.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author helen
 * @since 2018/12/19
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){

        e.printStackTrace();
        return R.error();

    }

    /*@ExceptionHandler(权限验证错误Exception.class)
    @ResponseBody
    public R errorAuth(Exception e){

        e.printStackTrace();
        return R.error();

    }*/

}
