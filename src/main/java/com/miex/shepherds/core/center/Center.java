package com.miex.shepherds.core.center;

import com.miex.shepherds.core.manager.Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("apple")
    public String dos(){
        return "apple";
    }
}

