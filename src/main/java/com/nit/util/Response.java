package com.nit.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "API相应结果")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {

    @ApiModelProperty(value = "返回状态码",example = "200")
    private int code;

    @ApiModelProperty(value = "返回数据")
    private Object result;

    @ApiModelProperty(value = "描述信息")
    private String message = "";

    private Integer total = 0;
    private Long time;

    public Response() {}

    public Response(Object result) {
        super();
        this.code = ResultCode.GLOBAL_SUCCESSFUL;
        this.result = result;
        this.message = "success";
    }

    public Response(Object result, Integer total) {
        super();
        this.code = ResultCode.GLOBAL_SUCCESSFUL;
        this.result = result;
        this.message = "success";
        this.total = total;
    }

    public Response(int code, Object result, String message) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public Response(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public static Response fail(String message){
        return new Response(ResultCode.GLOBAL_PROJECT_SYSTEM_ERROR,message);
    }

    public Response(int code, Object result, String message, Integer total) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
        this.total = total;
    }

//    public static Response buildFromCupResultCode(BusinessResultCode businessResultCode) {
//        Response response = new Response();
//        response.setCode(businessResultCode.getCode());
//        response.setMessage(businessResultCode.getMsg());
//        return response;
//    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", result=" + result +
                ", message='" + message + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
