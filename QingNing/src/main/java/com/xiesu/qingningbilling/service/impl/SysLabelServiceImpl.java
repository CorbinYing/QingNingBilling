package com.xiesu.qingningbilling.service.impl;

import com.xiesu.qingningbilling.entity.SysLabel;
import com.xiesu.qingningbilling.mapper.SysLabelMapper;
import com.xiesu.qingningbilling.service.SysLabelService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SysLabelServiceImpl implements SysLabelService {


    @Resource
    private SysLabelMapper sysLabelMapper;

    @Override
    public List<SysLabel> findAll(){
        return sysLabelMapper.selectAll();
    }
}
