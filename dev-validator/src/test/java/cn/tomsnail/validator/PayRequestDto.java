package cn.tomsnail.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cn.tomsnail.framwork.validator.ValidatorFactory;
import cn.tomsnail.framwork.validator.annotation.BeanValidator;
import cn.tomsnail.framwork.validator.annotation.FieldValidator;
import cn.tomsnail.framwork.validator.exception.ParamValidatorException;

@BeanValidator(isAllValidator=false)
public class PayRequestDto {
	
	 /**
     * 支付完成时间
     **/
    @NotEmpty(message="支付完成时间不能空")
    @Size(max=14,message="支付完成时间长度不能超过{max}位")
    @FieldValidator(onlyToBean=true,mapperName="PAY_TIME")
    private String payTime;
     
    /**
     * 状态
     **/
    @Pattern(regexp = "0[0123]", message = "状态只能为00或01或02或03")
    @FieldValidator(onlyToBean=true,mapperName="STATUS")
    private String status;
 
    public String getPayTime() {
        return payTime;
    }
 
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
    
    public static void main(String[] args) {
    	
    	PayRequestDto payRequestDto = null;
    	
    	Map<String,Object> vMap = new HashMap<String,Object>();
    	vMap.put("PAY_TIME", "11111111111");
    	vMap.put("STATUS", "06");
    	
    	try {
			payRequestDto = (PayRequestDto) ValidatorFactory.getHibernateValidator().getValidBean(vMap, PayRequestDto.class);
		} catch (ParamValidatorException e) {
			e.printStackTrace();
		}
    	
    	long d1 = System.currentTimeMillis();
    	try {
    		for (int i=0;i<1000;i++)
    			payRequestDto = (PayRequestDto) ValidatorFactory.getHibernateValidator().getValidBean(vMap, PayRequestDto.class);
			
		} catch (ParamValidatorException e) {
		}
    	
    	long d2 = System.currentTimeMillis();
    	System.out.println(d2-d1);
    	System.out.println(payRequestDto.getStatus());
	}

}
