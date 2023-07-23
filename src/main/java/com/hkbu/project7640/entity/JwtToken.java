package com.hkbu.project7640.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Chet
 * @date 12/4/2023 11:34 am
 * @description jwt实体类
 */
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;
    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
