package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.Role;
import com.as200.bsbd.sys.service.IRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/all")
    public Result<List<Role>> getRole(){
        List<Role> data = roleService.list();
        return Result.success(data, "查询成功");
    }

    // 获取到角色信息,分页
    @GetMapping
    public Result<?> getRoleInfo(@RequestParam(value = "pageNo")Long pageNo,
                                 @RequestParam(value = "pageSize")Long pageSize,
                                 @RequestParam(value = "roleName", required = false)String roleName){
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper();
        wrapper.eq(StringUtils.hasLength(roleName), Role::getRoleName, roleName);
//        wrapper.orderByDesc(Role::getRoleId);

        Page<Role> page = new Page<>(pageNo,pageSize);
        roleService.page(page, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("roleInfo", page.getRecords());

        return Result.success(data, "查询成功");
    }
    @GetMapping("/{id}")
    public Result<?> getRoleInfoById(@PathVariable(value = "id")Integer ID){
        return Result.success(roleService.getById(ID),"查询成功");
    }

    @PostMapping
    public Result<Role> addRole(@RequestBody Role role){
        roleService.save(role);
        return Result.success("添加成功");
    }

    @PutMapping
    public Result<Role> updateRole(@RequestBody Role role){
        roleService.updateById(role);
        return Result.success("修改成功");
    }
    @DeleteMapping
    public Result<Role> deleteRole(@RequestBody Role role){
        roleService.removeById(role);
        return Result.success("删除成功");
    }
}
