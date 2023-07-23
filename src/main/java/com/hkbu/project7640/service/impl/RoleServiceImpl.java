package com.hkbu.project7640.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hkbu.project7640.entity.Role;
import com.hkbu.project7640.entity.UserRole;
import com.hkbu.project7640.mapper.RoleMapper;
import com.hkbu.project7640.mapper.UserRoleMapper;
import com.hkbu.project7640.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chet
 * @since 2023-04-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    RoleMapper roleMapper;
    @Resource
    UserRoleMapper userRoleMapper;

    @Override
    public Role getRoleByUserId(Integer id) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        UserRole userRole = userRoleMapper.selectOne(wrapper);
        return roleMapper.selectById(userRole.getRoleId());
    }
}
