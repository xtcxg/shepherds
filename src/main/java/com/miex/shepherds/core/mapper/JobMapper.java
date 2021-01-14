package com.miex.shepherds.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miex.shepherds.core.domain.Job;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobMapper extends BaseMapper<Job> {

    @Select("SELECT * FROM job")
    List<Job> selectAll();
}
