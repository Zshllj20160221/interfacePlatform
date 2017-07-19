/*
 * 
 * 
 * 项目名称 : interfacePlatform
 * 创建日期 : 2017年7月12日
 * 修改历史 : 
 *     1. [2017年7月12日]创建文件 by zsh
 */
package interfacePlatform.dao;

import interfacePlatform.model.UserModel;

/**
 * //TODO 添加类/接口功能描述
 * @author zsh
 */
public interface IUserDao {
    public boolean isExist(UserModel userModel);
}
