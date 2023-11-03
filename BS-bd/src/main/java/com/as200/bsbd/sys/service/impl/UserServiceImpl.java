package com.as200.bsbd.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.as200.bsbd.common.util.JwtUtil;
import com.as200.bsbd.sys.entity.Menu;
import com.as200.bsbd.sys.entity.User;
import com.as200.bsbd.sys.mapper.UserMapper;
import com.as200.bsbd.sys.service.IMenuService;
import com.as200.bsbd.sys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author as200
 * @since 2023-08-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IMenuService iMenuService;

    @Override
    public Map<String, Object> login(User user) {
//        查询
        LambdaQueryWrapper<User> warpper = new LambdaQueryWrapper<>();
        warpper.eq(User::getUsername, user.getUsername());
        warpper.eq(User::getPassword, user.getPassword());
        User loginUser = this.baseMapper.selectOne(warpper);
//        不为空则生成token，并将用户信息存入redis
        if (loginUser != null){
//            生成token
            // 方案1
//            String key = "user:" + UUID.randomUUID();
//            存入redis
//            loginUser.setPassword(null);
//            redisTemplate.opsForValue().set(key, loginUser, 720, TimeUnit.MINUTES);


            // 方案2
//            创建jwt
            // 2023年9月26日11:45:21 出错 --bug -- 已解决
            String token = jwtUtil.createToken(loginUser);
//            返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        //从redis获取用户信息
//        Object obj = redisTemplate.opsForValue().get(token);
        User loginUser = null;
        try {
            loginUser = jwtUtil.parseToken(token, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (loginUser != null){
//            User loginUser = JSON.parseObject(JSON.toJSONString(obj), User.class);
            Map<String, Object> data = new HashMap<>();
            data.put("name", loginUser.getUsername());
            data.put("avatar", loginUser.getAvatar());

            // 角色
            List<String> roles = this.baseMapper.getRoleNameByUserId(loginUser.getId());
            data.put("role", roles);

            // 权限列表
            List<Menu> menuList = iMenuService.getMenuListByUserId(loginUser.getId());
            data.put("menuList", menuList);

            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
//        redisTemplate.delete(token);

    }

    @Override
    public Object getUserInfo(Long pageNo, Long pageSize, String userName) {
        Long offset = (pageNo-1) * pageSize;
        return this.baseMapper.getUserInfo(offset, pageSize, userName);
    }

    @Override
    public Map<String, Object> getUserInfoByID(Integer userID) {
        return this.baseMapper.getUserInfoByID(userID);
    }
}
