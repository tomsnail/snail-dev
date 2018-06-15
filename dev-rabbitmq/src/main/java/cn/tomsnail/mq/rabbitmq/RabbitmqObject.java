package cn.tomsnail.mq.rabbitmq;

public class RabbitmqObject {
	
	
	protected String exchangeName;
	
	protected String queueName;
	
	protected String vHost;
	
	protected String ip;
	
	protected String username;
	
	protected String password;
	
	protected String routeKey;
	
	protected int port;
	
	protected boolean isInitd = false;
	
	protected String type;
	
	protected boolean eDurable = true;
	protected boolean eAutoDelete = false;
	
	protected boolean qDurable = true;
	protected boolean qExclusive = false;
	protected boolean qAutoDelete = false;
	
	protected boolean autoAck = true;
	
	
	protected boolean isReconnect = true;
	
	protected boolean isRun = true;

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getvHost() {
		return vHost;
	}

	public void setvHost(String vHost) {
		this.vHost = vHost;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(String routeKey) {
		this.routeKey = routeKey;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isInitd() {
		return isInitd;
	}

	public void setInitd(boolean isInitd) {
		this.isInitd = isInitd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean iseDurable() {
		return eDurable;
	}

	public void seteDurable(boolean eDurable) {
		this.eDurable = eDurable;
	}

	public boolean iseAutoDelete() {
		return eAutoDelete;
	}

	public void seteAutoDelete(boolean eAutoDelete) {
		this.eAutoDelete = eAutoDelete;
	}

	public boolean isqDurable() {
		return qDurable;
	}

	public void setqDurable(boolean qDurable) {
		this.qDurable = qDurable;
	}

	public boolean isqExclusive() {
		return qExclusive;
	}

	public void setqExclusive(boolean qExclusive) {
		this.qExclusive = qExclusive;
	}

	public boolean isqAutoDelete() {
		return qAutoDelete;
	}

	public void setqAutoDelete(boolean qAutoDelete) {
		this.qAutoDelete = qAutoDelete;
	}

	public boolean isAutoAck() {
		return autoAck;
	}

	public void setAutoAck(boolean autoAck) {
		this.autoAck = autoAck;
	}
	
	public String getUUID(){
		return exchangeName+queueName+ip+vHost;
	}

	public boolean isReconnect() {
		return isReconnect;
	}

	public void setReconnect(boolean isReconnect) {
		this.isReconnect = isReconnect;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}
	
	

}
