package cn.tomsnail.snail.e3.tx.weixin.util.token;


public interface MemoryCache<T> {

    T get(String key);

    void set(String key, T object);

    void remove(String key);

}
