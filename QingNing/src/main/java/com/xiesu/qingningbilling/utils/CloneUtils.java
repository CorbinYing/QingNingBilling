package com.xiesu.qingningbilling.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Java对象克隆工具类
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
