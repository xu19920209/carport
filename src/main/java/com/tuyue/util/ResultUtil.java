package com.tuyue.util;


import com.tuyue.bean.Result;
import com.tuyue.enums.ResultEnum;

/**
 * Created by Administrator on 2017/5/25.
 */
public class ResultUtil {
    public static Result success(Object o){
        Result result = new Result();
        result.setCode(1);
        result.setMsg("成功");
        result.setData(o);
        return result;
    }
    public static Result success(ResultEnum anEnum, Object o){
        Result result = new Result();
        result.setCode(anEnum.getCode());
        result.setMsg(anEnum.getMsg());
        result.setData(o);
        return result;
    }
    public static Result success(){
        return success(null);
    }
    public static Result success(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result success(Integer code,String msg,Object o){
        Result result = new Result();
        result.setData(o);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(ResultEnum anEnum){
        Result result = new Result();
        result.setCode(anEnum.getCode());
        result.setMsg(anEnum.getMsg());
        return result;
    }
}
