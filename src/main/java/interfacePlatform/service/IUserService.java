/*
 * 
 * 
 * 项目名称 : interfacePlatform
 * 创建日期 : 2017年7月13日
 * 修改历史 : 
 *     1. [2017年7月13日]创建文件 by zsh
 */
package interfacePlatform.service;

import interfacePlatform.model.UserModel;

import java.util.List;


/**
 * //TODO 添加类/接口功能描述
 * @author zsh
 */
public interface IUserService extends IBaseService {
    UserModel getUserModel(String id);
    List<UserModel> getAllUserModel();
    void addUserModel(UserModel user);
    boolean delUserModel(String id);
    boolean updateUserModel(UserModel user);
}
