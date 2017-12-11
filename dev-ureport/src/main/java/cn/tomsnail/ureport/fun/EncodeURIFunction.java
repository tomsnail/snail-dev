package cn.tomsnail.ureport.fun;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.bstek.ureport.build.Context;
import com.bstek.ureport.exception.ReportComputeException;
import com.bstek.ureport.expression.function.Function;
import com.bstek.ureport.expression.model.data.ExpressionData;
import com.bstek.ureport.expression.model.data.ObjectExpressionData;
import com.bstek.ureport.expression.model.data.ObjectListExpressionData;
import com.bstek.ureport.model.Cell;

public class EncodeURIFunction implements Function{

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context,
			Cell currentCell) {
		if(dataList==null || dataList.size()<1){
			throw new ReportComputeException("Function [param] need one parameter.");
		}
		Object obj=null;
		ExpressionData<?> data=dataList.get(0);
		if(data instanceof ObjectExpressionData){
			ObjectExpressionData objData=(ObjectExpressionData)data;
			obj=objData.getData();
		}else if(data instanceof ObjectListExpressionData){
			ObjectListExpressionData listData=(ObjectListExpressionData)data;
			List<?> list=listData.getData();
			if(list.size()>0){
				obj=list.get(0);
			}
		}
		if(obj==null){
			throw new ReportComputeException("Function [param] need one parameter.");
		}
		Map<String,Object> map=context.getParameters();
		if(map==null){
			return null;
		}
		Object t = map.get(obj.toString());
		
		try {
			System.out.println(t.toString());
			//String url = t.toString().replaceAll("%(?![0-9a-fA-F]{2})", "%25");  

			String urlStr = URLDecoder.decode(t.toString(), "UTF-8");
			System.out.println(urlStr);
			System.out.println(URLDecoder.decode(t.toString(), "GBK"));
			return urlStr;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		return t;
	}

	@Override
	public String name() {
		return "encodeURI";
	}

}
