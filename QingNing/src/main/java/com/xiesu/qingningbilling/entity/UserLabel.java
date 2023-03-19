package com.xiesu.qingningbilling.entity;

import com.xiesu.qingningbilling.common.constant.ColumnConstant;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户标签表
 */
@Getter
@Setter
public class UserLabel extends SysLabel implements Serializable {

    /**
     * 账户id
     */
    private String accountId;


    /**
     * 删除标志，默认为false(0)
     */
    private Boolean deleteFlag;

    /**
     * 该条记录是否是可用的
     *
     * @return boolean
     */
    public boolean isNormal() {
        Objects.requireNonNull(deleteFlag);
        return ColumnConstant.INIT_DELETE_FLAG.equals(deleteFlag);
    }

}
