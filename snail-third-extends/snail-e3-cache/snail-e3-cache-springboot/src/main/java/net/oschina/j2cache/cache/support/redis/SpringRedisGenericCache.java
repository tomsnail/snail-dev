package net.oschina.j2cache.cache.support.redis;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import net.oschina.j2cache.Level2Cache;

public class SpringRedisGenericCache implements Level2Cache {

	private final static Logger log = LoggerFactory.getLogger(SpringRedisGenericCache.class);

	private String namespace;

	private String region;

	private RedisTemplate<String, Serializable> redisTemplate;

	public SpringRedisGenericCache(String namespace, String region, RedisTemplate<String, Serializable> redisTemplate) {
		if (region == null || region.isEmpty()) {
			region = "_"; // 缺省region
		}
		this.namespace = namespace;
		this.redisTemplate = redisTemplate;
		this.region = getRegionName(region);
	}

	private String getRegionName(String region) {
		if (namespace != null && !namespace.isEmpty())
			region = namespace + ":" + region;
		return region;
	}

	@Override
	public void clear() {
		Collection<String> keys = keys();
		keys.stream().forEach(k -> {
			redisTemplate.delete(k);
		});
	}

	@Override
	public boolean exists(String key) {
		return 	redisTemplate.execute((RedisCallback<Boolean>) redis -> {	
			return redis.exists(_key(key));
		});
	}

	@Override
	public void evict(String... keys) {
		for (String k : keys) {
			redisTemplate.execute((RedisCallback<Long>) redis -> {	
				return redis.del(_key(k));
			});
		}
	}

	@Override
	public Collection<String> keys() {
		Set<String> list = redisTemplate.keys(this.region + ":*");
		List<String> keys = new ArrayList<>(list.size());
		for (String s : list) {
			keys.add(s);
		}
		return keys;
	}

	@Override
	public byte[] getBytes(String key) {
		return redisTemplate.execute((RedisCallback<byte[]>) redis -> {
			return redis.get(_key(key));
		});
	}

	@Override
	public List<byte[]> getBytes(Collection<String> keys) {
		return redisTemplate.execute((RedisCallback<List<byte[]>>) redis -> {
			byte[][] bytes = keys.stream().map(k -> _key(k)).toArray(byte[][]::new);
			return redis.mGet(bytes);
		});
	}

	@Override
	public void setBytes(String key, byte[] bytes, long timeToLiveInSeconds) {
		if (timeToLiveInSeconds <= 0) {
			log.debug(String.format("Invalid timeToLiveInSeconds value : %d , skipped it.", timeToLiveInSeconds));
			setBytes(key, bytes);
		} else {
			redisTemplate.execute((RedisCallback<List<byte[]>>) redis -> {
				redis.setEx(_key(key), (int) timeToLiveInSeconds, bytes);
				return null;
			});
		}
	}

	@Override
	public void setBytes(Map<String, byte[]> bytes, long timeToLiveInSeconds) {
		bytes.forEach((k, v) -> setBytes(k, v, timeToLiveInSeconds));
	}

	@Override
	public void setBytes(String key, byte[] bytes) {
		redisTemplate.execute((RedisCallback<byte[]>) redis -> {
			redis.set(_key(key), bytes);
			return null;
		});
	}

	@Override
	public void setBytes(Map<String, byte[]> bytes) {
		 bytes.forEach((k,v) -> setBytes(k, v));
	}

	private byte[] _key(String key) {
		byte[] k;
		try {
			k = (this.region + ":" + key).getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			k = (this.region + ":" + key).getBytes();
		}
		return k;
	}
}
