/*
 *
 *
 * 项目名称 : interfacePlatform
 * 创建日期 : 2017年7月12日
 * 修改历史 :
 *     1. [2017年7月12日]创建文件 by zsh
 */
package interfacePlatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * //TODO 添加类/接口功能描述
 * @author zsh
 */

@Table(name="user")
@Entity
@Proxy(lazy = false)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class UserModel extends BaseHibernateModel {

    @Column(length=32)
    private String userName;

    @Column(length=32)
    /**上面的length对应数据库列的长度*/
    private String age;


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

}
