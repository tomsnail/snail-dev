package cn.tomsnail.snail.e3.ehcache3;

import java.nio.ByteBuffer;

import org.ehcache.impl.serialization.StringSerializer;
import org.ehcache.spi.serialization.Serializer;
import org.ehcache.spi.serialization.SerializerException;

import cn.tomsnail.snail.core.util.JsonUtil;

public class ObjectSerializer  implements Serializer<Object>{

	StringSerializer ss = new StringSerializer();
	
	
	@Override
	public ByteBuffer serialize(Object object) throws SerializerException {
		String json = JsonUtil.toJson(object);
		return ss.serialize(json);
	}

	@Override
	public Object read(ByteBuffer binary) throws ClassNotFoundException, SerializerException {
		String json = ss.read(binary);
		return JsonUtil.getObject(json, Object.class);
	}

	@Override
	public boolean equals(Object object, ByteBuffer binary) throws ClassNotFoundException, SerializerException {
		String json = JsonUtil.toJson(object);
		return ss.equals(json, binary);
	}

}
