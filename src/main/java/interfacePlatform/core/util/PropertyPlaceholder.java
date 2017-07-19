/**
 * 读取配置文件
 */
package interfacePlatform.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyPlaceholder extends PropertyPlaceholderConfigurer {

	private static Map<String, String> propertyMap;

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		propertyMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			propertyMap.put(keyStr, value);
		}
	}

	// static method for accessing context properties
	public static Object getProperty(String name) {
		if (propertyMap != null) {
			return propertyMap.get(name);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * static method for using
	 * @param key
	 * @return
	 */
    public static String get(Object key) {
        return getProperty(key.toString())==null?null:getProperty(key.toString()).toString();
    }
    
}
