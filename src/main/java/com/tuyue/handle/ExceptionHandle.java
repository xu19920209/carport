package com.tuyue.handle;


import com.tuyue.bean.Result;
import com.tuyue.enums.ResultEnum;
import com.tuyue.exception.AdminException;
import com.tuyue.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/25.
 */
//@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
   // @ExceptionHandler(value = Exception.class)
    //@ResponseBody
    public Result handle(Exception e){
        if (e instanceof AdminException){
            AdminException adminException= (AdminException) e;
            return ResultUtil.error(adminException.getCode(),adminException.getMessage());
        }else {
            if (e instanceof MissingServletRequestParameterException) {
                return ResultUtil.error(400,e.getMessage());
            }
            e.printStackTrace();
//            logger.error("【系统异常】 {}",e.getMessage());
            return ResultUtil.error(ResultEnum.UNKONW_ERROR);
        }
    }

}
