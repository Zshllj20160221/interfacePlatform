/*
 * 
 * 
 * 项目名称 : interfacePlatform
 * 创建日期 : 2017年7月13日
 * 修改历史 : 
 *     1. [2017年7月13日]创建文件 by zsh
 */
package interfacePlatform.service;

import java.io.Serializable;
import java.util.List;

import interfacePlatform.model.PageResults;



/** 
 * <!-- begin-UML-doc -->
 * 处理业务管理的基类接口
 * <!-- end-UML-doc -->
 * @author luchongbin
 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IBaseService {


    
    /** 
    * 保存记录. 
    * 
    * @param obj 
    */  

   public <T> T save(T o);

   /**
    * 根据ID获取对象. 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常.
    */
   public <T> T get(Class<T> entityClass, Serializable id);

   /** 
    * 删除记录. 
    * 
    * @param obj 
    */  
   public void delete(Object o);
   /**
    * 删除对象集
    * @param objs
    */
   public void delete(List objs);
   /**
    * 根据ID删除对象.
    */
   public <T> void removeById(Class<T> entityClass, Serializable id);
   
    /** 
    * 保存或修改记录. 
    * 
    * @param obj 
    */  
   public void saveOrUpdate(Object o);
   /**
    * 根据hql查询
    *
    * @param values 可变参数,见{@link #createQuery(String,Object...)}
    */
   public List find(String hql, Object... values);
   
   public <T> boolean deleteById(Class<T> entityClass, Serializable id);
   /**
    * <执行Hql语句>
    * @param hqlString hql
    * @param values 不定参数数组
    * @see com.itv.launcher.util.IBaseDao#queryHql(String, Object[])
    */
   public void queryHql(String hql, Object... values);
   /**
    * <执行Sql语句>
    * @param sqlString sql
    * @param values 不定参数数组
    * @see com.itv.launcher.util.IBaseDao#querySql(String, Object[])
    */
   public void querySql(String sqlString, Object... values);
   /**
    * <根据HQL语句查找唯一实体>
    * @param hqlString HQL语句
    * @param values 不定参数的Object数组
    * @return 查询实体
    * @see com.itv.launcher.util.IBaseDao#getByHQL(String, Object[])
    */
   public Object findSingle(String hql, Object... values);
    /**
    * <refresh>
    * @param t 实体
    * @see com.itv.launcher.util.IBaseDao#refresh(Object)
    */
   public <T> void refresh(T t);
   /**
    * <根据HQL得到记录数>
    * @param hql HQL语句
    * @param values 不定参数的Object数组
    * @return 记录总数
    * @see com.itv.launcher.util.IBaseDao#countByHql(String, Object[])
    */
   public Long countByHql(String hql, Object... values);
    /**
    * <HQL分页查询>
    * @param hql HQL语句
    * @param countHql 查询记录条数的HQL语句
    * @param pageNo 下一页
    * @param pageSize 一页总条数
    * @param values 不定Object数组参数
    * @return PageResults的封装类，里面包含了页码的信息以及查询的数据List集合
    * @see com.itv.launcher.util.IBaseDao#findPageByFetchedHql(String, String, int, int, Object[])
    */
   public <T> PageResults<T> findPageByFetchedHql(String hql, String countHql,
                                                  int pageNo, int pageSize, Object... values);
   

}
