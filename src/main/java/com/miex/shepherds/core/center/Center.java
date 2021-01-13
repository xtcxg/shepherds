package com.miex.shepherds.core.center;

import com.miex.shepherds.core.domain.BackMsg;
import com.miex.shepherds.core.domain.Job;
import com.miex.shepherds.core.manager.Manager;
import com.miex.shepherds.core.mapper.JobMapper;
import com.miex.shepherds.core.mapper.WorkerMapper;
import com.miex.shepherds.core.register.Register;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 控制中心
 * 1.控制shepherd
 * 1.记录group信息
 *
 */
@Slf4j
@RestController
@RequestMapping
public class Center {

    @Autowired
    Manager manager;

    @Autowired
    Register register;

    @Resource
    JobMapper jobMapper;
    @Resource
    WorkerMapper workerMapper;

    /**
     * 从 job 获取数据，在 Register 提供一个工作岗位，
     * 从 worker 获取数据，创建 worker 交由 Manager 管理
     */
    @PostConstruct
    public void init() {
        // 初始化工作岗位
        jobMapper.selectAll().forEach(e -> {
            register.register(e);
        });

        // 初始化工人
        workerMapper.selectAll().forEach(e -> {
            manager.entry(e);
        });
    }

    /**
     * 获取当前能提供的所有工作岗位
     */
    @GetMapping("jobs")
    public List<Job> jobs() {
        return register.getAllJob();
    }

    /**
     * 根据 id 获取工作内容
     * @param id 工作 id
     * @return 工作内容
     */
    @PostMapping("job")
    public Job job(Long id) {
        return register.getJob(id);
    }

    @PostMapping("add/job")
    public BackMsg addJob(@Valid @RequestBody Job job) {
        if (register.addJob(job)) {
            return new BackMsg();
        } else {
            return new BackMsg(BackMsg.BackEnums.SYS_FAIL,"数据插入失败");
        }
    }


}

