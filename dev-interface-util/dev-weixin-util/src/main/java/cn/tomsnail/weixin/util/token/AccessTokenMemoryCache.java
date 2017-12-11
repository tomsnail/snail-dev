package cn.tomsnail.weixin.util.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class AccessTokenMemoryCache implements MemoryCache<AccessToken> {

    private Map<String, AccessToken> ats = new ConcurrentHashMap<String, AccessToken>();

    @Override
    public AccessToken get(String mpId) {
        return ats.get(mpId);
    }

    @Override
    public void set(String mpId, AccessToken object) {
        ats.put(mpId, object);
    }

    @Override
    public void remove(String mpId) {
        ats.remove(mpId);
    }

}
