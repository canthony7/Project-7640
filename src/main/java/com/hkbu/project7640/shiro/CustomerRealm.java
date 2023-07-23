package com.hkbu.project7640.shiro;

import com.hkbu.project7640.dto.ResponseEnum;
import com.hkbu.project7640.entity.JwtToken;
import com.hkbu.project7640.entity.Role;
import com.hkbu.project7640.entity.User;
import com.hkbu.project7640.exception.GlobalException;
import com.hkbu.project7640.service.IRoleService;
import com.hkbu.project7640.service.IUserService;
import com.hkbu.project7640.utils.JWTUtils;
import com.hkbu.project7640.utils.UserHolder;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Chet
 * @date 12/4/2023 1:34 pm
 * @description 自定义realm
 */
public class CustomerRealm extends AuthorizingRealm {

    @Resource
    IUserService userService;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    JWTUtils jwtUtils;

    @Resource
    IRoleService roleService;

    // 这里一直没注意
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    // 授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = principalCollection.getPrimaryPrincipal().toString();
        User user = jwtUtils.getUserByToken(token);
        Role role = roleService.getRoleByUserId(user.getId());
        if (role != null){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            System.out.println("分配角色:" + role);
            simpleAuthorizationInfo.addRole(role.getRoleName());
            return simpleAuthorizationInfo;
        }
        return null;
    }

    // 认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = ((String) auth.getCredentials());
        if (token == null){
            throw new GlobalException(ResponseEnum.EMPTY_TOKEN);
        }
        User user = checkTokenEffective(token);
        UserHolder.saveUser(user);
        return new SimpleAuthenticationInfo(token, token, "myRealm");
    }

    // 验证token有效性
    public User checkTokenEffective(String token){
        User user = jwtUtils.getUserByToken(token);
        if (ObjectUtils.isEmpty(user)){
            throw new GlobalException(ResponseEnum.NOT_EXIST_ERROR);
        }
        if (!jwtTokenRefresh(token, user)){
            throw new GlobalException(ResponseEnum.TOKEN_ERROR);
        }
        return user;
    }

    public boolean jwtTokenRefresh(String token, User user){
        String cacheToken = (String) redisTemplate.opsForValue().get("userToken:" + user.getTelephone());
        // 缓存没过期，生命周期过期了，就重新签名
        if (cacheToken != null){
            if (!jwtUtils.verifyToken(cacheToken)){
                String newToken = jwtUtils.getToken(user);
                redisTemplate.opsForValue().set("userToken:" + user.getTelephone(), newToken, 3, TimeUnit.HOURS);
            } else {
                redisTemplate.opsForValue().set("userToken:" + user.getTelephone(), cacheToken, 3, TimeUnit.HOURS);
            }
            return true;
        }
        return false;
    }
}
