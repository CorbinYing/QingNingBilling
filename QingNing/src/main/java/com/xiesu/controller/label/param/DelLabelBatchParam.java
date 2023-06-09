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
package com.xiesu.controller.label.param;

import java.io.Serializable;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiesu created on 2023/3/29 15:56
 */
@Getter
@Setter
public class DelLabelBatchParam implements Serializable {

    /**
     * 待删除标签id
     */
    @NotBlank(message = "待删除标签id不能为空")
    private List<Long> labelIdList;


}
