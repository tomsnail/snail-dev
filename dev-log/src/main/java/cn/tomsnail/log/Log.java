package cn.tomsnail.log;

import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;


/**
 *        日志模型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 上午9:28:35
 * @see 
 */
public class Log {
	
	private static final ObjectMapper mapper = new ObjectMapper();  
	
	public static final int INFO = 1;
	
	public static final int ERROR = 0;
	
	public static final int DEBUG = 2;
	
	public static final int NOLOG = -1;
	
	public static final int B_ERROR = 3;
	
	/**
	 * 应用、模块名
	 */
	private String module;
	/**
	 * 目标
	 */
	private String target;
	/**
	 * uuid
	 */
	private String uuid;
	/**
	 * 方法开始时间
	 */
	private String startTime;
	/**
	 * 方法结束时间
	 */
	private String endTime;
	/**
	 * 花费时间
	 */
	private String costTime;
	/**
	 * 方法附加描述
	 */
	private String desc;
	/**
	 * 类名
	 */
	private String className;
	/**
	 * 方法名
	 */
	private String methodName;
	/**
	 * 参数，debug时有效
	 */
	private StringBuffer params = new StringBuffer();
	/**
	 * 返回结果，debug时有效
	 */
	private StringBuffer results = new StringBuffer();
	/**
	 * 运行异常信息
	 */
	private StringBuffer content = new StringBuffer();
	/**
	 * 是否记得到全部日志系统
	 */
	private boolean shared = true;
	/**
	 * 
	 */
	private int level = Log.INFO;
	
	private Throwable throwable;
	
	private boolean isError;
	
	public Log(){
		uuid = UUID.randomUUID().toString()+System.currentTimeMillis();
	}

	public StringBuffer getContent() {
		return content;
	}

	public void setContent(StringBuffer content) {
		this.content = content;
	}
	
	public void addContent(StringBuffer content) {
		this.content.append(content);
	}
	
	public void addContent(String content) {
		this.content.append(content);
	}
	
	public void addParams(String param){
		this.params.append(param);
	}
	
	public void addResult(String result){
		this.results.append(result);
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCostTime() {
		return costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public StringBuffer getParams() {
		return params;
	}

	public void setParams(StringBuffer params) {
		this.params = params;
	}

	public StringBuffer getResults() {
		return results;
	}

	public void setResults(StringBuffer results) {
		this.results = results;
	}

	@Override
	public String toString(){
		try {
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}
	
	
	
}
