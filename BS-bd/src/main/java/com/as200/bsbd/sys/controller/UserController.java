package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.User;
import com.as200.bsbd.sys.service.IRoleService;
import com.as200.bsbd.sys.service.IUserRoleService;
import com.as200.bsbd.sys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author as200
 * @since 2023-08-17
 */
@RestController
@RequestMapping("/user")
@CrossOrigin // 跨域处理
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    @GetMapping("/all")
    public Result<List<User>> getAllUser(){
        List<User> list = userService.list();
        return Result.success(list, "查询成功");
    }
    @PostMapping("/login")
    public Result<Map<String, Object>> login (@RequestBody User user){
        Map<String, Object> data = userService.login(user);
        if (!data.isEmpty()){
            return Result.success(data);
        }
        return Result.fail(20002,"用户名或密码错误");
    }

    @ApiOperation("获取用户的登入信息")
    @GetMapping("/info")
    public Result<Map<String, Object>> info(@RequestParam("token") String token){

        Map<String, Object> data = userService.getUserInfo(token);
        if (!data.isEmpty()){
            return Result.success(data);
        }
        return Result.fail(20003,"用户登入信息无效，请重新登入");
    }
    @ApiOperation("登出方法")
    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token){
        //从redis中清除key
        userService.logout(token);
        return Result.success();
    }

    // 获取到用户信息,以及用户权限名称,分页
    @ApiOperation("获取到用户信息,以及用户权限名称,分页")
    @GetMapping
    public Result<?> getUserInfo(@RequestParam(value = "pageNo")Long pageNo,
                                 @RequestParam(value = "pageSize")Long pageSize,
                                 @RequestParam(value = "username", required = false)String userName){
        Map<String, Object> data = new HashMap<>();
        LambdaQueryWrapper<User> warpper = new LambdaQueryWrapper<>();
        warpper.eq(StringUtils.hasLength(userName), User::getUsername, userName);
        data.put("total",userService.count(warpper));
        data.put("userInfo", userService.getUserInfo(pageNo, pageSize, userName));
        return Result.success(data, "查询成功");
    }

    // 根据用户ID获取到用户的信息
    @ApiOperation("根据用户ID获取到用户的信息")
    @GetMapping("/{userID}")
    public Result<?> getUserInfoByID(@PathVariable(value = "userID")Integer userID){
        Map<String, Object> data = userService.getUserInfoByID(userID);
        return Result.success(data, "查询成功");
    }

    @ApiOperation("删除用户以及相关信息")
    @DeleteMapping("/{userID}")
    public Result<?> deleteUserInfoByUserID(@PathVariable(value = "userID")Integer userID){
        // 移除用户信息
        userService.removeById(userID);
        // 移除用户权限
        userRoleService.removeByUserID(userID);
        return Result.success("删除成功");
    }


    // 根据用户ID修改用户信息
    @ApiOperation("根据用户ID修改用户信息")
    @PutMapping
    public Result<?> updateUser(@RequestBody User user){
        userService.updateById(user);
        return Result.success("修改成功");
    }

    @ApiOperation("修改密码")
    @PutMapping("/changepwd")
    public Result<?> updatePassWord(@RequestParam(value = "id")Integer ID,
                                    @RequestParam(value = "oldPassword")String oldpwd,
                                    @RequestParam(value = "newPassword")String newpwd) {
        // 查询旧密码是否和原旧密码相等
        User user = userService.checkOldPwd(ID,oldpwd);
        if (user != null) {
            // 修改密码并加密
            user.setPassword(passwordEncoder.encode(newpwd));
            userService.updateById(user);
            return Result.success("修改成功,请重新登入");
        } else {
            return Result.fail("修改失败,可能是原密码不正确");
        }
    }

    @ApiOperation("新增用户,以及用户的默认角色")
    @PostMapping("/add")
    public Result<?> addUser(@RequestBody User user) {
        userService.saveUser(user);
        return Result.success("注册成功");
    }

    @ApiOperation("根据传入的用户名来判断数据库中是否有这个用户名由此判断能否注册此用户")
    @GetMapping("/checkUser")
    public Result<Boolean> CheckUser(@RequestParam String username) {
        // 查询数据库中是否存在该用户名
        Boolean data = userService.findByUsername(username);
        return Result.success(data);
    }



}
