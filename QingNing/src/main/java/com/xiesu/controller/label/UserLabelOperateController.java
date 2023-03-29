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
package com.xiesu.controller.label;

import com.xiesu.common.response.AbstractResponse;
import com.xiesu.common.response.ErrResponseResult;
import com.xiesu.common.response.OkResponseResult;
import com.xiesu.controller.AbstractBaseController;
import com.xiesu.controller.label.param.DelLabelBatchParam;
import com.xiesu.vo.label.UserLabelVO;
import com.xiesu.convert.UserLabelConvert;
import com.xiesu.domain.UserLabel;
import com.xiesu.service.UserLabelService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.LambdaUtil;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户标签操作接口层
 *
 * @author xiesu
 */
@Slf4j
@RestController
@RequestMapping(path = "/user/label")
public class UserLabelOperateController extends AbstractBaseController {

    @Resource
    private UserLabelService userLabelService;

    /**
     * 获取当前登陆人的所有自定义标签
     */
    @GetMapping(path = "/query-list")
    public List<UserLabelVO> queryUserLabelList() {
        String acid = getCurrentUser();
        List<UserLabel> labelList = userLabelService.findByAccountId(acid);
        return UserLabelConvert.INSTANCE.convert2UserLabelVO(labelList);
    }


    /**
     * 获取用户标签的详细信息
     *
     * @param labelId 标签id
     */
    @GetMapping(path = "/query-one")
    public AbstractResponse queryUserLabel(@RequestParam("labelId") Long labelId) {
        Optional<UserLabel> userLabelOp = userLabelService.findByLabelId(labelId);
        if (userLabelOp.isEmpty()) {
            log.info("未获取到标签信息，labelId={}", labelId);
            return ErrResponseResult.failed().msg("未查询到标签信息").build();
        }

        UserLabel label = userLabelOp.get();
        return OkResponseResult.success()
                .item("labelId", label.getLabelId())
                .item("labelName", label.getLabelName())
                .item("createTime", label.getCreateTime())
                .build();
    }


    /**
     * 批量删除用户标签，历史记账等信息标签不受影响
     *
     * @param labelBatchParam 标签id集合
     */
    @PostMapping("/delete-batch")
    public AbstractResponse deleteBatchUserLabel(
            @RequestBody @Valid DelLabelBatchParam labelBatchParam) {
        List<Long> labelIdList = labelBatchParam.getLabelIdList();
        userLabelService.softDeleteBatchByLabelId(labelIdList);
        return OkResponseResult.success().build();
    }


    /**
     * 删除用户一个标签，历史记账等信息标签不受影响
     *
     * @param labelId 标签id
     */
    @DeleteMapping("/delete-one")
    public AbstractResponse deleteOneUserLabel(@RequestParam("labelId") Long labelId) {
        userLabelService.softDeleteByLabelId(labelId);
        return OkResponseResult.success().build();
    }

}
