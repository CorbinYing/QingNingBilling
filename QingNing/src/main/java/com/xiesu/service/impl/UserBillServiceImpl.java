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
import com.xiesu.convert.UserBillConvert;
import com.xiesu.dao.UserBillDao;
import com.xiesu.dao.UserBillLabelRelationDao;
import com.xiesu.dao.UserLabelDao;
import com.xiesu.domain.UserBill;
import com.xiesu.domain.UserBillLabelRelation;
import com.xiesu.domain.UserLabel;
import com.xiesu.dto.bill.UserBillDTO;
import com.xiesu.service.UserBillService;
import com.xiesu.utils.IdHelper;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private UserLabelDao userLabelDao;

    @Resource
    private UserBillLabelRelationDao userBillLabelRelationDao;

    @Override
    public Long addOneBill(UserBillDTO addBillDto) {
        Preconditions.checkArgument(Objects.nonNull(addBillDto), "待插入数据不能未空");

        List<Long> labelIdList = addBillDto.getLabelList().stream().map(UserLabel::getLabelId)
                .toList();

        Long relationId = IdHelper.nextId();
        LocalDateTime now = LocalDateTime.now();

        List<UserBillLabelRelation> relationDTOList = new ArrayList<>();
        for (Long billId : labelIdList) {
            UserBillLabelRelation r = new UserBillLabelRelation();
            r.setBillLabelId(relationId);
            r.setLabelId(billId);
            r.setCreateTime(now);
            relationDTOList.add(r);
        }
        //添加中间表信息
        userBillLabelRelationDao.insertBatch(relationDTOList);

        //添加账单
        UserBill bill = UserBillConvert.INSTANCE.convert2UserBill(addBillDto);
        bill.setBillId(IdHelper.nextId());
        bill.setBillLabelId(relationId);
        userBillDao.insertOne(bill);

        return bill.getBillId();
    }

    @Override
    public void deleteBatch(List<Long> deleteBillIdList) {
        Preconditions.checkArgument(CollectionUtil.isNotEmpty(deleteBillIdList), "待删除账单id不能为空");

        for (int begin = 0, end; begin < deleteBillIdList.size(); begin = end + 1) {
            end = Math.min(500, deleteBillIdList.size()) - 1;
            List<Long> list = deleteBillIdList.subList(begin, end);

            //删除账单-标签中间表信息
            userBillLabelRelationDao.deleteBatchByBillId(list);
            //删除账单信息
            userBillDao.deleteBatchByBillId(list);
        }
    }

    @Override
    public void deleteOne(Long deleteBillId) {
        Preconditions.checkArgument(Objects.nonNull(deleteBillId), "待删除账单id不能为空");
        //删除账单-标签中间表
        userBillLabelRelationDao.deleteByBillId(deleteBillId);
        //删除账单信息
        userBillDao.deleteByBillId(deleteBillId);
    }

    @Override
    public UserBillDTO findOne(Long billId) {
        Preconditions.checkArgument(Objects.nonNull(billId), "账单id不能为空");
        UserBill bill = userBillDao.selectOneByBillId(billId);
        if (Objects.isNull(bill)) {
            return null;
        }

        Long billLableId = bill.getBillLabelId();
        List<UserLabel> labels = userLabelDao.selectByBillLabelId(billLableId);

        UserBillDTO billDTO = UserBillConvert.INSTANCE.convert2UserBillDTO(bill);
        billDTO.setLabelList(labels);

        return billDTO;

    }
}
