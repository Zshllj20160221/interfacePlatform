package interfacePlatform.model;

/**
 * 
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

/** 
 * <!-- begin-UML-doc -->
 * 基于业务模型的实体类对象的超类。依赖于后台。
 * <!-- end-UML-doc -->
 * @author luchongbin
 * @param <T>
 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
@MappedSuperclass
@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true) 
public class BaseHibernateModel extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 4567041038811176723L;
	
	public BaseHibernateModel() {
		super();
	}
	public BaseHibernateModel(Integer id) {
	    super();
	    this.id = id;
	    
	    
	}
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	protected Integer id;
	
	@Transient
	private int hashCode = Integer.MIN_VALUE;
	/**
	 * 
	 * <p>
	 * 不映射到数据库
	 * </p>
	 */
	
	protected String name;
	
	
	/**
	 * 查询时使用，不用映射数据库字段。
	 */
	@Formula(value="database()")
	protected String dbName;
	
	@Transient
	private Map<String, String> propMap = new HashMap<String, String>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof BaseHibernateModel)) return false;
		else {
			BaseHibernateModel bo = (BaseHibernateModel) obj;
			if (null == this.getId() || null == bo.getId()) {
				return false;
			} else if (null == this.getDbName() || null == bo.getDbName()) {
				return false;
			} else  {
				return (this.getId().equals(bo.getId()) && this.getDbName().equals(bo.getDbName()));
			}
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}
	
	public String getDbName() {
		return dbName;
	}
	
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
		
	public Map<String, String> getPropMap() {
		return propMap;
	}
	public void setPropMap(Map<String, String> propMap) {
		this.propMap = propMap;
	}
	
	public String getProperty(String key) {
		return propMap.get(key);
	}
	
	public void setProperty(String key, String value) {
		propMap.put(key, value);
	}
	
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
	
	
	
}