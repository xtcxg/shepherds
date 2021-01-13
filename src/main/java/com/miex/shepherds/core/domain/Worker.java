package com.miex.shepherds.core.domain;

import com.alibaba.fastjson.JSONObject;

public class Worker {
    Long id;
    Long modelIndex;
    String workName;
    String info;
    String args;
    String cmd;
    String employTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelIndex() {
        return modelIndex;
    }

    public void setModelIndex(Long modelIndex) {
        this.modelIndex = modelIndex;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getEmployTime() {
        return employTime;
    }

    public void setEmployTime(String employTime) {
        this.employTime = employTime;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", modelIndex=" + modelIndex +
                ", workName='" + workName + '\'' +
                ", info='" + info + '\'' +
                ", args='" + args + '\'' +
                ", cmd='" + cmd + '\'' +
                ", employTime='" + employTime + '\'' +
                '}';
    }
}
