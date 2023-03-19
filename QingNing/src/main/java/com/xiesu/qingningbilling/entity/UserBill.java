package com.xiesu.qingningbilling.entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户账单记录表
 */
@Getter
@Setter
public class UserBill extends AbstractEntity implements Serializable {

    /**
     * 账单id
     */
    private Long billId;
    /**
     * 账户id
     */
    private String accountId;
    /**
     * 账单时间
     */
    private LocalDateTime gmtBill;
    /**
     * 账单标签关联表id
     */
    private Long billLabelId;

}
