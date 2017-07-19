package interfacePlatform.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import interfacePlatform.dao.IHibernateGenericDao;
import interfacePlatform.model.PageResults;

/**
 * @Repository只能标注在DAO类上。因为该注解的作用不仅是将类识别为Bean，同时
 * 它还能降所标注的类中跑出的数据访问异常封装为Spring的数据访问异常类型，
 * @Component是一个泛华的概念，仅仅表示一个组件（Bean），可以用在任何层次。
 * 而@Service通常作用在业务层，但是目前该功能与 @Component 相同。
 * @Controller通常作用在控制层。但是目前该功能与 @Component 相同。
 * 通过以上的注解，Spring会自动创建相应的BeanDefinition对象，并注册到
 * ApplicationContext中，这些类就成了Spring的受管组件。
 * 原理：当一个Bean被自动检测到时，会根据那个扫描器的BeanNameGenerator策略
 * 生成Bean名称。默认情况下，对于上面四中注解，会把name取值昨晚Bean的名字。
 */
@Repository("hibernateGenericDao")
public class HibernateGenericDaoImpl<T, ID extends Serializable> implements IHibernateGenericDao<T, ID>  {
	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> entityClass;
	
	protected Class getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

	@Override
	public <T> T save(T o) {
		// TODO Auto-generated method stub
		Session session = getSession();
		session.save(o);
		//此句是强制性地将缓存中的user数据与数据库中的同步，从而使得数据保存到数据库中。
		session.flush();
		return o;
	}
	@Override
	public <T> T get(Class<T> entityClass, Serializable id) {
		// TODO Auto-generated method stub
       T rT=null;
		try {
			rT=(T) getSession().get(entityClass, id);
		} catch (Exception e) {
			rT=null;
			System.out.println(e.getMessage());
		}
		return rT;
	}
	@Override
	public void delete(Object o) {
		try{
			getSession().delete(o);
			getSession().flush();
		}catch(Exception e){
			System.out.println("数据库表中无你要查询的数据");
			e.printStackTrace();
			
		}
		
		
	}
     private Session getSession(){
    	 return  sessionFactory.getCurrentSession();
     }
	
	@Override
	public void saveOrUpdate(Object o) {
		// TODO Auto-generated method stub
		Session session = getSession();
		session.saveOrUpdate(o);  
		session.flush();
	}
	@Override
	public void delete(List objs) {
		// TODO Auto-generated method stub
		for(Object o:objs){
			delete(0);
		}
		getSession().flush();
	}
	@Override
	public <T> void removeById(Class<T> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		delete(get(entityClass, id));
		getSession().flush();
	}
	@Override
	public List find(String hql, Object... values) {
		// TODO Auto-generated method stub
		Query query = this.getSession().createQuery(hql);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
//		return null;
	}

	@Override
	public boolean deleteById(Class<T> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		T t = get(entityClass,id);
        if(t == null){
            return false;
        }
        delete(t);
        getSession().flush();
       return true;
	}

	@Override
	public void queryHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		Query query = this.getSession().createQuery(hql);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
	}

	@Override
	public void querySql(String sqlString, Object... values) {
		// TODO Auto-generated method stub
		Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
	}

	@Override
	public Object findSingle(String hql, Object... values) {
		// TODO Auto-generated method stub
		Query query = this.getSession().createQuery(hql);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.uniqueResult();
		
	}

	@Override
	public void refresh(T t) {
		// TODO Auto-generated method stub
		getSession().refresh(t);
	}

	@Override
	public Long countByHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		Query query = this.getSession().createQuery(hql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (Long) query.uniqueResult();
	}

	@Override
	public PageResults<T> findPageByFetchedHql(String hql, String countHql,
			int pageNo, int pageSize, Object... values) {
		// TODO Auto-generated method stub
		PageResults<T> retValue = new PageResults<T>();
        Query query = this.getSession().createQuery(hql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        int currentPage = pageNo > 1 ? pageNo : 1;
        retValue.setCurrentPage(currentPage);
        retValue.setPageSize(pageSize);
        if (countHql == null)
        {
            ScrollableResults results = query.scroll();
            results.last();
            retValue.setTotalCount(results.getRowNumber() + 1);// 设置总记录数
        }
        else
        {
            Long count = countByHql(countHql, values);
            retValue.setTotalCount(count.intValue());
        }
        retValue.resetPageNo();
        List<T> itemList = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        if (itemList == null)
        {
            itemList = new ArrayList<T>();
        }
        retValue.setResults(itemList);
         
        return retValue;
	}
	
    
}
