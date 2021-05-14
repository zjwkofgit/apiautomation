package com.we.apiautomation.apitest.model;

import lombok.Data;

@Data
public class ResponseInfo {

    private static final long serialVersionUID = 1L;
    /**
     * 约定后台返回数据格式：
     * *        response.data = {
     * *          "success": true/false,         //请求成功或失败
     * *          "result": {},                  //请求成功后的结果
     * *          "error":{
     * *            "code": 100001,              //请求失败错误码
     * *            "message": "用户名字重复"    //请求失败描述
     * *          }
     * *        }
     */
    private Boolean success;
    private Object result;
    private ErrorInfo error;

    public ResponseInfo(Boolean success, ErrorInfo error) {
        this.success = success;
        this.error = error;
    }

    public ResponseInfo(Boolean success, Object result) {
        this.success = success;
        this.result = result;
    }
}
