package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: xcEduService01
 * @description: 统一异常捕获类
 * @author: Mr.Yang
 * @create: 2020-01-09 16:44
 * @ControllerAdvice 控制器增强
 **/
@ControllerAdvice
public class ExceptionCatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);
    //使用 EXCEPTIONS 存放异常类型和错误代码的映射 ImmutableMap的特点的一旦创建不可改变，并且线程安全
    private static ImmutableMap<Class<? extends Exception>, ResultCode> EXCEPTION;
    //使用builder创建一个异常类型和错误代码的异常Map
    private static ImmutableMap.Builder<Class<? extends Exception>, ResultCode> builder = ImmutableMap.builder();

    static {
        //在类初始化时，map构造器中加入一些基础的异常类型判断
        //请求体数据异常
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
    }

    /**
     * ExceptionHandler 注解 捕获 CustomException 异常 同时调用方法
     *
     * @param custonExcetion 待捕获的异常对象
     * @return JSON
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult handleException(CustomException custonExcetion) {
        //记录日志
        LOGGER.error("catch exception:{}" + custonExcetion.getMessage());
        LOGGER.error("catch exception:{}" + custonExcetion.getCause());
        ResultCode resultCode = custonExcetion.getResultCode();
        //@ResponseBody 将结果转为JSON响应给前端
        return new ResponseResult(resultCode);
    }

    /**
     * 捕获系统异常
     *
     * @param exception 系统异常
     * @return JSON
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult sysException(Exception exception) {
        //1.打印记录日志
        LOGGER.error("catch exception:{}", exception.getMessage());
        //2.判断异常Map是否为空
        if (ObjectUtils.isEmpty(EXCEPTION)) {
            //异常Map为空，使用构造器创建一个ImmutableMap
            EXCEPTION = builder.build();
        }
        //3.根据异常类型获得resultCode
        ResultCode resultCode = EXCEPTION.get(exception.getClass());

        //声明接口规范定义的响应结果类 ResponseResult
        ResponseResult result = null;

        //4.判断resultCode
        if (ObjectUtils.isEmpty(resultCode)) {
            //如果为空，将给出服务器错误，代码99999
            result = new ResponseResult(CommonCode.SERVER_ERROR);
        } else {
            //如果不为空，将resultCode通过响应结果对象放回
            result = new ResponseResult(resultCode);
        }

        //5.返回结果对象
        return result;
    }

}
