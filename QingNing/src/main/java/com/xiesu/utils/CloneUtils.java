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
package com.xiesu.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Java对象克隆工具类
 *
 * @author xiesu
 */
public final class CloneUtils {

    /**
     * 深度克隆
     *
     * @param obj 待克隆对象
     * @param <T> 指定类型
     * @return 新的深克隆对象
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepClone(T obj) {
        // JDK序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        T cloneObj;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            // JDK反序列化
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            cloneObj = (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cloneObj;
    }


}
