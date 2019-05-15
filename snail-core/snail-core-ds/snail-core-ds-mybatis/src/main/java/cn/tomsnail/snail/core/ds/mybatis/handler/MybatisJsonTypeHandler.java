package cn.tomsnail.snail.core.ds.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import com.alibaba.fastjson.JSON;

/**
 * The type Mybatis json type handler.
 *
 * @param <T> the type parameter
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MybatisJsonTypeHandler<T extends Object> extends BaseTypeHandler<T> {
	
	
 
    private Class<T> clazz;

    /**
     * Instantiates a new Mybatis json type handler.
     *
     * @param clazz the clazz
     */
    public MybatisJsonTypeHandler(Class<T> clazz) {
        if (clazz == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.clazz = clazz;
    }
 
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, this.toJson(parameter));
    }
 
    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.toObject(rs.getString(columnName), clazz);
    }
 
    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.toObject(rs.getString(columnIndex), clazz);
    }
 
    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.toObject(cs.getString(columnIndex), clazz);
    }
 
    private String toJson(T object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    private T toObject(String content, Class<?> clazz) {
        if (content != null && !content.isEmpty()) {
            try {
                return (T) JSON.parseObject(content, clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }
}
