package com.hkbu.project7640.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Chet
 * @date 11/4/2023 10:22 pm
 * @description 登录接收类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

}
