package cn.tomsnail.unit.test;

public class ShutDownHook implements Runnable {

	private long startTime = 0l;

	private String desc;

	public ShutDownHook() {
		this(null);
	}

	public ShutDownHook(String desc) {
		this.desc = desc;
		Runtime rt = Runtime.getRuntime();
		rt.addShutdownHook(new Thread(this));
		startTime = System.currentTimeMillis();
	}

	@Override
	public void run() {
		if (this.desc == null) {
			this.desc = Thread.currentThread().getName();
		}
		System.out.println(this.desc + ":"
				+ (System.currentTimeMillis() - startTime));
	}

}
