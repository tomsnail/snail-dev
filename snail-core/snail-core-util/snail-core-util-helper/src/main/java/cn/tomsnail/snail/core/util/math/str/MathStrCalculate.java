package cn.tomsnail.snail.core.util.math.str;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * example: (1*(2+3*4)+(5+(6+7)*8)*9)/10
 * @author yangsong
 * @date 2012-7-15 上午10:35:16
 */
public class MathStrCalculate {
	
	/**
	 * 符号栈
	 * @author yangsong
	 * @date 2012-7-15 上午11:33:29
	 */
	private Stack<MathStackModel> _bct_stack = new Stack<MathStackModel>();
	/**
	 * 计算栈
	 * @author yangsong
	 * @date 2012-7-15 上午11:33:42
	 */
	private Stack<MathStackModel> _temp_stack = new Stack<MathStackModel>();
	
	/**
	 * 数组
	 * @author yangsong
	 * @date 2012-7-15 上午11:35:07
	 */
	private MathModel[] _maths = null;
	
	/**
	 * 0.预处理字符串将其转换为对象数组
	 * @author yangsong
	 * @date 2012-7-15 上午11:36:31
	 */
	private void step0(String s){
		List<MathModel> list = new ArrayList<MathModel>();
		char[] chs = s.toCharArray();
		for(int i=0;i<chs.length;i++){
			int j = i+1;
			char ch = chs[i];
			MathModel mm = new MathModel();
			if(ch=='('){
				mm.setName(MathDefine.LEFT_BRACKET);
			}else if(ch==')'){
				mm.setName(MathDefine.RIGHT_BACKET);
			}else if(ch=='+'){
				mm.setName(MathDefine.ADD);
			}else if(ch=='-'){
				mm.setName(MathDefine.DEL);
			}else if(ch=='*'){
				mm.setName(MathDefine.MUL);
			}else if(ch=='/'){
				mm.setName(MathDefine.DIV);
			}else{
				mm.setName(MathDefine.DIGIT);
				String str = String.valueOf(ch);
				
				while(j<chs.length){
					char tempChar = chs[j++];
					if(tempChar!='('&&tempChar!=')'&&tempChar!='+'&&tempChar!='-'&&tempChar!='*'&&tempChar!='/'){
						str = str + String.valueOf(tempChar);
					}else{
						break;
					}
				}
				i = j-2;
				mm.setValue(str);
				
			}
			list.add(mm);
			if(j>=chs.length){
				break;
			}
		}
		_maths = new MathModel[list.size()];
		for(int i=0;i<list.size();i++){
			_maths[i] = list.get(i);
		}
	}
	
	/**
	 * 1.循环遍历并对对括号进行压栈处理
	 * @author yangsong
	 * @date 2012-7-15 上午11:36:31
	 */
	private String step1(){
		for(int i=0;i<_maths.length;i++){
			MathModel mm = _maths[i];
			if(mm.getName().equals(MathDefine.DIGIT)){
				MathStackModel msm = new MathStackModel();
				if(i+1<_maths.length){
					MathModel mm1 = _maths[++i];
					if(!mm1.getName().equals(MathDefine.RIGHT_BACKET)){
						msm.setMathStr(mm);
						msm.setSign(mm1);
						_bct_stack.push(msm);
					}else{
						List<MathStackModel> list = new ArrayList<MathStackModel>();
						msm.setMathStr(mm);
						list.add(0,msm);
						MathStackModel msm1 = _bct_stack.pop();
						while(!msm1.getSign().getName().equals(MathDefine.LEFT_BRACKET)){
							list.add(0,msm1);
							if(!_bct_stack.isEmpty()){
								msm1 = _bct_stack.pop();
							}else{
								break;
							}
						}
						MathModel mm2 = new MathModel();
						mm2.setName(MathDefine.DIGIT);
						mm2.setValue(step2(list));
						msm.setMathStr(mm2);
						_bct_stack.push(msm);
					}
				}else{
					msm.setMathStr(mm);
					_bct_stack.push(msm);
				}
			}else if(mm.getName().equals(MathDefine.LEFT_BRACKET)){
				MathStackModel msm = new MathStackModel();
				msm.setSign(mm);
				_bct_stack.push(msm);
			}else if(mm.getName().equals(MathDefine.RIGHT_BACKET)){
				
				MathStackModel msm = new MathStackModel();
				List<MathStackModel> list = new ArrayList<MathStackModel>();
				MathStackModel msm1 = _bct_stack.pop();
				if(msm1.getSign()==null){
					list.add(0,msm1);
					msm1 = _bct_stack.pop();
				}
				while(!msm1.getSign().getName().equals(MathDefine.LEFT_BRACKET)){
					list.add(0,msm1);
					if(!_bct_stack.isEmpty()){
						msm1 = _bct_stack.pop();
					}else{
						break;
					}
				}
				MathModel mm2 = new MathModel();
				mm2.setName(MathDefine.DIGIT);
				mm2.setValue(step2(list));
				msm.setMathStr(mm2);
				_bct_stack.push(msm);
			}else{
				MathStackModel msm1 = _bct_stack.pop();
				msm1.setSign(mm);
				_bct_stack.push(msm1);
			}
		}
		List<MathStackModel> list = new ArrayList<MathStackModel>();
		for(MathStackModel msm:_bct_stack){
			list.add(msm);
		}
		return step2(list);
	}
	
