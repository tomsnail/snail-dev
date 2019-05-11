package cn.tomsnail.snail.ext.security.jwt;

import cn.tomsnail.snail.core.util.math.encrypt.Base64Util;
import cn.tomsnail.snail.core.util.string.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtHelper {


    /**
     * token 过期时间, 单位: 秒. 这个值表示 30 天
     */
    private static final long TOKEN_EXPIRED_TIME = 30 * 24 * 60 * 60;

    /**
     * jwt 加密解密密钥
     */
    private static final String JWT_SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";

    public static final String jwtId = "tokenId";
    /**
     * 创建JWT
     */
    public static String createJWT(Map<String, Object> claims, Long time) {
        return createJWT(claims,time,JWT_SECRET,"HmacSHA256");
    }

    public static String createJWT(Map<String, Object> claims, Long time,String secret,String alg) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        Date now = new Date(System.currentTimeMillis());

        SecretKey secretKey = generalKey(secret,alg);
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(jwtId)                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)
                .signWith(secretKey,signatureAlgorithm);//iat: jwt的签发时间
        if (time >= 0) {
            long expMillis = nowMillis + time;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();
    }

    /**
     * 验证jwt
     */
    public static Claims verifyJwt(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey(JWT_SECRET,"HmacSHA256");
        Claims claims;
        try {
            claims = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(key)         //设置签名的秘钥
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }//设置需要解析的jwt
        return claims;

    }

    public static Claims verifyJwt(String token,String secret,String alg) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey(secret,alg);
        Claims claims;
        try {
            claims = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(key)         //设置签名的秘钥
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }//设置需要解析的jwt
        return claims;

    }

    public static SecretKey generalKey(String secret,String alg) {
        String stringKey = secret;

        byte[] encodedKey = Base64Util.getBytesFromBase64(stringKey);

        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, alg);
        return key;
    }

    /**
     * 根据userId和openid生成token
     */
    public static String generateToken(String openId, Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("openId", openId);
        return createJWT(map, TOKEN_EXPIRED_TIME);
    }

    public static void main(String[] args) {
        String token = generateToken("123",1);
        System.out.println(token);
        System.out.println(verifyJwt(token));

    }

}
