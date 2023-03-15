package com.xiesu.qingningbilling.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiesu
 */
@Getter
@Setter
public abstract class AbstractEntity implements Serializable, Cloneable {


    /**
     * 主键
     */
    private Integer id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 乐观锁字段
     */
    private Integer version;



    @Override
    public AbstractEntity clone() {
        try {
            return (AbstractEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
