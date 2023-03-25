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
package com.xiesu.service;

import com.xiesu.entity.UserLabel;
import java.util.List;
import java.util.Optional;

/**
 * 用户自定义标签service
 *
 * @author xiesu created on 2023/3/20 09:13
 */
public interface UserLabelService {

    /**
     * 获取账户下所有的用户标签
     *
     * @param accountId 账户id
     * @return List<UserLabel> or empty list
     */
    List<UserLabel> findByAccountId(String accountId);


    /**
     * 获取账户下所有的用户标签
     *
     * @param labelId 标签id
     * @return Optional<UserLabel>
     */
    Optional<UserLabel> findByLabelId(Long labelId);


    /**
     * 软删除一个用户label
     *
     * @param labelId 标签id
     */
    void deleteByLabelId(Long labelId);

}
