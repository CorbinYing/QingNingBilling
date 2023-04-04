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
package com.xiesu.dto.bill;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiesu created on 2023/4/4 09:21
 */
@Getter
@Setter
public class UserBillLabelRelationDTO implements Serializable {

    /**
     * 账单关联标签标识
     */
    private Long billLabelId;
    /**
     * 标签id
     */
    private Long labelId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
