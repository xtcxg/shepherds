package com.miex.shepherds.core.center;

import com.miex.shepherds.core.domain.Job;
import com.miex.shepherds.core.domain.RequestManager;
import com.miex.shepherds.core.domain.ResponseManager;
import com.miex.shepherds.core.domain.Worker;
import com.miex.shepherds.core.manager.Manager;
import com.miex.shepherds.core.register.Register;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 控制中心
 * 1.控制shepherd
 * 1.记录group信息
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping
public class Center {

    @Resource
    Manager manager;

    @Resource
    Register register;

    /**
     * 从 job 获取数据，在 Register 提供一个工作岗位，
     * 从 worker 获取数据，创建 worker 交由 Manager 管理
     */
    @PostConstruct
    public void init() {

    }

    /**
     * 获取工人列表
     * @param request
     * @return
     */
    @PostMapping("works")
    public List<Worker> workers(@RequestBody RequestManager<Worker> request){
        return manager.getList(request);
    }

    /**
     * 获取工作岗位列表
     */
    @PostMapping("jobs")
    public List<Job> jobs(@RequestBody RequestManager<Job> request) {
        log.info(">>>>>get jobs : {}" ,request);
        return register.jobs(request);
    }

    /**
     * 根据 jobIndex 获取工作内容
     * @param jobIndex 工作 jobIndex
     * @return 工作内容
     */
    @PostMapping("job")
    public Job job(String jobIndex) {
        return register.getJob(jobIndex);
    }

    /**
     * 新增一个工作岗位
     * @param job
     * @return
     */
    @PostMapping("add/job")
    public ResponseManager addJob(@Valid @RequestBody Job job) {
        job.setObtainTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        if (register.addJob(job)) {
            return new ResponseManager();
        } else {
            return new ResponseManager(ResponseManager.ResEnums.SYS_FAIL,"数据插入失败");
        }
    }
}

