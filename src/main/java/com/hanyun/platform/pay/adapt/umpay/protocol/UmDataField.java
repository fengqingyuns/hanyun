/**
 * 
 */
package com.hanyun.platform.pay.adapt.umpay.protocol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * 报文字段值
 * @author liyinglong@hanyun.com
 * @date 2016年12月15日 下午4:41:01
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class UmDataField {
    @XmlAttribute(name="v")
    private String value;
    
    public UmDataField() {
    }
    
    public UmDataField(String value) {
        this.value = value;
    }
    
    public static UmDataField valueOf(String value) {
        return new UmDataField(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
