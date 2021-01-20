package com.miex.shepherds.core.manager;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miex.shepherds.core.domain.RequestManager;
import com.miex.shepherds.core.domain.ResponseManager;
import com.miex.shepherds.core.domain.Worker;
import com.miex.shepherds.core.mapper.WorkerMapper;
import com.miex.shepherds.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理工人
 */
@Slf4j
@Component
public class Manager {
    @Resource
    WorkerMapper workerMapper;

    /**
     * 工人列表
     */
    private final Map<String,Worker> workers = new HashMap<>();

    /**
     * 初始化工人
     */
    @PostConstruct
    public void init() {
        QueryWrapper<Worker> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("worker_id","0");
        workerMapper.selectList(queryWrapper).forEach(e -> {
            if (!Worker.Status.STOP.getStatus().equals(e.getWorkStatus())) {
                e.setWorkStatus(Worker.Status.STOP.getStatus());
                workerMapper.updateById(e);
            }
            workers.put(e.getWorkerId(),e);
        });
    }

    /**
     * 雇佣一个工人
     * @param worker 新增的工人
     */
    public ResponseManager entry(Worker worker){
        try {
            workerMapper.insert(worker);
            workers.put(worker.getWorkerId(),worker);
            return new ResponseManager();
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseManager(ResponseManager.ResEnums.SYS_ERROR);
        }
    }

    /**
     * 获取工人列表
     * @param request
     * @return
     */
    public List<Worker> getList(RequestManager<String> request){
        return page(request.getPage(),request.getSize());
    }

    public boolean work(String workerId) {
        Worker worker = workers.get(workerId);
        boolean res = false;
        try {
            String command = worker.getCmd() + " " + workerId  + (null == worker.getArgs() ? "" : worker.getArgs());
            log.info("开始运行：" + workerId);
            Process process = Runtime.getRuntime().exec(command);
            // worker 日志
//            InputStreamReader ipr=new InputStreamReader(process.getInputStream());
//            LineNumberReader lnr = new LineNumberReader(ipr);
//            new Thread(() -> {
//                String line;
//                try {
//                    while ((line = lnr.readLine ()) != null){
//                        System.out.println(line);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
            res = true;
            worker.setWorkStatus("running");
            res = true;
        } catch (IOException ioe){
            worker.setWorkStatus("wrong");
            ioe.printStackTrace();
        }
        worker.setStartTime(TimeUtil.getTimeNow());
        workerMapper.updateById(worker);
        return res;
    }

    public boolean add(JSONObject params) {
        try {
            Worker worker = new Worker();
            worker.setProp(params.getJSONObject("prop"));
            worker.setExecutePlan(params.getJSONObject("executePlan"));
            worker.setCmd(params.getString("cmd"));
            worker.setWorkerName(params.getString("workerName"));
            worker.setJobIndex(params.getString("jobIndex"));
            System.out.println(worker);
            workerMapper.insert(worker);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取工人分页信息，从第1页开始
     * @param page 页数
     * @param size 每页数据量
     * @return 当前页数据
     */
    public List<Worker> page(int page,int size) {
        List<Worker> list = new ArrayList<>();
        int tag = 0;
        int begin = (page-1) * size;
        int end = Math.min(page * size, workers.size());
        for (String key:workers.keySet()) {
            if(begin <= tag && tag < end) {
                list.add(workers.get(key));
            }
            tag++;
        }
        return list;
    }
}
