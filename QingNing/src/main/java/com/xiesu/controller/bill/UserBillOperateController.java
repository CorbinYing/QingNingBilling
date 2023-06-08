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

import com.github.pagehelper.PageInfo;
import com.xiesu.common.response.OkResponseResult;
import com.xiesu.common.response.ResponseBuildUtil;
import com.xiesu.common.response.ResponseResult;
import com.xiesu.controller.AbstractBaseController;
import com.xiesu.controller.bill.param.AddBillParam;
import com.xiesu.controller.bill.param.DelBillBatchParam;
import com.xiesu.controller.bill.param.QueryBillPageParam;
import com.xiesu.convert.UserBillConvert;
import com.xiesu.dto.bill.UserBillDTO;
import com.xiesu.service.UserBillService;
import com.xiesu.vo.bill.UserBillVO;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public UserBillVO insertOne(@RequestBody @Valid AddBillParam billParam) {

        UserBillDTO addBillDto = UserBillConvert.INSTANCE.convert2UserBillDTO(billParam);

        Long billId = userBillService.addOneBill(addBillDto);
        UserBillDTO billDTO = userBillService.findOne(billId);

        return UserBillConvert.INSTANCE.convert2UserBillVO(billDTO);
    }


    /**
     * 批量删除账单信息
     */
    @PostMapping("/delete-batch")
    public ResponseResult deleteBatch(@RequestBody @Valid DelBillBatchParam billBatchParam) {
        List<Long> deleteBillIdList = billBatchParam.getBillIdList();
        userBillService.deleteBatch(deleteBillIdList);
        return ResponseBuildUtil.success().build();
    }

    /**
     * 删除账单信息
     *
     * @param deleteBillId 待删除账单id
     */
    @DeleteMapping("/delete-one")
    public ResponseResult deleteOne(@RequestParam("billId") Long deleteBillId) {
        userBillService.deleteOne(deleteBillId);
        return ResponseBuildUtil.success().build();
    }

    /**
     * 分页查询当前用户的账单信息
     *
     * @param pageParam 分页参数
     */
    @GetMapping("/query-user-bill-page")
    public ResponseResult queryList(@RequestBody @Valid QueryBillPageParam pageParam) {

        PageInfo<UserBillDTO> pageInfo = userBillService.findListByAccountId(pageParam);

        return ResponseBuildUtil.success(pageInfo);
    }


}
