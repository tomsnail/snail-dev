package cn.tomsnail.weixin.util.token;


public interface MemoryCache<T> {

    T get(String key);

    void set(String key, T object);

    void remove(String key);

}
