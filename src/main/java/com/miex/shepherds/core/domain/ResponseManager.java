package com.miex.shepherds.core.domain;

import lombok.Data;

@Data
public class ResponseManager {
    int code;
    String msg;
    Object data;

    public ResponseManager() {
        this.code = ResEnums.SUCCESS.getCode();
        this.msg = ResEnums.SUCCESS.getMsg();
    }

    public ResponseManager(Object data) {
        this.data = data;
    }

    public ResponseManager(ResEnums enums) {
        this.code = enums.getCode();
        this.msg = enums.getMsg();
    }
    public ResponseManager(ResEnums enums, String msg) {
        this.code = enums.getCode();
        this.msg = msg;
    }

    public enum ResEnums{
        /**
         * 0 :正常
         * 1*:系统错误
         * 2*：请求错误
         */
        SUCCESS(0,"success"),
        SYS_ERROR(1001,"系统内部错误"),
        BD_ERROR(1002, "数据库操作失败"),
        PARAMS_ERROR(2001, "参数校验不通过");
        int code;
        String msg;
        ResEnums(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode(){
            return this.code;
        }
        public String getMsg() {
            return this.msg;
        }

    }
}
