package cn.tomsnail.mybatis.dialect;

public abstract class Dialect
{
  public abstract String getLimitString(String paramString, int paramInt1, int paramInt2);

  public abstract String getCountString(String paramString);
}

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.dialect.Dialect
 * JD-Core Version:    0.5.4
 */