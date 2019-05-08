package cn.tomsnail.snail.core.obj.base;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public class BaseTransactionComponent extends BaseComponent{

}
