package com.miex.shepherds.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@TableName("job")
public class Job {

    /**
     * 文件的crc 16
     */
    @TableId
    @NotNull
    String jobIndex;

    /**
     * 全称
     */
    @NotNull
    String fullName;

    /**
     * 名称
     */
    String jobName;

    /**
     * 版本
     */
    String jobVersion;

    Integer insideVersion;

    /**
     * 默认配置，使用json
     */
    String defProp;

    /**
     * 默认的运行命令
     */
    String jobCmd;

    /**
     * 插件状态
     */
    String jobStatus;

    /**
     * 用户自定义的名称
     */
    String customName;

//    @Value(value = "#{new java.text.SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\").format(new java.util.Date())}" )
    /**
     * 注册时间
     */
    String obtainTime;

}
