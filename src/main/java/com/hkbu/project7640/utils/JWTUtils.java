package com.hkbu.project7640.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hkbu.project7640.entity.Role;
import com.hkbu.project7640.entity.User;
import com.hkbu.project7640.service.IRoleService;
import com.hkbu.project7640.service.IUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Map;

/**
 * @author Chet
 * @date 10/2/2023 7:33 pm
 * passed
 */
@Component
public class JWTUtils {

    @Resource
    IUserService userService;
    @Resource
    IRoleService roleService;

    protected static final String SECRET = "jnhsiui@$%";

    public String getToken(User user){
        String token;
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, 2);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Role role = roleService.getRoleByUserId(user.getId());
        token = JWT.create()
                .withIssuer("sale-system")
                .withClaim("username", user.getTelephone())
                .withClaim("role", role.getRoleName())
                .withExpiresAt(instance.getTime())
                .sign(algorithm);
        return token;
    }

    // 验证token并通过token得到用户
    public boolean verifyToken(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        } catch (JWTVerificationException e){
            return false;
        }
    }

    // 通过token获取用户信息
    public User getUserByToken(String token){
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> claims = jwt.getClaims();
        System.out.println(claims.get("username"));
        User user = userService.getUserByName(claims.get("username").asString());
        return user;
    }
}
