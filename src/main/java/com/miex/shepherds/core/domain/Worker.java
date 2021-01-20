package com.miex.shepherds.core.domain;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableId;
import com.miex.shepherds.utils.SnowFlakeUtil;
import com.miex.shepherds.utils.TimeUtil;
import lombok.Data;

import java.util.Timer;

@Data
public class Worker {

    public Worker(){
        this.workerId = SnowFlakeUtil.getCrc();
        this.employTime = TimeUtil.getTimeNow();
        this.workStatus = "stop";
    }

    /**
     * 唯一id，根据创建时间的CRC32编码得到
     */
    @TableId
    String workerId;

    /**
     * 来源
     */
    String jobIndex;

    /**
     * 用户定义的名称
     */
    String workerName;

    /**
     * 执行计划
     * {
     *     "autoBoot": "on", //自启 on/off
     *     "cron": ""   // 用于数据同步或备份
     * }
     */
    String executePlan;

    public void setExecutePlan(JSONObject params){
        this.executePlan = params.toJSONString();
    }
//    public JSONObject getExecutePlan(){
//        return JSONObject.parseObject(this.executePlan);
//    }

    /**
     * 核心数据，集群配置信息
     */
    String nodeInfo;

    /**
     * 程序启动命令
     */
    String cmd;

    /**
     * 命令行参数
     */
    String args;

    /**
     * 程序启动时加载的配置
     */
    String prop;

    public void setProp(JSONObject prop) {
        this.prop = prop.toJSONString();
    }
//    public JSONObject getProp(){
//        return JSONObject.parseObject(this.prop);
//    }

    /**
     * 工作状态
     * running
     * stop
     * wrong
     */
    String workStatus;

    /**
     * 当前状态，开始时间
     */
    String startTime;

    /**
     * 创建时间
     * yyyy-mm-dd HH:MM:ss
     */
    String employTime;

    public enum Status{
        RUNNING("running"),
        STOP("stop"),
        WRONG("wrong");

        String status;
        Status(String status){
            this.status = status;
        }
        public String getStatus(){
            return this.status;
        }
    }
}
