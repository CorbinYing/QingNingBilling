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
package com.xiesu.domain;

import com.xiesu.common.constant.ColumnConstant;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户账单-标签关联表
 *
 * @author xiesu
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
