package cn.tomsnail.mybatis.orm;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * String 转换为BigInt
 * @author yangsong
 * @date 2015年9月24日 下午12:24:43
 */
public class BigIntTypeHandler implements TypeHandler<String>{

	public String getResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getObject(columnName).toString();
	}

	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getObject(columnIndex).toString();
	}

	public String getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return cs.getObject(columnIndex).toString();
	}

	
	public void setParameter(PreparedStatement ps, int i, String parameter,
			JdbcType jdbcType) throws SQLException {
		//防止为null时，创建XMLType出现错误        
		if (StringUtils.isNotBlank(parameter)) {         
			BigInteger bigInteger = new BigInteger(parameter);
			ps.setObject(i, bigInteger);
		} else {
			ps.setString(i, null);
		}
	}

}
