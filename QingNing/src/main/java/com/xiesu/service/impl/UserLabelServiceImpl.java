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
package com.xiesu.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.base.Preconditions;
import com.xiesu.dao.UserLabelDao;
import com.xiesu.domain.UserLabel;
import com.xiesu.service.UserLabelService;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户自定义标签service
 *
 * @author xiesu created on 2023/3/20 09:15
 */
@Service
public class UserLabelServiceImpl implements UserLabelService {

    @Resource
    private UserLabelDao userLabelDao;

    @Override
    public List<UserLabel> findByAccountId(String accountId) {
        Preconditions.checkArgument(StringUtils.isNotBlank(accountId), "账户id不能为空");

        return userLabelDao.selectByAccountId(accountId);
    }

    @Override
    public Optional<UserLabel> findByLabelId(Long labelId) {
        Preconditions.checkArgument(labelId != null, "标签id不能为空");
        UserLabel label = userLabelDao.selectByLabelId(labelId);
        return Optional.ofNullable(label);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void softDeleteByLabelId(Long labelId) {
        Preconditions.checkArgument(labelId != null, "标签id不能为空");
        userLabelDao.softDeleteByLabelId(labelId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void softDeleteBatchByLabelId(List<Long> labelIdList) {
        Preconditions.checkArgument(CollectionUtil.isNotEmpty(labelIdList), "标签id不能为空");

        for (int begin = 0, end; begin < labelIdList.size(); begin = end + 1) {
            end = Math.min(500, labelIdList.size()) - 1;
            List<Long> list = labelIdList.subList(begin, end);
            userLabelDao.softDeleteBatchByLabelId(list);
        }

    }
}
