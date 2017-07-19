/*
 * 
 * 
 * 项目名称 : interfacePlatform
 * 创建日期 : 2017年7月5日
 * 修改历史 : 
 *     1. [2017年7月5日]创建文件 by zsh
 */
package interfacePlatform.dto;

import java.io.Serializable;

/**
 * //TODO 添加类/接口功能描述
 * @author zsh
 */
public class Person implements Serializable{
   
    private static final long serialVersionUID = -4871652714748272908L;
    
    private String name;
    private int age;
    private transient String idCard;//不序列化
    
    private String [] langueage;
    

    public Person(){
        
    }
    
    /**
     * 
     * @param name
     * @param age
     * @param idCard
     */
    public Person(String name, int age, String idCard) {
        this.name = name;
        this.age = age;
        this.idCard = idCard;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the age.
     */
    public int getAge() {
        return age;
    }
    /**
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * @return Returns the idCard.
     */
    public String getIdCard() {
        return idCard;
    }
    /**
     * @param idCard The idCard to set.
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    
    /**
     * @return Returns the langueage.
     */
    public String[] getLangueage() {
        return langueage;
    }
    
    /**
     * @param langueage The langueage to set.
     */
    public void setLangueage(String[] langueage) {
        this.langueage = langueage;
    }
    
    
}
