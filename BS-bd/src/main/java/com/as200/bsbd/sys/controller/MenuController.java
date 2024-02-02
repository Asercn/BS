package com.as200.bsbd.sys.controller;

import com.as200.bsbd.common.vo.Result;
import com.as200.bsbd.sys.entity.Menu;
import com.as200.bsbd.sys.mapper.MenuMapper;
import com.as200.bsbd.sys.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @since 2023-10-28
 */
@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {
    @Autowired
    private IMenuService menuService;


    @ApiOperation("查询所有列表")
    @GetMapping
    public Result<List<Menu>> getAllMenu(){
        List<Menu> menuList = menuService.getAllMenu();
        return Result.success(menuList, "查询成功");
    }

    @ApiOperation("新增菜单")
    @PostMapping
    public Result<?> addMenu(@RequestBody Menu menu) {
        // 新增的话肯定是叶子目录
        menu.setIsLeaf("Y");
        if(menu.getIcon() == null) {
            menu.setIcon("el-icon-warning");
        }
        // 获取父级菜单
        Menu parentMenu = menuService.getParentMenu(menu.getParentId());
        if (menu.getParentId().equals(0)) {
            // 如果父级为0，则设置Component为Layout，Path为/拼接上name
            menu.setComponent("Layout");
            menu.setPath('/' + menu.getName());
        } else if(parentMenu != null) {
            // 如果父级不为0，且有父级则根据父级的路径拼接上文件名
            menu.setComponent(parentMenu.getName() + '/' + menu.getName());
            menu.setPath(menu.getName());
            // 设置父级的is_leaf为N
            parentMenu.setIsLeaf("N");
            menuService.updateById(parentMenu);
        } else {
            return Result.fail("父级菜单不存在");
        }
        // 暂时不会创建文件，先不弄 2024年2月2日03:40:42
//        menuService.createMenu(menu);
        Map<String, String> data = new HashMap<>();
        String filePath = menu.getComponent() + ".vue";
        data.put("filePath", filePath);
        menuService.save(menu);
        return Result.success(data,"新增成功");
    }




    @ApiOperation("删除目录")
    @DeleteMapping("/{menuId}")
    public Result<?> deleteMenu(@PathVariable(value = "menuId")Integer menuId) {
        menuService.deleteMenu(menuId);
        return Result.success("删除成功");
    }


    @ApiOperation("根据ID获取菜单")
    @GetMapping("/{menuId}")
    public Result<?> getMenuById(@PathVariable(value = "menuId")Integer menuId) {
        return Result.success(menuService.getById(menuId));
    }

    @ApiOperation("修改目录")
    @PutMapping
    public Result<?> updateMenu(@RequestBody Menu menu) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        menuService.updateById(menu);
        // 查询自己有没有叶子目录
        wrapper.eq(Menu::getParentId, menu.getMenuId());
        if (menuService.list(wrapper).isEmpty()) {
            menu.setIsLeaf("Y");
        } else {
            menu.setIsLeaf("N");
        }
        menuService.updateById(menu);
        return Result.success("修改成功");
    }

}
