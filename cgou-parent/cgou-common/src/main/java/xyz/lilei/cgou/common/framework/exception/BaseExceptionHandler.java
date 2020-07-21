package xyz.lilei.cgou.common.framework.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.common.entity.StatusCode;

/**
 * @ClassName BaseExceptionHandler
 * @Description TODO
 * @Author lilei
 * @Date 02/07/2020 00:50
 * @Version 1.0
 **/
@ControllerAdvice
public class BaseExceptionHandler {

    /***
     * 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
