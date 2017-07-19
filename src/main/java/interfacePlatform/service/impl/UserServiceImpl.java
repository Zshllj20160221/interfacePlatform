/*
 * 
 * 
 * 项目名称 : interfacePlatform
 * 创建日期 : 2017年7月13日
 * 修改历史 : 
 *     1. [2017年7月13日]创建文件 by zsh
 */
package interfacePlatform.service.impl;

import java.util.List;

import interfacePlatform.model.UserModel;
import org.springframework.stereotype.Service;

import interfacePlatform.service.IUserService;

/**
 * //TODO 添加类/接口功能描述
 * @author zsh
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements IUserService{

    /**
     * //TODO 添加override说明
     * @see interfacePlatform.service.IUserService#getUserModel(String)
     **/
    @Override
    public UserModel getUserModel(String id) {
        return super.get(UserModel.class, id);
    }

    /**
     * //TODO 添加override说明
     * @see interfacePlatform.service.IUserService#getAllUserModel()
     **/
    @Override
    public List<UserModel> getAllUserModel() {
        String hql = "from UserModel";
        return super.find(hql);
    }

    /**
     * //TODO 添加override说明
     * @see interfacePlatform.service.IUserService#addUserModel(UserModel)
     **/
    @Override
    public void addUserModel(UserModel user) {
        super.saveOrUpdate(user);
    }

    /**
     * //TODO 添加override说明
     * @see interfacePlatform.service.IUserService#delUserModel(String)
     **/
    @Override
    public boolean delUserModel(String id) {
        return super.deleteById(UserModel.class, id);
    }

    /**
     * //TODO 添加override说明
     * @see interfacePlatform.service.IUserService#updateUserModel(UserModel)
     **/
    @Override
    public boolean updateUserModel(UserModel user) {
        super.saveOrUpdate(user);
        return true;
    }

}
