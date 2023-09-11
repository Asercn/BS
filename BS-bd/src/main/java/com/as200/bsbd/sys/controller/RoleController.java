package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.Role;
import com.as200.bsbd.sys.service.IRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping
    public Result<List<Role>> getRoleInfo(){
        return Result.success(roleService.list(),"查询成功");
    }
    @GetMapping("/{id}")
    public Result<?> getRoleInfoById(@PathVariable(value = "id")Integer ID){
        return Result.success(roleService.getById(ID),"查询成功");
    }

}
