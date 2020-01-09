package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * RuntimeException 没有侵入性
 *
 * @program: xcEduService01
 * @description: 自定义异常类型
 * @author: Mr.Yang
 * @create: 2020-01-09 16:38
 **/
public class CustomException extends RuntimeException{
    /**
     * 错误代码
     */
    private ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

}
