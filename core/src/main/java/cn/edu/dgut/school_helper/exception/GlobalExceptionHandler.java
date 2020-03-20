package cn.edu.dgut.school_helper.exception;

import cn.edu.dgut.school_helper.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局统一异常处理
 * 原本把全局统一返回处理也并在一起的，觉得不好用
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handleException(Exception e) {
        log.info("系统异常信息",e);
        return JsonResult.errorException(e.getMessage());
    }

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceRuntimeException.class)
    @ResponseBody
    public JsonResult handleServiceRuntimeException(ServiceRuntimeException e) {
        log.info("业务异常信息",e);
        return JsonResult.errorMsg(e.getMessage());
    }
}
