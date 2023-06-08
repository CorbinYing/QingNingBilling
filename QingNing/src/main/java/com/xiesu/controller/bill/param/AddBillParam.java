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
package com.xiesu.controller.bill.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 新增账单接收参数
 *
 * @author xiesu created on 2023/3/26 13:21
 */
@Getter
@Setter
public class AddBillParam implements Serializable {

    /**
     * 涉及标签id(可以不设置)
     */
    private List<Long> labelIdList;

    /**
     * 账单时间
     */
    @NotNull(message = "账单时间不能为空")
    private LocalDateTime gmtBill;

    /**
     * 账单金额
     */
    @NotNull
    private BigDecimal money;
    /**
     * 账单备注
     */
    private String remark;
}
