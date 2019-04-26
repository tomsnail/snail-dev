package cn.tomsnail.snail.example.core.framework.dubbo;




public class DemoServiceImpl implements DemoService{
	@Override
	public String hello(String name) {
		return "hello "+name;
	}

}
