package com.hkbu.project7640.controller;

import com.hkbu.project7640.dto.LoginDto;
import com.hkbu.project7640.dto.ResponseBean;
import com.hkbu.project7640.dto.ResponseEnum;
import com.hkbu.project7640.entity.Role;
import com.hkbu.project7640.entity.User;
import com.hkbu.project7640.exception.GlobalException;
import com.hkbu.project7640.service.IRoleService;
import com.hkbu.project7640.service.IUserService;
import com.hkbu.project7640.utils.JWTUtils;
import com.hkbu.project7640.utils.UserHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chet
 * @since 2023-04-03
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/project7640")
public class UserController {

    @Resource
    IUserService userService;

    @Resource
    JWTUtils jwtUtils;

    @Resource
    IRoleService roleService;

    @Resource
    RedisTemplate redisTemplate;

    @PostMapping("/login")
    public ResponseBean login(@RequestBody LoginDto loginDto){
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        User user = userService.getUserByUsernameAndPassword(username, password);
        if (user == null) {
            throw new GlobalException(ResponseEnum.LOGIN_ERROR);
        } else {
            String token = jwtUtils.getToken(user);
            // 存入redis，生存时间3小时
            redisTemplate.opsForValue().set("userToken:" + user.getTelephone(), token, 3, TimeUnit.HOURS);
            Role role = roleService.getRoleByUserId(user.getId());
            // 返回给前端
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("role", role.getRoleName());
            UserHolder.saveUser(user);
            return ResponseBean.success(map);
        }
    }

    @GetMapping("hello")
    public ResponseBean hello(){
        return ResponseBean.success("hello");
    }
}
