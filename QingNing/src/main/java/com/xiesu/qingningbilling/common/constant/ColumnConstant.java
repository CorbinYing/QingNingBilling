package com.xiesu.qingningbilling.common.constant;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据库字段默认常量值
 */
public abstract class ColumnConstant implements Serializable {

    /**
     * 版本号默认值
     */
    public final static Integer INIT_VERSION = 0;
    /**
     * 删除标志默认值(默认为删除) false=0 true=1
     */
    public final static Boolean INIT_DELETE_FLAG = false;

    /**
     * 当前时间,用于update_time,create_time
     *
     * @return LocalDate
     */
    public static LocalDateTime presentTime() {
        return LocalDateTime.now();
    }

    /**
     * 更新删除标志时，返回时间戳
     *
     * @return 时间戳
     */
    public static Long updateDeleteFlag() {
        return System.currentTimeMillis();
    }

    /**
     * 更新版本时，版本+1
     *
     * @param version int型
     * @return version+1
     */
    public static int updateVersion(int version) {
        return ++version;
    }

}
