package com.as200.bsbd.sys.service;

import com.as200.bsbd.sys.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author as200
 * @since 2023-08-21
 */
public interface IUserRoleService extends IService<UserRole> {

    // 根据用户ID移除用户权限
    void removeByUserID(Integer userID);
}
