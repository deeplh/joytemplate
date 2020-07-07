package com.keepjoy.core.exception;

import java.io.Serializable;

public class KeepJoyServiceException extends RuntimeException implements Serializable {

    /**
     * authorityError
     */
    private static final long serialVersionUID = 1L;

    public static final int EXCEPTIONCODE_EXCEPTION=99;//未知的Exception级别的异常


    protected String detail;//错误原因
    protected String exception;//错误原因(与detail完全一致,冗余字段)
    protected Exception ex;//错误对象
    protected Integer exceptionCode;//异常编码
    protected String exceptionKey;//错误异常的key


    public KeepJoyServiceException(String detail) {
        super();
        this.detail = detail;
        this.exception=this.detail;
    }

    public KeepJoyServiceException(String detail,String exceptionKey) {
        super();
        this.detail = detail;
        this.exceptionKey=exceptionKey;
        this.exception=this.detail;
    }

    public KeepJoyServiceException(String detail, Integer exceptionCode) {
        super();
        this.detail = detail;
        this.exceptionCode = exceptionCode;
        this.exception=this.detail;
    }

    public KeepJoyServiceException(String detail,Integer exceptionCode,String exceptionKey) {
        super();
        this.detail = detail;
        this.exceptionCode = exceptionCode;
        this.exceptionKey=exceptionKey;
        this.exception=this.detail;
    }


    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public Exception getEx() {
        return ex;
    }
    public void setEx(Exception ex) {
        this.ex = ex;
    }


    public Integer getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(Integer exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionKey() {
        return exceptionKey;
    }

    public void setExceptionKey(String exceptionKey) {
        this.exceptionKey = exceptionKey;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }


}
