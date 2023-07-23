package com.hkbu.project7640.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hkbu.project7640.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chet
 * @since 2023-04-03
 */
public interface IUserService extends IService<User> {

    User getUserByUsernameAndPassword(String username, String password);

    User getUserByName(String username);

}
