/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xiesu.common.constant;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据库字段默认常量值
 *
 * @author xiesu
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
