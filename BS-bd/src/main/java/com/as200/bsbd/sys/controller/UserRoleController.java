package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.UserRole;
import com.as200.bsbd.sys.service.IUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author as200
 * @since 2023-08-21
 */
@RestController
@RequestMapping("/userRole")
//@CrossOrigin
public class UserRoleController {

    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping("/all")
    public Result<List<UserRole>> getAllUserRole(){
        List<UserRole> data = userRoleService.list();
        return Result.success(data,"查询成功");
    }

    // 根据用户ID修改用户权限
    @PutMapping
    public Result<?> updateRoleByUserID(@RequestBody UserRole userRole){
        UpdateWrapper<UserRole> wrapper = new UpdateWrapper<>();
        // 2023.9.13修改功能
        // 预期，根据user_id改变role_id，探究wrapper的update用法
        // 2023.9.14 完成根据userid改变roleid方法
        wrapper.set("role_id",userRole.getRoleId()).eq("user_id", userRole.getUserId());
        userRoleService.update(wrapper);
        return Result.success("修改成功");
    }

}
