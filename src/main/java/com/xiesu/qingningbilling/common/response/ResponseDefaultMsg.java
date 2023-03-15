package com.xiesu.qingningbilling.common.response;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义读取国际化配置文件
 *
 * @author xiesu
 */
public class ResponseDefaultMsg {

    private static final String RESOURCE_BUNDLE_NAME = "i18n/codeMessage";

    private static volatile Map<Locale, ResourceBundle> resourceBundleMap;


    public static String getDefaultMsg(Integer code) {
        return getDefaultMsg(code, null);
    }

    /**
     * 具体格式化方式{@link MessageFormat}
     * <p>
     * 格式化默认消息，,param 为null 则不进行格式化,参数个数小于表达式参数时，按顺序格式化，剩余表达式的不进行格式化 参数个数大于表达式参数时，只对第一个参数进行格式化
     * partern="{0},hello" param="lisi"  ----->result="lisi,hello" <p/>
     * <p>
     * partern="{0},{1},hello" param="lisi"  ----->result="lisi,{1},hello"
     * <p/>
     * partern="{0},{1},hello" param="lisi,zhangsan,wangwu"
     * ----->result="lisizhangsanwangwu,{1},hello"
     * <p/>
     * partern="hello" param="lisi" ----->result="hello"
     *
     * @param code   错误码
     * @param locale Local
     * @param params 允许为null
     * @return string msg
     */
    public static String getDefaultMsg(Integer code, Locale locale, Object... params) {
        assert code != null;
        if (locale == null) {
            locale = Locale.getDefault();
        }

        getResourceBundleMap(locale);
        ResourceBundle resourceBundle = resourceBundleMap.get(locale);
        String msg = resourceBundle.getString(String.valueOf(code));

        return params == null ? msg : MessageFormat.format(msg, params);
    }


    /**
     * 获取指定local的ResourceBundle
     */
    private static void getResourceBundleMap(Locale locale) {
        Objects.requireNonNull(locale);
        if (resourceBundleMap == null) {
            //此处this指的是调用者的线程对象
            synchronized (ResponseDefaultMsg.class) {
                if (resourceBundleMap == null) {
                    resourceBundleMap = new ConcurrentHashMap<>();
                }
            }
        }

        if (!resourceBundleMap.containsKey(locale)) {
            resourceBundleMap.put(locale, ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale));
        }

    }

}
