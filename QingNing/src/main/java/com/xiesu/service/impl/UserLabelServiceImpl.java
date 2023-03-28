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

import com.google.common.base.Preconditions;
import com.xiesu.dao.UserLabelDao;
import com.xiesu.domain.UserLabel;
import com.xiesu.service.UserLabelService;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
    public void deleteByLabelId(Long labelId) {
        Preconditions.checkArgument(labelId != null, "标签id不能为空");
        userLabelDao.deleteByLabelId(labelId);
    }
}
