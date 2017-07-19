package interfacePlatform.core.util;

import org.springframework.util.StringUtils;
import interfacePlatform.core.frame.SpringContextHolder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class JedisUtils {

	private final static JedisPool jedisPool = SpringContextHolder.getBean(
			"jedisPool", JedisPool.class);

	/**
	 * 获取jedis连接
	 * 
	 * @return
	 */
	public static Jedis getJedis() {
		return jedisPool.getResource();
	}
	
	/**
	 * 判斷Key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean exist(String key) {
	    Jedis jedis = getJedis();
		try{
			if (jedis.exists(key)) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		}catch (Exception e) {
            if(jedis!=null) jedis.close();
            return Boolean.FALSE;
        }
	}

	/**
	 * 根据Key获取Value
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = "";
		Jedis jedis = getJedis();
		try {
			if (jedis.exists(key)) {
				value = jedis.get(key);
				value = StringUtils.isEmpty(value)? "":value;
			}
		}catch (Exception e) {
            if(jedis!=null) jedis.close();
        }
		return value;
	}

	/**
	 * 根据Key获取Value
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] get(byte[] key) {
	    Jedis jedis = getJedis();
		try {
			if (jedis.exists(key)) {
				return jedis.get(key);
			} else {
				return null;
			}
		}catch (Exception e) {
            if(jedis!=null) jedis.close();
            return null;
        }
	}

	/**
	 * 缓存二进制对象到Redis
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(byte[] key, byte[] value, int seconds) {
	    Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
			if (seconds != 0)
				jedis.expire(key, seconds);
		}catch (Exception e) {
            if(jedis!=null) jedis.close();
        }
	}

	/**
	 * 缓存KV到Redis
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public static void set(String key, String value, int seconds) {
	    Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
			if (seconds != 0)
				jedis.expire(key, seconds);
		}catch (Exception e) {
            if(jedis!=null) jedis.close();
        }
	}

	/**
	 * 删除key
	 * //TODO 添加方法功能描述
	 * @param key
	 */
	public static void delete(String key) {
	    Jedis jedis = getJedis();
		try{
			if (jedis.exists(key))
				jedis.del(key);
		}catch (Exception e) {
            if(jedis!=null) jedis.close();
        }
	}

	/**
	 * 设置KV同时设置有效时间
	 * 
	 * @param key
	 * @param seconds
	 * @param value
	 */
	public static String setEx(String key, int seconds, String value) {
	    Jedis jedis = getJedis();
		try{
			return jedis.setex(key, seconds, value);
		}catch (Exception e) {
		    if(jedis!=null) jedis.close();
		    return null;
		}
		
		
	}

	/**
	 * 自增1
	 * 
	 * @param key
	 * @return
	 */
	public static long incrBy(String key) {
	    Jedis jedis = getJedis();
		try {
			return jedis.incr(key);
		}catch(Exception e){
		    if(jedis!=null) jedis.close();
		    return 0;
		}
	}

	/**
	 * 返回Jedis连接
	 * 
	 * @param jedis
	 */
	public static void closeJedis(Jedis jedis) {
		if (null != jedis) {
			jedis.close();
		}
	}
	
	public static void set(String key,String value){
	    Jedis jedis = getJedis();
	    try{
	        if(jedis!=null) jedis.set(key, value);
	    }catch(Exception e){
	        if(jedis!=null) jedis.close();
	    }
	}
}
