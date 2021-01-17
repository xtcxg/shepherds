package com.miex.shepherds.core.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestManager<T> {

    @NotNull
    int page;

    @NotNull
    int size;
    T params;
}
