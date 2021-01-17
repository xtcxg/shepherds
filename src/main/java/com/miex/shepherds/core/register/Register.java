package com.miex.shepherds.core.register;

import com.miex.shepherds.core.domain.Job;
import com.miex.shepherds.core.domain.RequestManager;
import com.miex.shepherds.core.domain.Worker;
import com.miex.shepherds.core.mapper.JobMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 工作注册中心
 * 管理job
 */
@Slf4j
@Component
public class Register {

    @Resource
    JobMapper jobMapper;

    private final Map<String,Job> jobs = new HashMap<>();

    /**
     * 初始化工作岗位，数据量不会太大，所有信息记录在内存中
     */
    @PostConstruct
    public void init(){
        jobMapper.selectList(null).forEach(e -> {
            jobs.put(e.getJobIndex(),e);
        });
    }

    /**
     * 注册一项工作,id为 job 文件的 crc32 的值
     * @param job 工作
     */
    public boolean register(Job job){
        log.info("注册新工作:" + job);
        try{
            jobMapper.insert(job);
            jobs.put(job.getJobIndex(),job);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Job> jobs(RequestManager<Job> request){
        return page(request.getPage(),request.getSize());
    }

    public List<Job> getAllJob(){
        List<Job> list = new ArrayList<>();
        jobs.forEach((k,v) -> {
            list.add(v);
        });
        return list;
    }

    public Job getJob(String id) {
        return jobs.get(id);
    }

    public boolean addJob(Job job) {
        if (1 == jobMapper.insert(job)) {
            return true;
        } else {
            return false;
        }
    }


    private List<Job> page(int page,int size) {
        List<Job> list = new ArrayList<>();
        int tag = 0;
        int begin = (page-1) * size;
        int end = Math.min(page * size, jobs.size());
        for (String key:jobs.keySet()) {
            if(begin <= tag && tag < end) {
                list.add(jobs.get(key));
            }
            tag++;
        }
        return list;
    }
}
