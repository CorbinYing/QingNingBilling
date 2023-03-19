package com.xiesu.qingningbilling.service;

import com.xiesu.qingningbilling.entity.SysLabel;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface SysLabelService {


    List<SysLabel> findAll();
}
