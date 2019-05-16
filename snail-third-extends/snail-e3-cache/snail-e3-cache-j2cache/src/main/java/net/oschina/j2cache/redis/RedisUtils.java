package net.oschina.j2cache.redis;

import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

public class RedisUtils {

    /**
     * 初始化 Redis 连接池
     * @param props j2cache.properties
     * @param prefix configuration prefix
     * @return redis connection pool configuration object
     */
    public final static JedisPoolConfig newPoolConfig(Properties props, String prefix) {
        JedisPoolConfig cfg = new JedisPoolConfig();
        cfg.setMaxTotal(Integer.parseInt(props.getProperty(key(prefix,"maxTotal"), "-1")));
        cfg.setMaxIdle(Integer.parseInt(props.getProperty(key(prefix,"maxIdle"), "100")));
        cfg.setMaxWaitMillis(Integer.parseInt(props.getProperty(key(prefix,"maxWaitMillis"), "100")));
        cfg.setMinEvictableIdleTimeMillis(Integer.parseInt(props.getProperty(key(prefix,"minEvictableIdleTimeMillis"), "864000000")));
        cfg.setMinIdle(Integer.parseInt(props.getProperty(key(prefix,"minIdle"), "10")));
        cfg.setNumTestsPerEvictionRun(Integer.parseInt(props.getProperty(key(prefix,"numTestsPerEvictionRun"), "10")));
        cfg.setLifo(Boolean.parseBoolean(props.getProperty(key(prefix,"lifo"), "false")));
        cfg.setSoftMinEvictableIdleTimeMillis(Integer.parseInt((String)props.getOrDefault(key(prefix,"softMinEvictableIdleTimeMillis"), "10")));
        cfg.setTestOnBorrow(Boolean.parseBoolean(props.getProperty(key(prefix,"testOnBorrow"), "true")));
        cfg.setTestOnReturn(Boolean.parseBoolean(props.getProperty(key(prefix,"testOnReturn"), "false")));
        cfg.setTestWhileIdle(Boolean.parseBoolean(props.getProperty(key(prefix,"testWhileIdle"), "true")));
        cfg.setTimeBetweenEvictionRunsMillis(Integer.parseInt(props.getProperty(key(prefix,"timeBetweenEvictionRunsMillis"), "300000")));
        cfg.setBlockWhenExhausted(Boolean.parseBoolean(props.getProperty(key(prefix,"blockWhenExhausted"), "false")));
        return cfg;
    }

    private static String key(String prefix, String key) {
        return (prefix == null) ? key : prefix + "." + key;
    }

}
