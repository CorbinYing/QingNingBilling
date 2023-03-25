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
package com.xiesu.vo.bill;

import com.xiesu.vo.label.UserLabelVO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiesu created on 2023/3/26 11:59
 */
@Getter
@Setter
public class UserBillVO implements Serializable {

    /**
     * 账单id
     */
    private Long billId;

    /**
     * 账单时间
     */
    private LocalDateTime gmtBill;

    /**
     * 账单使用标签
     */
    private List<UserLabelVO> labelVOList;

    /**
     * 账单金额
     */
    private BigDecimal money;
    /**
     * 账单备注
     */
    private String remark;

}
