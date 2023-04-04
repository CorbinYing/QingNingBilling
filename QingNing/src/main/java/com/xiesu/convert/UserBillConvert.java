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
package com.xiesu.convert;

import com.xiesu.controller.bill.param.AddBillParam;
import com.xiesu.domain.UserBill;
import com.xiesu.dto.bill.UserBillDTO;
import com.xiesu.vo.bill.UserBillVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author xiesu created on 2023/3/26 12:09
 */
@Mapper
public interface UserBillConvert {

    UserBillConvert INSTANCE = Mappers.getMapper(UserBillConvert.class);

    UserBillDTO convert2UserBillDTO(AddBillParam billParam);

    UserBillDTO convert2UserBillDTO(UserBill userBill);

    UserBillVO convert2UserBillVO(UserBillDTO billDTO);

    UserBill convert2UserBill(UserBillDTO userBillDTO);

}
