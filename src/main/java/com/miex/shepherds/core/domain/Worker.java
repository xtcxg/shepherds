package com.miex.shepherds.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Worker {
    @TableId
    String workerId;
    String jobIndex;
    String workerName;
    String prop;
    String info;
    String args;
    String cmd;
    String employTime;

}
