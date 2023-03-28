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
package com.xiesu.controller.bill;

import com.xiesu.common.response.AbstractResponse;
import com.xiesu.controller.AbstractBaseController;
import com.xiesu.controller.bill.param.AddBillParam;
import com.xiesu.controller.bill.param.DelBillBatchParam;
import com.xiesu.convert.UserBillConvert;
import com.xiesu.dto.bill.UserBillDTO;
import com.xiesu.service.UserBillService;
import com.xiesu.vo.bill.UserBillVO;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理用户账单相关业务接口层
 *
 * @author xiesu
 */
@RestController
@RequestMapping("/user/bill")
public class UserBillOperateController extends AbstractBaseController {

    @Resource
    private UserBillService userBillService;

    /**
     * 添加账单记录，成功后返回该记录信息
     */
    @PostMapping("/add-one")
    public UserBillVO insertOne(@RequestBody @Validated AddBillParam billParam) {

        UserBillDTO addBillDto = UserBillConvert.INSTANCE.convert(billParam);
        UserBillDTO billDTO = userBillService.addOneBill(addBillDto);
        return UserBillConvert.INSTANCE.convert(billDTO);
    }


    /**
     * 批量删除账单信息
     */
    @PostMapping("/delete-batch")
    public AbstractResponse deleteBatch(@RequestBody @Validated DelBillBatchParam billBatchParam) {
        //        TODO: 批量删除不能直接拼接URL
        return null;
    }


    @DeleteMapping("/delete-one")
    public AbstractResponse deleteOne(@RequestParam("billId") Long deleteBillId) {

        return null;
    }


}
