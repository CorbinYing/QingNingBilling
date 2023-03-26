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
package com.xiesu.dto.label;

import com.xiesu.entity.UserLabel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author xiesu created on 2023/3/25 21:33
 */
@Mapper
public interface UserLabelMapping {

    UserLabelMapping INSTANCE = Mappers.getMapper(UserLabelMapping.class);


    UserLabelDTO convert(UserLabel userLabel);

    List<UserLabelDTO> convert(List<UserLabel> userLabelList);


}
