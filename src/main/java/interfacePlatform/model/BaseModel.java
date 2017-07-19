/*
 * 
 * 
 * 项目名称 : interfacePlatform
 * 创建日期 : 2017年7月12日
 * 修改历史 : 
 *     1. [2017年7月12日]创建文件 by zsh
 */
package interfacePlatform.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * //TODO 添加类/接口功能描述
 * @author zsh
 */
public class BaseModel{

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
            ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
