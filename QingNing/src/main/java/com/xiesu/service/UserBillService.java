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

import com.github.pagehelper.PageInfo;
import com.xiesu.common.page.Pageable;
import com.xiesu.dto.bill.UserBillDTO;
import java.util.List;

/**
 * 用户账单记录处理业务类service
 *
 * @author xiesu
 */
public interface UserBillService {

    /**
     * 新增一个用户账单，并返回持久化后的信息
     *
     * @param addBillDto 账单信息
     * @return billId  账单唯一索引
     */
    Long addOneBill(UserBillDTO addBillDto);

    /**
     * 批量删除账单信息(同步删除标签-账单中间表信息)
     *
     * @param deleteBillIdList 带删除账单id
     */
    void deleteBatch(List<Long> deleteBillIdList);

    /**
     * 删除账单信息(同步删除标签-账单中间表信息)
     *
     * @param deleteBillId 带删除账单id
     */
    void deleteOne(Long deleteBillId);

    /**
     * 查询指定账单信息
     *
     * @param billId 账单id
     * @return userBillDto
     */
    UserBillDTO findOne(Long billId);


    /**
     * 查询指定账户账单信息
     *
     * @param pageable 分页信息
     * @return PageInfo<userBillDto>
     */
    PageInfo<UserBillDTO> findListByAccountId(Pageable pageable);


}
