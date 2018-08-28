/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 兴业移动支付字段映射工具类
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年12月29日 下午6:26:50
 */
public abstract class CibXmlFieldMappingUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibXmlFieldMappingUtils.class);
    
    public static final String SIGN_FIELD_NAME = "sign";
    public static final Map<Class<?>, Map<String, String>> CLASS_XML_FIELD_MAP = new HashMap<>();

    /**
     * 获取对象字段xml别名与值的映射表
     * 
     * @param bean
     * @return
     */
    public static Map<String, String> getObjectXmlFieldValueMap(Object bean) {
        Map<String, String> xmlFieldValueMap = new HashMap<>();

        Map<String, String> fieldNameMap = CLASS_XML_FIELD_MAP.get(bean.getClass());
        if (fieldNameMap == null || fieldNameMap.isEmpty()) {
            fieldNameMap = getXmlFieldNameMap(bean.getClass());
            CLASS_XML_FIELD_MAP.put(bean.getClass(), fieldNameMap);
        }
        try {
            for (String key : fieldNameMap.keySet()) {
                Object valObj = PropertyUtils.getProperty(bean, key);
                if (valObj == null) {
                    continue;
                }
                String valStr = valObj.toString();
                if (StringUtils.isBlank(valStr)) {
                    continue;
                }
                xmlFieldValueMap.put(fieldNameMap.get(key), valStr);
            }
        } catch (Exception e) {
            LOGGER.error("getObjectXmlFieldValueMap error!", e);
        }
        return xmlFieldValueMap;
    }

    /**
     * 获取xml字段名映射表
     * 
     * @param clazz
     * @return
     */
    private static Map<String, String> getXmlFieldNameMap(Class<?> clazz) {
        Map<String, String> fieldNameMap = new HashMap<>();
        while (clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if(SIGN_FIELD_NAME.equals(field.getName())){
                    continue;
                }
                XmlElement annotaion = field.getAnnotation(XmlElement.class);
                if (annotaion != null && StringUtils.isNotBlank(annotaion.name())) {
                    fieldNameMap.put(field.getName(), annotaion.name());
                }
            }
            // 获取父类
            clazz = clazz.getSuperclass();
        }
        return fieldNameMap;
    }
}
