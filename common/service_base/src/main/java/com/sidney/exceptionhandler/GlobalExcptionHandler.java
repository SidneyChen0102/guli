package com.sidney.exceptionhandler;

import com.sidney.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Sidney
 * @data 2020/5/4  0:08
 * @description
 */

@ControllerAdvice
@ResponseBody
public class GlobalExcptionHandler {
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行全局异常处理...");
    }
}
