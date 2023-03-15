package com.xiesu.qingningbilling.entity;
import com.xiesu.qingningbilling.common.constant.ColumnConstant;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户账单-标签关联表
 */
@Getter
@Setter
public class UserBillLabelRelation extends AbstractEntity implements Serializable {

    /**
     * 账单关联标签标识
     */
    private Long billLabelId;
    /**
     * 标签id
     */
    private Long labelId;

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
