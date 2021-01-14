package com.miex.shepherds.core.manager;

import com.miex.shepherds.core.domain.Worker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理工人
 */
@Component
public class Manager {

    // 工人列表
    static Map<Long,Worker> workers = new HashMap<>();

    public void entry(Worker worker){
        workers.put(worker.getWorkerId(),worker);
    }
}
