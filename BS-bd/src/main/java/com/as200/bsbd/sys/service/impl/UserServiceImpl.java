package com.as200.bsbd.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.as200.bsbd.common.util.JwtUtil;
import com.as200.bsbd.sys.entity.Menu;
import com.as200.bsbd.sys.entity.RoleMenu;
import com.as200.bsbd.sys.entity.User;
import com.as200.bsbd.sys.entity.UserRole;
import com.as200.bsbd.sys.mapper.RoleMenuMapper;
import com.as200.bsbd.sys.mapper.UserMapper;
import com.as200.bsbd.sys.mapper.UserRoleMapper;
import com.as200.bsbd.sys.service.IMenuService;
import com.as200.bsbd.sys.service.IRoleMenuService;
import com.as200.bsbd.sys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private IMenuService iMenuService;

    @Override
    public Map<String, Object> login(User user) {
//        根据用户名查询
        LambdaQueryWrapper<User> warpper = new LambdaQueryWrapper<>();
        warpper.eq(User::getUsername, user.getUsername());
//        warpper.eq(User::getPassword, user.getPassword());
        User loginUser = this.baseMapper.selectOne(warpper);
//        不为空则生成token，并将用户信息存入redis
        // 不为空不被禁用且数据库密码和传入的密码相同
        if (loginUser != null && passwordEncoder.matches(user.getPassword(), loginUser.getPassword())){
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
        if (loginUser != null && loginUser.getState().equals("1")){
//            User loginUser = JSON.parseObject(JSON.toJSONString(obj), User.class);
            Map<String, Object> data = new HashMap<>();
            data.put("id", loginUser.getId());
            data.put("name", loginUser.getUsername());
            data.put("email", loginUser.getEmail());
            data.put("phone", loginUser.getPhone());
            data.put("avatar", loginUser.getAvatar());

            // 角色
//            List<String> roles = this.baseMapper.getRoleNameByUserId(loginUser.getId());
//            data.put("role", roles);
            data.put("role", this.baseMapper.getRoleNameByUserId(loginUser.getId()));

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

    @Override
    @Transactional
    public void saveUser(User user) {
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 存入用户
        this.baseMapper.insert(user);
        // 存入用户的角色信息
        userRoleMapper.insert(new UserRole(null, user.getId(), 3));

    }

    @Override
    public Boolean findByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(username), User::getUsername, username);
        User data = this.baseMapper.selectOne(wrapper);
        if (data != null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public User checkOldPwd(Integer id, String oldpwd) {
        // 获取到此ID的用户的原旧密码
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, id);
        User user = this.baseMapper.selectOne(wrapper);
        // 用原来的旧密码和oldpwd进行比较
        // 传回一个USER类型
        if (user != null && passwordEncoder.matches(oldpwd, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
