package com.as200.bsbd.sys.service;

import com.as200.bsbd.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author as200
 * @since 2023-08-17
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);
    Map<String, Object> getUserInfo(String token);

    void logout(String token);

}