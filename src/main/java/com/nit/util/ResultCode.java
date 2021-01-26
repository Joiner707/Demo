package com.nit.util;

/**
 *
 */
public class ResultCode {
    public static final int GLOBAL_SUCCESSFUL = 200;

    /**
     * 参数不正确
     */
    public static final int GLOBAL_PARAM_ERROR = 3001;

    /**
     * 数据不存在
     */
    public static final int GLOBAL_DATA_NOT_EXIST = 3002;

    /**
     * 参数一样
     */
    public static final int GLOBAL_PARAM_SAME = 3003;

    /**
     * 重复请求
     */
    public static final int GLOBAL_REPEAT_REQUEST = 3004;

    /**
     * 重复请求
     */
    public static final int GLOBAL_PROJECT_NOT_EXIST = 3005;

    /**
     * clone_error
     */
    public static final int GLOBAL_PROJECT_CLONE_ERROR = 3006;

    /**
     * 项目已存在
     */
    public static final int PROKECT_ALREADY_EXISTS = 3007;

    /**
     * 参数不能都为空
     */
    public static final int ALL_PARAM_NO_VALUE = 3007;

    /**
     * 通用
     */
    public static final int GLOBAL_PROJECT_SYSTEM_ERROR = 8001;




}
