package cn.tomsnail.framwork.validator;

import java.util.HashMap;
import java.util.Map;

import cn.tomsnail.framwork.validator.exception.ParamValidatorException;


public class ValidatorTest {


	public  static void main(String[] args) throws Exception {
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", "1234d");
		map.put("t", "@");
		map.put("t1", "s");
	
		long d3 = System.currentTimeMillis();
		for(int i=0;i<1;i++){
			try {
				String userId = ValidatorFactory.getStringValidator().add(RuleType.NotNull).add(RuleType.MustNumber).getValidatorValue(map, "userId");
				String userId1 = ValidatorFactory.getStringValidator().add(RuleType.ESCAPE).getValidatorValue(map, "t");
				System.out.println(userId1);
				Integer userId2 = ValidatorFactory.getIntegerValidator().getValidatorValue(map, "t1", "t1验证错误");
			} catch (ParamValidatorException e) {
				System.out.println(e.getMessage());
				//ValidatorFailHandler.handler(e.getMessage(), "");
			}
		}
		long d4 = System.currentTimeMillis();
	}
	
	
}

class TestModel{
	
	private String userId;
	private Long orderId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public TestModel(String userId, Long orderId) {
		super();
		this.userId = userId;
		this.orderId = orderId;
	}
	
	
}
