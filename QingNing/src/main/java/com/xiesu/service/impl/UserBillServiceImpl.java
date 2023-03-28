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

import com.xiesu.dto.bill.UserBillDTO;
import com.xiesu.service.UserBillService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 用户账单记录处理业务类service
 *
 * @author xiesu
 */
@Service
public class UserBillServiceImpl implements UserBillService {

    @Override
    public UserBillDTO addOneBill(UserBillDTO addBillDto) {
        return null;
    }

    @Override
    public void deleteBatch(List<Long> deleteBillIdList) {

    }

    @Override
    public void deleteOne(Long deleteBillId) {

    }
}
