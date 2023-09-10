package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.Role;
import com.as200.bsbd.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
}
