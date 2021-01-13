package com.miex.shepherds.core.domain;

import lombok.Data;

@Data
public class BackMsg {
    int code;
    String msg;
    Object data;

    public BackMsg() {
        this.code = BackEnums.SUCCESS.getCode();
        this.msg = BackEnums.SUCCESS.getMsg();
    }

    public BackMsg(BackEnums enums) {
        this.code = enums.getCode();
        this.msg = enums.getMsg();
    }
    public BackMsg(BackEnums enums,String msg) {
        this.code = enums.getCode();
        this.msg = msg;
    }

    public enum BackEnums{
        /**
         * 0 :正常
         * 1*:系统错误
         * 2*：
         */
        SUCCESS(0,"success"),
        SYS_FAIL(1001,"系统内部错误");
        int code;
        String msg;
        BackEnums(int code, String msg) {
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
