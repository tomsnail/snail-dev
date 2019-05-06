package cn.tomsnail.snail.example.ext.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CacheService {

    private final AtomicInteger num = new AtomicInteger(0);

    @Cacheable(cacheNames="test")
    public Integer getNum() {
        return num.incrementAndGet();
    }

    @Cacheable(cacheNames="testBean")
    public CacheBean testBean() {
        CacheBean bean = new CacheBean();
        bean.setNum(num.incrementAndGet());
        return bean;
    }

    @CacheEvict(cacheNames={"testBean"})
    public void evict() {

    }

    public void reset() {
        num.set(0);
    }

}
