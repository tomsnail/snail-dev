package cn.tomsnail.snail.core.ds.mybatis.dialect;

public abstract class Dialect implements cn.tomsnail.snail.core.ds.dialect.Dialect
{

  public abstract String getCountString(String paramString);
  
  public boolean supportsLimit() {
	  return false;
  }
}

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.dialect.Dialect
 * JD-Core Version:    0.5.4
 */