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
package com.xiesu.dao;

import com.xiesu.domain.UserLabel;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户标签Mapper
 *
 * @author xiesu
 */
@Mapper
public interface UserLabelDao {

    /**
     * 查询指定账户下的所有标签
     *
     * @param accountId 账户id
     * @return List<UserLabel> but maybe empty
     */
    List<UserLabel> selectByAccountId(@Param("accountId") String accountId);

    /**
     * 查询指定账户下的所有标签
     *
     * @param labelId 标签id
     * @return UserLabel or null
     */
    UserLabel selectByLabelId(@Param("labelId") Long labelId);

    /**
     * 查询账单关联的标签信息
     *
     * @param billLabelId 账单关联中间表 关联字段
     * @return List<UserLabel>
     */
    List<UserLabel> selectByBillLabelId(@Param("billLabelId") Long billLabelId);


    /**
     * 软删除指定labelId 的用户标签
     *
     * @param labelId 用户标签
     */
    void softDeleteByLabelId(@Param("labelId") Long labelId);


    /**
     * 批量软删除用户标签
     *
     * @param labelIdList 用户标签id list
     */
    void softDeleteBatchByLabelId(@Param("labelIdList") List<Long> labelIdList);

}
