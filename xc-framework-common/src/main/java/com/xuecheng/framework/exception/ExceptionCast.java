package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @program: xcEduService01
 * @description: 用于抛出异常
 * @author: Mr.Yang
 * @create: 2020-01-09 16:42
 **/
public class ExceptionCast {
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
