package com.miex.shepherds.core.domain;

import lombok.Data;

@Data
public class Worker {
    Long workerId;
    Long jobIndex;
    String workerName;
    String prop;
    String info;
    String args;
    String cmd;
    String employTime;

}
