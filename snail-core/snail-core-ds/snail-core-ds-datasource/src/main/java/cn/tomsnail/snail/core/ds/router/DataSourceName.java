package cn.tomsnail.snail.core.ds.router;

public class DataSourceName {
	
	private String name;
	
	private int weight;

	public String getName() {
		return name;
	}

	
	public int getWeight() {
		return weight;
	}


	public DataSourceName(String name, int weight) {
		super();
		this.name = name;
		this.weight = weight;
	}
	
	
	public boolean lt(DataSourceName dataSourceName){
		if(dataSourceName==null){
			return false;
		}
		return this.weight < dataSourceName.weight;
	}
	
	
	
	

}
