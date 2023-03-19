package com.xiesu.qingningbilling.mapper;

import com.xiesu.qingningbilling.entity.SysLabel;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SysLabelMapper{

    /**
     * 查询所有的系统标签
     * @return List<SysLabel> nonnull but maybe empty
     */
    List<SysLabel> selectAll();

}
