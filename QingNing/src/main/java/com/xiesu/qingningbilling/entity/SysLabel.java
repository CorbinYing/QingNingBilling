package com.xiesu.qingningbilling.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 系统标签表
 */
@Getter
@Setter
public class SysLabel extends AbstractEntity implements Serializable {

    /**
     * 标签id
     */
    private Long labelId;
    /**
     * 标签名
     */
    private String labelName;

}
