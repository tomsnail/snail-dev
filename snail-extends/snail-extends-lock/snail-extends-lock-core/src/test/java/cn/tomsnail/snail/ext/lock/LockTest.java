package cn.tomsnail.snail.ext.lock;

import cn.tomsnail.snail.ext.lock.core.ILock;
import cn.tomsnail.snail.ext.lock.core.LockTarget;
import cn.tomsnail.snail.ext.lock.core.annotation.Lock;
import org.springframework.stereotype.Component;

@Component
public class LockTest {

    @Lock(name = "",target = LockTarget.LOCAL,url = "")
    ILock lock;

    public void test(){
        lock.lock();
        System.out.println(System.currentTimeMillis());
        lock.unLock();
    }
}
