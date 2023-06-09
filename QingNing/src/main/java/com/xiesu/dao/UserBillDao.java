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

import com.xiesu.domain.UserBill;
import com.xiesu.dto.bill.UserBillDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户账单mapper
 *
 * @author xiesu
 */
@Mapper
public interface UserBillDao {

    /**
     * 删除指定账单
     *
     * @param billId 账单id
     */
    void deleteByBillId(@Param("billId") Long billId);

    /**
     * 批量删除账单
     *
     * @param billIdList 账单idList
     */
    void deleteBatchByBillId(@Param("billIdList") List<Long> billIdList);

    /**
     * 添加账单
     *
     * @param bill 账单
     */
    void insertOne(@Param("bill") UserBill bill);

    UserBill selectOneByBillId(@Param("billId") Long billId);


    List<UserBillDTO> selectListByAccountId(@Param("accountId") String accountId);
}
