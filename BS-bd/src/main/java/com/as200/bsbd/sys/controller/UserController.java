package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.User;
import com.as200.bsbd.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    private IUserService userService;
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

    @GetMapping("/info")
    public Result<Map<String, Object>> info(@RequestParam("token") String token){
        //从redis获取用户信息
        Map<String, Object> data = userService.getUserInfo(token);
        if (!data.isEmpty()){
            return Result.success(data);
        }
        return Result.fail(20003,"用户登入信息无效，请重新登入");
    }
    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token){
        //从redis中清除key
        userService.logout(token);
        return Result.success();
    }

}
