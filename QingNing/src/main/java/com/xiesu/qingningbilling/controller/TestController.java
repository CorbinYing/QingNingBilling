package com.xiesu.qingningbilling.controller;

import com.xiesu.qingningbilling.common.response.AbstractResponse;
import com.xiesu.qingningbilling.common.response.OkResponseResult;
import com.xiesu.qingningbilling.entity.SysLabel;
import com.xiesu.qingningbilling.service.SysLabelService;
import com.xiesu.qingningbilling.utils.IdHelper;
import jakarta.annotation.Resource;
import java.util.ArrayList;
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
    public AbstractResponse test1() {
        List<SysLabel> sysLabels = sysLabelService.findAll();
        System.out.println(LocaleContextHolder.getLocale());
        Map<String, String> map = new HashMap<>();
        map.put("qwe", "qwe");
        map.put("wert", "wert");
        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(1, 1);

        return OkResponseResult.success()
                .item("sysLabel", sysLabels.get(0))
                .item("language", LocaleContextHolder.getLocale())
                .item("name", "zhangsan")
                .item("sysLabels", sysLabels)
                .items(map)
                .items(map1)
                .build();
    }


    @GetMapping("/testMap")
    public Map<Object, Object> testMap() {
        Map<Object, Object> map = new HashMap<>();
        map.put(1, "qwe");
        map.put(2, "wert");

        return map;
    }


    @GetMapping("/testString")
    public String testString() {
        return "zhangsan";
    }


    @GetMapping("/testInteger")
    public Integer testInteger() {
        return 1;
    }

    @GetMapping("/testInt")
    public int testInt() {
        return 1;
    }

    @GetMapping("/testObject")
    public SysLabel testObj() {
        List<SysLabel> sysLabels = sysLabelService.findAll();
        return sysLabels.get(0);
    }


    @GetMapping("/testList")
    public List<SysLabel> testList() {
        List<SysLabel> sysLabels = sysLabelService.findAll();
        return sysLabels;
    }




    @GetMapping("/testId")
    public List<Long> testId() {
        long start=System.currentTimeMillis();
        List<Long> list=new ArrayList<>(10000);
        for (int i=0;i<100000;i++){
           Long id= IdHelper.nextId();
           list.add(id);
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        return list;
    }

}
