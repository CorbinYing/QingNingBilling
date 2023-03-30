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
import com.xiesu.dao.UserBillDao;
import com.xiesu.dao.UserBillLabelRelationDao;
import com.xiesu.dto.bill.UserBillDTO;
import com.xiesu.service.UserBillService;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;


/**
 * 用户账单记录处理业务类service
 *
 * @author xiesu
 */
@Service
public class UserBillServiceImpl implements UserBillService {

    @Resource
    private UserBillDao userBillDao;

    @Resource
    private UserBillLabelRelationDao userBillLabelRelationDao;

    @Override
    public UserBillDTO addOneBill(UserBillDTO addBillDto) {
        return null;
    }

    @Override
    public void deleteBatch(List<Long> deleteBillIdList) {
        Preconditions.checkArgument(CollectionUtil.isNotEmpty(deleteBillIdList), "待删除账单id不能为空");
        //删除账单-标签中间表信息
        userBillLabelRelationDao.deleteBatchByBillId(deleteBillIdList);
        //删除账单信息
        //userBillDao.


    }

    @Override
    public void deleteOne(Long deleteBillId) {
        Preconditions.checkArgument(Objects.nonNull(deleteBillId), "待删除账单id不能为空");
        //删除账单-标签中间表
        userBillLabelRelationDao.deleteByBillId(deleteBillId);
        //删除账单信息
    }
}
