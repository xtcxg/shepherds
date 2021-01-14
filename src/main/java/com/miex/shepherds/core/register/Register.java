package com.miex.shepherds.core.register;

import com.miex.shepherds.core.domain.Job;
import com.miex.shepherds.core.mapper.JobMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 管理当前的job
 */
@Component
public class Register {

    @Resource
    JobMapper jobMapper;


    private static Map<Long,Job> jobs = new HashMap<>();

    public void register(Job job){
        jobs.put(job.getJobIndex(),job);
    }

    public List<Job> getAllJob(){
        List<Job> list = new ArrayList<>();
        jobs.forEach((k,v) -> {
            list.add(v);
        });
        return list;
    }

    public Job getJob(Long id) {
        return jobs.get(id);
    }

    @Transactional
    public boolean addJob(Job job) {
        if (1 == jobMapper.insert(job)) {
            return true;
        } else {
            return false;
        }
    }
}
