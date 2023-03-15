package com.xiesu.qingningbilling.controller;

import com.xiesu.qingningbilling.common.response.AbstractResponse;
import com.xiesu.qingningbilling.common.response.OkResponseResult;
import com.xiesu.qingningbilling.entity.SysLabel;
import com.xiesu.qingningbilling.service.SysLabelService;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Resource
    private SysLabelService sysLabelService;


    @GetMapping("/test")
    public AbstractResponse test1(){
        List<SysLabel> sysLabels = sysLabelService.findAll();
        System.out.println(LocaleContextHolder.getLocale());
        Map<String,String> map=new HashMap<>();
        map.put("qwe","qwe");
        map.put("wert","wert");
        Map<Integer,Integer> map1=new HashMap<>();
        map1.put(1,1);

        return OkResponseResult.success()
                .item("sysLabel",sysLabels.get(0))
                .item("language", LocaleContextHolder.getLocale())
                .item("name","zhangsan")
                .item("sysLabels",sysLabels)
                .items(map)
                .items(map1)
                .build();
    }

}
