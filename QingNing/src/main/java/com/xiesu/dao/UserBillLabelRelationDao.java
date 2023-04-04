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

import com.xiesu.domain.UserBillLabelRelation;
import com.xiesu.dto.bill.UserBillLabelRelationDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户账单标签关联中间表Mapper
 *
 * @author xiesu
 */
@Mapper
public interface UserBillLabelRelationDao {

    /**
     * 删除账单关联的标签-账单中间表数据
     *
     * @param billId 账单id
     */
    void deleteByBillId(@Param("billId") Long billId);

    /**
     * 批量删除账单关联的标签-账单中间表数据
     *
     * @param billIdList 账单id List
     */
    void deleteBatchByBillId(@Param("billIdList") List<Long> billIdList);


    void insertBatch(@Param("relationList") List<UserBillLabelRelation> relationList);

}
