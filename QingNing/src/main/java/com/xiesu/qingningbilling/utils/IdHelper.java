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
package com.xiesu.qingningbilling.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 获取全局唯一、趋势递增的分布式ID工具类
 */
public final class IdHelper {

    public static Long nextId() {
        return IdPool.ID_POOL.nextId();
    }

    public static String nextIdString() {
        return IdPool.ID_POOL.nextIdString();
    }


    public enum IdPool {
        ID_POOL;
        private static final String SNOW_FLAKE_ID_URL = "http://127.0.0.1:8080/api/snowflake/getList/key";
        private static final Queue<Long> ID_BLOCKING_QUEUE = new LinkedBlockingQueue<>(35000);

        private static void putIdBatch() {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(SNOW_FLAKE_ID_URL).build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                Objects.requireNonNull(response.body(), "未获取到分布式主键");
                String res = response.body().string();
                //返回结果转为List--Long，两种方式，一万个数据，耗时分别在26、28 ms左右
                List<Long> idList = new ObjectMapper().readValue(res, new TypeReference<>() {
                });
                //ObjectMapper objectMapper = new ObjectMapper();
                //List<Long> idList = objectMapper.readValue(res,
                //        objectMapper.getTypeFactory().constructParametricType(List.class, Long.class));

                ID_BLOCKING_QUEUE.addAll(idList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        private Long nextId() {
            if (ID_BLOCKING_QUEUE.size() < 20000) {
                putIdBatch();
            }
            return ID_BLOCKING_QUEUE.poll();
        }

        private String nextIdString() {
            return String.valueOf(nextId());
        }
    }
}
