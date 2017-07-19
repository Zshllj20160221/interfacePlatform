/*
 * 
 * 
 * 项目名称 : interfacePlatform
 * 创建日期 : 2017年7月12日
 * 修改历史 : 
 *     1. [2017年7月12日]创建文件 by zsh
 */
package interfacePlatform.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import interfacePlatform.model.UserModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import interfacePlatform.dao.IUserDao;

/**
 * //TODO 添加类/接口功能描述
 * @author zsh
 */
@Repository("userDao")
public class UserDaoimpl implements IUserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoimpl.class);

    @Resource
    protected SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean isExist(UserModel userModel) {
        Session session = this.getSession();
        String hql = "from UserModel where username=?";
        Query query = session.createQuery(hql);
        query.setString(0, userModel.getUserName());
        List list = query.list();
        if (list.size() >= 1) {
            logger.debug("查到");
            logger.debug(list.get(0).toString());
            return true;
        }
        
        logger.debug("查不到");
        return false;
    }

}
