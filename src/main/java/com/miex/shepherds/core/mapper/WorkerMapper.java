package com.miex.shepherds.core.mapper;

import com.miex.shepherds.core.domain.Worker;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WorkerMapper {

    @Select("SELECT * FROM worker")
    List<Worker> selectAll();
}
