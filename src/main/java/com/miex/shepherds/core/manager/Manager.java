package com.miex.shepherds.core.manager;

import com.miex.shepherds.core.domain.Job;
import com.miex.shepherds.core.domain.Worker;
import com.miex.shepherds.core.mapper.JobMapper;
import com.miex.shepherds.core.mapper.WorkerMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class Manager {

    // 原型列表
    static Map<Long, Job> jobs = new HashMap<>();
    // 工人列表
    static Map<Long,Worker> workers = new HashMap<>();

    @Resource
    JobMapper jobMapper;
    @Resource
    WorkerMapper workerMapper;

    @PostConstruct
    public void construct(){
        jobMapper.selectAll().forEach(e -> {
            jobs.put(e.getIndex(),e);
        });

        workerMapper.selectAll().forEach(e -> {
            workers.put(e.getId(),e);
        });
    }

    public Worker getCoreWorker(){
        return workers.get(0);
    }

}
