package com.keepjoy.core.handler;


import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.util.MyLogUtil;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class KeepJoyGlobalExceptionHandler {


        @ResponseBody
        @ExceptionHandler(value = Exception.class)
        public Map errorHandler(Exception ex) {
            MyLogUtil.printlnException(ex,"未知异常");
            Map map = new HashMap();
            if(ex instanceof MissingServletRequestParameterException){
                map.put("exception", "参数异常");
                map.put("exceptionCode", 98);
            }
            else if(ex instanceof InvocationTargetException){
                if(ex.getCause() instanceof KeepJoyServiceException){
                    KeepJoyServiceException e= (KeepJoyServiceException) ex.getCause();
                    map.put("exceptionCode", e.getExceptionCode());
                }else{
                    map.put("exceptionCode", 99);
                }
            }
            else {
                map.put("exception", "系统正忙,请稍后再试");
                map.put("exceptionCode", 99);
            }

            return map;
        }

        /**
         * 拦截捕捉自定义异常 KeepJoyServiceException.class
         * @param ex
         * @return
         */
        @ResponseBody
        @ExceptionHandler(value = KeepJoyServiceException.class)
        public Map myErrorHandler(KeepJoyServiceException ex) {
            MyLogUtil.printlnException(ex,"业务异常");
            Map map = new HashMap();
            map.put("exception", ex.getException());
            map.put("exceptionCode", ex.getExceptionCode());
            map.put("exceptionKey", ex.getExceptionKey());
            return map;
        }


}
