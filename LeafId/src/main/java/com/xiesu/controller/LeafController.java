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
package com.xiesu.controller;

import com.xiesu.core.common.Result;
import com.xiesu.core.common.Status;
import com.xiesu.core.snowflake.SnowflakeService;
import com.xiesu.exception.LeafServerException;
import com.xiesu.exception.NoKeyException;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 获取全局有序-趋势递增id的http接口 引用 <a href="https://github.com/Meituan-Dianping/Leaf">美团分布式id算法</a>
 * 项目文件，并删除无关的Segment mode相关算法及方法
 *
 * @author xiesu
 */
@RestController
public class LeafController {

    @Resource
    private SnowflakeService snowflakeService;

    /**
     * 获取单个id
     *
     * @param key 标志key
     * @return 单个string id
     */
    @RequestMapping(value = "/api/snowflake/get/{key}")
    public String getSnowflakeId(@PathVariable("key") String key) {
        return get(key, snowflakeService.getId(key));
    }

    /**
     * 批次获取id,单次获取10000个
     *
     * @param key 标志key
     * @return list<Long>
     */
    @RequestMapping(value = "/api/snowflake/getList/{key}")
    public List<Long> getSnowflakeIdBarch(@PathVariable("key") String key) {
        List<Long> idList = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            Result result = snowflakeService.getId(key);
            idList.add(getSnowFlakeId(key, result));
        }
        return idList;
    }


    private String get(@PathVariable("key") String key, Result id) {
        Result result;
        if (key == null || key.isEmpty()) {
            throw new NoKeyException();
        }
        result = id;
        if (result.getStatus().equals(Status.EXCEPTION)) {
            throw new LeafServerException(result.toString());
        }
        return String.valueOf(result.getId());
    }


    private long getSnowFlakeId(@PathVariable("key") String key, Result id) {
        Result result;
        if (key == null || key.isEmpty()) {
            throw new NoKeyException();
        }
        result = id;
        if (result.getStatus().equals(Status.EXCEPTION)) {
            throw new LeafServerException(result.toString());
        }
        return result.getId();
    }

}
