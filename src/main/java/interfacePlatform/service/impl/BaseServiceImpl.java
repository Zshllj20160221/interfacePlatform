/*
 * 
 * 
 * 项目名称 : interfacePlatform
 * 创建日期 : 2017年7月13日
 * 修改历史 : 
 *     1. [2017年7月13日]创建文件 by zsh
 */
package interfacePlatform.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import interfacePlatform.dao.IHibernateGenericDao;
import interfacePlatform.model.PageResults;
import interfacePlatform.service.IBaseService;


/** 
 * <!-- begin-UML-doc -->
 * 处理业务管理的基类接口实现
 * <!-- end-UML-doc -->
 * @author luchongbin
 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
//@Transactional
public class BaseServiceImpl implements IBaseService{
    
    @Autowired
    private IHibernateGenericDao hibernateGenericDao;

    @Override
    public <T> T save(T o) {
        // TODO Auto-generated method stub
        return (T) hibernateGenericDao.save(o);
    }

    @Override
    public <T> T get(Class<T> entityClass, Serializable id) {
        // TODO Auto-generated method stub
        return (T)hibernateGenericDao.get(entityClass, id);
    }

    @Override
    public void delete(Object o) {
        // TODO Auto-generated method stub
        hibernateGenericDao.delete(o);
        
    }
    @Override
    public void delete(List objs) {
        // TODO Auto-generated method stub
        hibernateGenericDao.delete(objs);
    }

    @Override
    public <T> void removeById(Class<T> entityClass, Serializable id) {
        // TODO Auto-generated method stub
        hibernateGenericDao.removeById(entityClass, id);
    }

    @Override
    public void saveOrUpdate(Object o) {
        // TODO Auto-generated method stub
        hibernateGenericDao.saveOrUpdate(o);
    }

    @Override
    public List find(String hql, Object... values) {
        // TODO Auto-generated method stub
        return hibernateGenericDao.find(hql, values);
    }

    @Override
    public <T> boolean deleteById(Class<T> entityClass, Serializable id) {
        // TODO Auto-generated method stub
        return hibernateGenericDao.deleteById(entityClass, id);
    }

    @Override
    public void queryHql(String hql, Object... values) {
        // TODO Auto-generated method stub
        hibernateGenericDao.queryHql(hql, values);
        
    }

    @Override
    public void querySql(String sqlString, Object... values) {
        // TODO Auto-generated method stub
        hibernateGenericDao.querySql(sqlString, values);
        
    }

    @Override
    public Object findSingle(String hql, Object... values) {
        // TODO Auto-generated method stub
        return hibernateGenericDao.findSingle(hql, values);
    }

    @Override
    public <T> void refresh(T t) {
        // TODO Auto-generated method stub
        hibernateGenericDao.refresh(t);
    }

    @Override
    public Long countByHql(String hql, Object... values) {
        // TODO Auto-generated method stub
        return hibernateGenericDao.countByHql(hql, values);
    }

    @Override
    public <T> PageResults<T> findPageByFetchedHql(String hql, String countHql,
            int pageNo, int pageSize, Object... values) {
        // TODO Auto-generated method stub
        return hibernateGenericDao.findPageByFetchedHql(hql, countHql, pageNo, pageSize, values);
    }
    

}
