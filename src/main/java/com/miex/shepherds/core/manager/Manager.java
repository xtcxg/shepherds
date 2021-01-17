package com.miex.shepherds.core.manager;

import com.miex.shepherds.core.domain.RequestManager;
import com.miex.shepherds.core.domain.ResponseManager;
import com.miex.shepherds.core.domain.Worker;
import com.miex.shepherds.core.mapper.WorkerMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理工人
 */
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
        workerMapper.selectList(null).forEach(e -> {
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
            return new ResponseManager(ResponseManager.ResEnums.SYS_FAIL);
        }
    }

    /**
     * 获取工人列表
     * @param request
     * @return
     */
    public List<Worker> getList(RequestManager request){
        return page(request.getPage(),request.getSize());
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
