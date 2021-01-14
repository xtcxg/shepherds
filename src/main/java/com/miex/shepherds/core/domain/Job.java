package com.miex.shepherds.core.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.validation.constraints.NotNull;

@Data
@TableName("job")
public class Job {

    /**
     * 文件的crc 16
     */
    @NotNull
    Long jobIndex;

    @NotNull
    String fullName;
    String identification;
    String version;
    Integer insideVersion;
    String obtainTime;
    Integer jobStatus;
}
