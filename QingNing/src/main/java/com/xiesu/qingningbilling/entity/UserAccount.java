package com.xiesu.qingningbilling.entity;

import com.xiesu.qingningbilling.common.constant.ColumnConstant;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * 账户表
 */
@Getter
@Setter
public class UserAccount extends AbstractEntity implements Serializable {

    /**
     * 账户唯一标识id
     */
    private String accountId;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 绑定邮箱(nullable,但逻辑唯一)
     */
    private String mail;
    /**
     * 绑定手机号(nullable,但逻辑唯一)
     */
    private String tel;

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
