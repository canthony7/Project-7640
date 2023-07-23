package com.hkbu.project7640.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hkbu.project7640.entity.Role;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chet
 * @since 2023-04-03
 */
public interface IRoleService extends IService<Role> {

    // 通过用户id获取角色信息
    public Role getRoleByUserId(Integer id);

}
