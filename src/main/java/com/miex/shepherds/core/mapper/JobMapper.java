package com.miex.shepherds.core.mapper;

import com.miex.shepherds.core.domain.Job;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobMapper {

    @Select("SELECT * FROM job")
    List<Job> selectAll();
}
