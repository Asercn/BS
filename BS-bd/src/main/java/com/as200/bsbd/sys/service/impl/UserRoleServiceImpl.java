package com.as200.bsbd.sys.service.impl;

import com.as200.bsbd.sys.entity.UserRole;
import com.as200.bsbd.sys.mapper.UserRoleMapper;
import com.as200.bsbd.sys.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author as200
 * @since 2023-08-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public void removeByUserID(Integer userID) {
        this.baseMapper.removeByUserID(userID);
    }


}
