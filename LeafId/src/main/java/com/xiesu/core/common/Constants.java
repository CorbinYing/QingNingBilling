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
package com.xiesu.core.common;

/**
 * 引用 <a href="https://github.com/Meituan-Dianping/Leaf">美团分布式id算法</a> 项目文件
 */
public class Constants {

    public static final String LEAF_SEGMENT_ENABLE = "leaf.segment.enable";
    public static final String LEAF_JDBC_URL = "leaf.jdbc.url";
    public static final String LEAF_JDBC_USERNAME = "leaf.jdbc.username";
    public static final String LEAF_JDBC_PASSWORD = "leaf.jdbc.password";

    public static final String LEAF_SNOWFLAKE_ENABLE = "leaf.snowflake.enable";
    public static final String LEAF_SNOWFLAKE_PORT = "leaf.snowflake.port";
    public static final String LEAF_SNOWFLAKE_ZK_ADDRESS = "leaf.snowflake.zk.address";
}