	/**
	 * 2.计算
	 * @author yangsong
	 * @date 2012-7-15 上午11:37:41
	 */
	private String step2(List<MathStackModel> list){
		String result = "";
		for(int i=0;i<list.size();i++){
			MathStackModel msm0 = list.get(i);
//			if(msm0.getSign()!=null){
			if(_temp_stack.isEmpty()){
				_temp_stack.push(msm0);
			}else{
				MathStackModel msm1 = _temp_stack.peek();
				if(msm0.getSign()==null){
					String _result = msm1.getSign().calculate(msm1.getMathStr().getValue(), msm0.getMathStr().getValue());
					msm0.getMathStr().setValue(_result);
					_temp_stack.pop();
					_temp_stack.push(msm0);
					while(_temp_stack.size()!=1){
						msm0 = _temp_stack.pop();
						MathStackModel msm2 = _temp_stack.peek();
						if(msm0.getSign()==null){
							String _result1 = msm2.getSign().calculate(msm2.getMathStr().getValue(), msm0.getMathStr().getValue());
							msm0.getMathStr().setValue(_result1);
							_temp_stack.pop();
							_temp_stack.push(msm0);
						}
					}
				}else if(msm0.getSign().getName().getLevel()<=msm1.getSign().getName().getLevel()){
					String _result = msm1.getSign().calculate(msm1.getMathStr().getValue(), msm0.getMathStr().getValue());
					msm0.getMathStr().setValue(_result);
					_temp_stack.pop();
					_temp_stack.push(msm0);
				}else{
					_temp_stack.push(msm0);
				}
			}
		}
		result = _temp_stack.pop().getMathStr().getValue();
		return result;
	}
	
	public String calculate(String s){
		this.step0(s);
		String result = step1();
		_bct_stack.clear();
		_temp_stack.clear();
		_maths = null;
		return result;
	}
	
	public String calculate(String s,int length){
		String result = this.calculate(s);
		if(result.contains(".")){
			Double d = Double.valueOf(result);  
			BigDecimal b = new BigDecimal(d);  
			Double r = b.setScale(length,BigDecimal.ROUND_HALF_UP).doubleValue();  
			result = r.toString();
			String temp1 = result.split("\\.")[1];
			String temp0 = result.split("\\.")[0];
			if(temp1.length()<length){
				for(int i=temp1.length();i<length;i++){
					temp1 = temp1+"0";
				}
				result = temp0+"."+temp1;
			}
		}
		return result;
	}
	public static void main(String[] args){
		MathStrCalculate msc = new MathStrCalculate();
		String exp = "(2*(2+3*4)+(5+(6+7)*8)*9)/11";
		System.out.println(msc.calculate(exp,5));
	}
	
}
